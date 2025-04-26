package lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom;

import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.CrudDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.PatientDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.Patient;

public interface PatientDAO extends CrudDAO<Patient, String> {
    PatientDTO getPatient(String patientId) ;

    String getNextPatientId();
}
