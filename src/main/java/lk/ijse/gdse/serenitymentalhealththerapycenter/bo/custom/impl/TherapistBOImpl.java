package lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.TherapistBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.TherapistDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.TherapistDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.Therapist;

import java.util.ArrayList;

public class TherapistBOImpl implements TherapistBO {

    private final TherapistDAO therapistDAO = DAOFactory.getInstance().getDAO(DAOFactory.DaoType.THERAPIST);

    @Override
    public TherapistDTO searchTherapist(String id) throws Exception {
        Therapist therapist = therapistDAO.search(id);

        if (therapist == null) {
            return null;
        }
        return new TherapistDTO(
                therapist.getTherapistId(),
                therapist.getName(),
                therapist.getSpecialization(),
                therapist.getAvailability(),
                therapist.getContactNumber(),
                therapist.getAssignedProgram()
        );
    }

    @Override
    public boolean deleteTherapist(String id) throws Exception {
        return therapistDAO.delete(id);
    }

    @Override
    public boolean updateTherapist(TherapistDTO therapistDTO) throws Exception {
        return therapistDAO.update(new Therapist(
                therapistDTO.getTherapistId(),
                therapistDTO.getName(),
                therapistDTO.getSpecialization(),
                therapistDTO.getAvailability(),
                therapistDTO.getContactNumber(),
                therapistDTO.getAssignedProgram()
        ));
    }

    @Override
    public boolean saveTherapist(TherapistDTO therapistDTO) throws Exception {
        return therapistDAO.save( new Therapist(
                therapistDTO.getTherapistId(),
                therapistDTO.getName(),
                therapistDTO.getSpecialization(),
                therapistDTO.getAvailability(),
                therapistDTO.getContactNumber(),
                therapistDTO.getAssignedProgram()));
    }

    @Override
    public ArrayList<TherapistDTO> getAllTherapist() throws Exception {
        ArrayList<TherapistDTO> therapistDTOS = new ArrayList<>();
        ArrayList<Therapist> therapists = (ArrayList<Therapist>) therapistDAO.getAll();

        for (Therapist therapist : therapists) {
            therapistDTOS.add(new TherapistDTO(
                    therapist.getTherapistId(),
                    therapist.getName(),
                    therapist.getSpecialization(),
                    therapist.getAvailability(),
                    therapist.getContactNumber(),
                    therapist.getAssignedProgram()
            ));
        }
        return therapistDTOS;
    }

    @Override
    public String getNextTherapistId() throws Exception {
        return therapistDAO.getNextID();
    }
}
