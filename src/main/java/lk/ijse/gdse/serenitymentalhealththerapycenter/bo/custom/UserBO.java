package lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom;

import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.SuperBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.UserDTO;

public interface UserBO extends SuperBO {
    boolean authenticateUser(String userName, String password) throws Exception;
    UserDTO getUserByUsername(String userName) throws Exception;
    boolean saveUser(UserDTO userDTO) throws Exception;
}
