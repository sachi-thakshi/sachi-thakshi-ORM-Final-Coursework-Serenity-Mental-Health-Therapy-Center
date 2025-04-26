package lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.TherapySessionBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.TherapySessionDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.TherapySessionDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.TherapySession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TherapySessionBOImpl implements TherapySessionBO {

    TherapySessionDAO therapySessionDAO = DAOFactory.getInstance().getDAO(DAOFactory.DaoType.THERAPY_SESSION);
    @Override
    public String getNextTherapySessionId() throws Exception {
        return therapySessionDAO.getNextID();
    }

    @Override
    public ArrayList<TherapySessionDTO> getAllTherapySession() throws Exception {
        ArrayList<TherapySessionDTO> therapySessionDTOS = new ArrayList<>();
        ArrayList<TherapySession> therapySessions = (ArrayList<TherapySession>) therapySessionDAO.getAll();

        for (TherapySession therapySession : therapySessions) {
            therapySessionDTOS.add(new TherapySessionDTO(
                    therapySession.getSessionId(),
                    therapySession.getPatientId(),
                    therapySession.getPatientName(),
                    therapySession.getTherapistId(),
                    therapySession.getProgram(),
                    therapySession.getSessionDate(),
                    therapySession.getTime(),
                    therapySession.getDuration(),
                    therapySession.getStatus()
            ));
        }
        return therapySessionDTOS;
    }

    @Override
    public boolean saveTherapySession(TherapySessionDTO therapySessionDTO) throws Exception {
        return therapySessionDAO.save(
                new TherapySession(
                        therapySessionDTO.getSessionId(),
                        therapySessionDTO.getPatientId(),
                        therapySessionDTO.getPatientName(),
                        therapySessionDTO.getTherapistId(),
                        therapySessionDTO.getProgram(),
                        therapySessionDTO.getSessionDate(),
                        therapySessionDTO.getTime(),
                        therapySessionDTO.getDuration(),
                        therapySessionDTO.getStatus()
                )
        );
    }

    @Override
    public boolean updateTherapySession(TherapySessionDTO therapySessionDTO) throws Exception {
        return therapySessionDAO.update(
                new TherapySession(
                        therapySessionDTO.getSessionId(),
                        therapySessionDTO.getPatientId(),
                        therapySessionDTO.getPatientName(),
                        therapySessionDTO.getTherapistId(),
                        therapySessionDTO.getProgram(),
                        therapySessionDTO.getSessionDate(),
                        therapySessionDTO.getTime(),
                        therapySessionDTO.getDuration(),
                        therapySessionDTO.getStatus()
                )
        );
    }

    @Override
    public TherapySessionDTO searchTherapySession(String sessionId) throws Exception {
        TherapySession therapySession = therapySessionDAO.search(sessionId);

        if (therapySession == null) {
            return null;
        }
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
    }

    @Override
    public boolean deleteTherapySession(String sessionId) throws Exception {
        return therapySessionDAO.delete(sessionId);
    }
}
