package lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom;


import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.CrudDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.User;

public interface UserDAO extends CrudDAO<User, String> {
    User findByUsername(String username) throws Exception;
}
