package lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom;


import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.CrudDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.User;
import org.hibernate.HibernateException;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User, String> {
    User findByUsername(String username) throws Exception;
    boolean confirmation(String userId, String password) throws SQLException;
    String getNextId() throws HibernateException;
}
