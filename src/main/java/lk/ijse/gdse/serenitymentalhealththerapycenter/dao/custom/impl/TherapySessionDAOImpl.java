package lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.gdse.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.TherapySessionDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.TherapySessionDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TherapySessionDAOImpl implements TherapySessionDAO {

    @Override
    public boolean save(TherapySession entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(TherapySession entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {

            TherapySession existingTherapySession = session.get(TherapySession.class, entity.getSessionId());
            if (existingTherapySession == null) {
                return false;
            }

            session.merge(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE FROM TherapySession WHERE sessionId = :sessionId");
            query.setParameter("sessionId", id);
            int result = query.executeUpdate();

            transaction.commit();
            return result > 0;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public TherapySession search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            TherapySession therapySession = session.get(TherapySession.class, id);
            return therapySession;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<TherapySession> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            List<TherapySession> therapySessions = session.createQuery("FROM TherapySession ", TherapySession.class).list();
            return therapySessions;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void setSession(Session session) throws Exception {
    }

    @Override
    public String getNextID() throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {

            Integer maxNum = (Integer) session.createQuery(
                    "SELECT MAX(CAST(SUBSTRING(ts.sessionId, 5) AS int)) " +
                            "FROM TherapySession ts " +
                            "WHERE ts.sessionId LIKE 'TS%' " +
                            "AND LENGTH(ts.sessionId) = 5"
            ).uniqueResult();

            return maxNum != null ?
                    String.format("TS%03d", maxNum + 1) :
                    "TS001";
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate next ID", e);
        }
    }

    @Override
    public TherapySessionDTO getSession(String sessionId) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            TherapySession therapySession = session.createQuery(
                            "FROM TherapySession WHERE sessionId = :sessionId", TherapySession.class)
                    .setParameter("sessionId", sessionId)
                    .uniqueResult();

            transaction.commit();
            session.close();

            if (therapySession != null) {
                return new TherapySessionDTO(
                        therapySession.getSessionId(),
                        therapySession.getPatientId(),
                        therapySession.getPatientName(),
                        therapySession.getTherapistId(),
                        therapySession.getProgram(),
                        therapySession.getSessionDate(),
                        therapySession.getTime(),
                        therapySession.getDuration(),
                        therapySession.getStatus()
                );
            } else {
                return null;
            }
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            throw e;
        }
    }

    @Override
    public ArrayList<String> getSessionId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        ArrayList<String> sessionId = new ArrayList<>();

        try {
            Query<String> query = session.createQuery("SELECT t.id FROM TherapySession t", String.class);
            List<String> resultList = query.getResultList();
            sessionId.addAll(resultList);

            System.out.println("Session IDs retrieved: " + sessionId); // Debug print
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return sessionId;
    }

    private boolean isTimeInRange(LocalTime time, LocalTime start, LocalTime end) {
        return !time.isBefore(start) && !time.isAfter(end);
    }

}
