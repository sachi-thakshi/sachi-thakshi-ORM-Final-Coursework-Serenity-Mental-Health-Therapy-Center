package lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom;

import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.SuperBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.PatientDTO;

import java.util.ArrayList;

public interface PatientBO extends SuperBO {
    ArrayList<PatientDTO> getAllPatients() throws Exception;
    boolean savePatient(PatientDTO patientDTO) throws Exception;
    boolean updatePatient(PatientDTO patientDTO) throws Exception;
    boolean deletePatient(String patientId) throws Exception;
}
