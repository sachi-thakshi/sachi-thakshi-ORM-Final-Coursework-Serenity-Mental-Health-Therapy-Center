package lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.UserBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.UserDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.UserDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.User;
import lk.ijse.gdse.serenitymentalhealththerapycenter.util.PasswordUtil;

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
}
