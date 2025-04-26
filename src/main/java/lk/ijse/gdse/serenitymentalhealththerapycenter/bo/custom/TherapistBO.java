package lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom;


import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.SuperBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.TherapistDTO;

import java.util.ArrayList;

public interface TherapistBO extends SuperBO {
    TherapistDTO searchTherapist(String id) throws Exception;
    boolean deleteTherapist(String id) throws Exception;
    boolean updateTherapist(TherapistDTO therapistDTO) throws Exception;
    boolean saveTherapist(TherapistDTO therapistDTO) throws Exception;
    ArrayList<TherapistDTO> getAllTherapist() throws Exception;
    String getNextTherapistId() throws Exception;
}
