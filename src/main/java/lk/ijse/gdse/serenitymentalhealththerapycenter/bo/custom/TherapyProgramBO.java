package lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom;

import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.SuperBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.TherapyProgramDTO;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TherapyProgramBO extends SuperBO {
    String getNextTherapyProgramId() throws SQLException;
    ArrayList<TherapyProgramDTO> getAllTherapyProgram() throws Exception;
    boolean saveTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws Exception;
    boolean updateTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws Exception;
    TherapyProgramDTO searchTherapyProgram(String id) throws Exception;
    boolean deleteTherapyProgram(String id) throws Exception;
    void setSession(Session session) throws Exception;
}
