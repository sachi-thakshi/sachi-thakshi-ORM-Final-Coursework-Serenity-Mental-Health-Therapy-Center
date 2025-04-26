package lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.UserBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.UserDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.UserDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.User;
import lk.ijse.gdse.serenitymentalhealththerapycenter.util.PasswordUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOFactory.DaoType.USER);

    @Override
    public boolean authenticateUser(String userName, String password) throws Exception {
        User user = userDAO.findByUsername(userName);

        if (user != null) {
            boolean passwordMatches = PasswordUtil.checkPassword(password, user.getPassword());

            return passwordMatches;
        }
        return false;
    }

    @Override
    public UserDTO getUserByUsername(String userName) throws Exception {
        User user = userDAO.findByUsername(userName);
        if (user != null) {
            return new UserDTO(
                    user.getUserId(),
                    user.getUserName(),
                    user.getPassword(),
                    user.getRole()
            );
        }
        return null;
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        if (userDAO.findByUsername(userDTO.getUserName()) != null) {
            return false;
        }

        String hashedPassword = PasswordUtil.hashPassword(userDTO.getPassword());
        System.out.println("Hashed password during registration: " + hashedPassword);

        User user = new User(userDTO.getUserId(), userDTO.getUserName(), hashedPassword, userDTO.getRole());
        userDAO.save(user);
        return true;
    }

    @Override
    public UserDTO searchUser(String userId) throws Exception {
        try {
            User user = userDAO.search(userId);
            if (user == null) {
                new Alert(Alert.AlertType.ERROR,"User Not Found with ID: " + userId).show();
            }
            return new UserDTO(
                    user.getUserId(),
                    user.getUserName(),
                    user.getPassword(),
                    user.getRole()
            );
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"User Not Found").show();
            return null;
        }
    }

    @Override
    public boolean confirmation(String userId, String password) throws SQLException {
        User user = null;
        try {
            user = userDAO.search(userId);
            if (user != null) {
                return PasswordUtil.checkPassword(password, user.getPassword());
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteUser(String userId) throws Exception {
        return userDAO.delete(userId);
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        String hashedPassword = PasswordUtil.hashPassword(userDTO.getPassword());

        User user = new User(
                userDTO.getUserId(),
                userDTO.getUserName(),
                hashedPassword,
                userDTO.getRole()
        );

        return userDAO.update(user);
    }

    @Override
    public ArrayList<UserDTO> getAllUser() throws Exception {
        ArrayList<UserDTO> userAccountDtos = new ArrayList<>();
        ArrayList<User> users = (ArrayList<User>) userDAO.getAll();

        for (User user : users) {
            userAccountDtos.add(new UserDTO(
                    user.getUserId(),
                    user.getUserName(),
                    user.getPassword(),
                    user.getRole()
            ));
        }

        return userAccountDtos;
    }

    @Override
    public String getNextuserId() throws SQLException, ClassNotFoundException {
        return userDAO.getNextId();
    }
}
