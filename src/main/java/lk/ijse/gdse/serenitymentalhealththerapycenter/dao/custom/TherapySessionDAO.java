package lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom;

import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.CrudDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.TherapySessionDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.TherapySession;
import org.hibernate.Session;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public interface TherapySessionDAO extends CrudDAO<TherapySession, String> {
    String getNextID() throws SQLException;
    TherapySessionDTO getSession(String sessionId) throws SQLException;
    ArrayList<String> getSessionId() throws SQLException;
}
