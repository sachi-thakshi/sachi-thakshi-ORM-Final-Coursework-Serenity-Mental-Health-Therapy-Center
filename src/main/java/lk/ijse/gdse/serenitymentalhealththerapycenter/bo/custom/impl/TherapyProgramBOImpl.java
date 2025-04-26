package lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.TherapyProgramBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.TherapyProgramDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.TherapyProgram;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public class TherapyProgramBOImpl implements TherapyProgramBO {
    TherapyProgramDAO therapyProgramDAO = DAOFactory.getInstance().getDAO(DAOFactory.DaoType.THERAPY_PROGRAM);

    @Override
    public String getNextTherapyProgramId() throws SQLException{
        return therapyProgramDAO.getNextID();
    }

    @Override
    public ArrayList<TherapyProgramDTO> getAllTherapyProgram() throws Exception {
        ArrayList<TherapyProgramDTO> therapyProgramDTOS = new ArrayList<>();
        ArrayList<TherapyProgram> therapyPrograms = (ArrayList<TherapyProgram>) therapyProgramDAO.getAll();

        for (TherapyProgram therapyProgram : therapyPrograms) {
            therapyProgramDTOS.add(new TherapyProgramDTO(
                    therapyProgram.getProgramId(),
                    therapyProgram.getName(),
                    therapyProgram.getDuration(),
                    therapyProgram.getFee(),
                    therapyProgram.getTherapist(),
                    therapyProgram.getDescription()
            ));
        }
        return therapyProgramDTOS;
    }

    @Override
    public boolean saveTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws Exception {
        return therapyProgramDAO.save(new TherapyProgram(
                therapyProgramDTO.getProgramId(),
                therapyProgramDTO.getName(),
                therapyProgramDTO.getDuration(),
                therapyProgramDTO.getFee(),
                therapyProgramDTO.getTherapist(),
                therapyProgramDTO.getDescription()
        ));
    }

    @Override
    public boolean updateTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws Exception {
        return therapyProgramDAO.update(new TherapyProgram(
                therapyProgramDTO.getProgramId(),
                therapyProgramDTO.getName(),
                therapyProgramDTO.getDuration(),
                therapyProgramDTO.getFee(),
                therapyProgramDTO.getTherapist(),
                therapyProgramDTO.getDescription()
        ));
    }

    @Override
    public TherapyProgramDTO searchTherapyProgram(String id) throws Exception {
        TherapyProgram therapyProgram = (TherapyProgram) therapyProgramDAO.search(id);

        if (therapyProgram == null) {
            return null;
        }
        return new TherapyProgramDTO(
                therapyProgram.getProgramId(),
                therapyProgram.getName(),
                therapyProgram.getDuration(),
                therapyProgram.getFee(),
                therapyProgram.getTherapist(),
                therapyProgram.getDescription()
        );
    }

    @Override
    public boolean deleteTherapyProgram(String id) throws Exception {
        return therapyProgramDAO.delete(id);
    }

    public void setSession(Session session) throws Exception {
        therapyProgramDAO.setSession(session);
    }

}
