package lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.TherapyProgramDAO;
import org.hibernate.Session;

import java.util.List;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {
    @Override
    public boolean save(Object entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Object entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Object o) throws Exception {
        return false;
    }

    @Override
    public Object search(Object o) throws Exception {
        return null;
    }

    @Override
    public List getAll() throws Exception {
        return List.of();
    }

    @Override
    public void setSession(Session session) throws Exception {

    }
}
