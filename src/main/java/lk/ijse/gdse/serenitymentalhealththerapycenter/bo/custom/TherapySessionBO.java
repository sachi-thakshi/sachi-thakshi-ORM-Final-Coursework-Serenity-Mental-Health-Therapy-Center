package lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom;

import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.SuperBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.TherapySessionDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface TherapySessionBO extends SuperBO {
    String getNextTherapySessionId() throws Exception;
    ArrayList<TherapySessionDTO> getAllTherapySession() throws Exception;
    boolean saveTherapySession(TherapySessionDTO therapySessionDTO) throws Exception;
    boolean updateTherapySession(TherapySessionDTO therapySessionDTO) throws Exception;
    TherapySessionDTO searchTherapySession(String sessionId) throws Exception;
    boolean deleteTherapySession(String sessionId) throws Exception;
}
