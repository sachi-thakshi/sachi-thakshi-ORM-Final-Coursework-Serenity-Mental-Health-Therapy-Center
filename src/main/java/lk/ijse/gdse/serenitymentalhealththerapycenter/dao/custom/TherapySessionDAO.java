package lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom;

import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.CrudDAO;
import org.hibernate.Session;

import java.util.List;

public interface TherapySessionDAO extends CrudDAO {
    boolean save(Object entity) throws Exception;

    boolean update(Object entity) throws Exception;

    boolean delete(Object o) throws Exception;

    Object search(Object o) throws Exception;

    List getAll() throws Exception;

    void setSession(Session session) throws Exception;
}
