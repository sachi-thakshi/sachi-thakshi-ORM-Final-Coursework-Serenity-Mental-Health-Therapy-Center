package lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.PatientBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.PatientDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.PatientDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.Patient;

import java.util.ArrayList;

public class PatientBOImpl implements PatientBO {

    PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOFactory.DaoType.PATIENT);

    @Override
    public ArrayList<PatientDTO> getAllPatients() throws Exception {
        ArrayList<PatientDTO> patientDTOS = new ArrayList<>();
        ArrayList<Patient> patients = (ArrayList<Patient>) patientDAO.getAll();

        for (Patient patient : patients) {
            patientDTOS.add(new PatientDTO(
                    patient.getId(),
                    patient.getName(),
                    patient.getContactNumber(),
                    patient.getAge(),
                    patient.getMedicalHistory()

            ));
        }
        return patientDTOS;
    }

    @Override
    public boolean savePatient(PatientDTO patientDTO) throws Exception {
        return patientDAO.save(new Patient(
               patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getMedicalHistory(),
                patientDTO.getContactNumber(),
                patientDTO.getAge()
        ));
    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) throws Exception {
        return patientDAO.update(new Patient(
                patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getMedicalHistory(),
                patientDTO.getContactNumber(),
                patientDTO.getAge()
        ));
    }

    @Override
    public boolean deletePatient(String patientId) throws Exception {
        return patientDAO.delete(patientId);
    }

    @Override
    public String getNextPatientId() throws Exception {
        return patientDAO.getNextPatientId();
    }

    @Override
    public PatientDTO searchPatient(String patientID) throws Exception {
        Patient patient = patientDAO.search(patientID);
        if (patient == null) {
            return null;
        }
        return new PatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getContactNumber(),
                patient.getAge(),
                patient.getMedicalHistory()

        );
    }
}
