package lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.gdse.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {

    private Session session;

    @Override
    public boolean save(Object entity) throws Exception {
        Transaction transaction = session.beginTransaction();
        try {
            session.persist((TherapyProgram) entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean update(Object entity) throws Exception {
        Transaction transaction = session.beginTransaction();
        try {
            TherapyProgram existing = session.get(TherapyProgram.class, ((TherapyProgram) entity).getProgramId());
            if (existing == null) return false;

            session.merge((TherapyProgram) entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean delete(Object o) throws Exception {
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE FROM TherapyProgram WHERE programId = :id");
            query.setParameter("id", (String) o);
            int result = query.executeUpdate();
            transaction.commit();
            return result > 0;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Object search(Object o) throws Exception {
        try {
            return session.get(TherapyProgram.class, (String) o);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List getAll() throws Exception {
        try {
            return session.createQuery("FROM TherapyProgram", TherapyProgram.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
    }

    @Override
    public String getNextID() throws SQLException {
        try {
            Query<String> query = session.createQuery(
                    "SELECT tp.programId FROM TherapyProgram tp WHERE tp.programId LIKE 'TP%' ORDER BY tp.programId DESC",
                    String.class
            );
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null) {
                int number = Integer.parseInt(lastId.substring(2));
                return String.format("TP%03d", number + 1);
            } else {
                return "TP001"; // First ID if no records
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to generate next ID", e);
        }
    }

    @Override
    public ArrayList<String> getPrograms() throws SQLException {
        ArrayList<String> programs = new ArrayList<>();
        try {
            Query<String> query = session.createQuery("SELECT tp.name FROM TherapyProgram tp", String.class);
            programs = (ArrayList<String>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load programs", e);
        }
        return programs;
    }
}
