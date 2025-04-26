package lk.ijse.gdse.serenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.BOFactory;
import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.PatientBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.PatientDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.tm.PatientTM;

import java.util.ArrayList;
import java.util.Optional;

public class PatientController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<PatientTM, Integer> colContact;

    @FXML
    private TableColumn<PatientTM , String> colMedicalHistory;

    @FXML
    private TableColumn<PatientTM , String> colName;

    @FXML
    private TableColumn<PatientTM , String> colPatientId;


    @FXML
    private TableColumn<PatientTM, Integer> colAge;

    @FXML
    private ImageView imgSearch;

    @FXML
    private TableView<PatientTM> tblPatients;

    @FXML
    private JFXTextField txtMedicalHistory;

    @FXML
    private TextField txtPatientContact;

    @FXML
    private TextField txtPatientId;

    @FXML
    private JFXTextField txtAge;

    @FXML
    private TextField txtPatientName;

    private final PatientBO PATIENTBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);

    public void initialize() {
        try {
            loadTableData();
            visibleData();
            refreshPage();

            String nextPatientId = PATIENTBO.getNextPatientId();
            txtPatientId.setText(nextPatientId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void loadTableData() throws Exception {
        ArrayList<PatientDTO> patientDTOS = PATIENTBO.getAllPatients();
        ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();

        for (PatientDTO patientDTO : patientDTOS) {
            PatientTM patientTM = new PatientTM(
                    patientDTO.getId(),
                    patientDTO.getName(),
                    patientDTO.getContactNumber(),
                    patientDTO.getAge(),
                    patientDTO.getMedicalHistory()

            );
            patientTMS.add(patientTM);
        }
        tblPatients.setItems(patientTMS);
    }
    private void visibleData() throws Exception {
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colMedicalHistory.setCellValueFactory(new PropertyValueFactory<>("medicalHistory"));
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        PatientTM selectedPatient = tblPatients.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Deletion");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Are you sure you want to delete patient ID: " + selectedPatient.getId() + "?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    boolean isDeleted = PATIENTBO.deletePatient(selectedPatient.getId());

                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Patient deleted successfully!").show();
                        loadTableData();
                        refreshPage();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete the patient!").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a patient to delete.").show();
        }
    }

    public void refreshPage(){
        txtPatientId.setText("");
        txtPatientName.setText("");
        txtPatientContact.setText("");
        txtAge.setText("");
        txtMedicalHistory.setText("");
    }
    @FXML
    void refreshOnAction(ActionEvent event) {
        try {
            refreshPage();
            loadTableData();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        try {
            String patientId = txtPatientId.getText();
            String name = txtPatientName.getText();
            String contactStr = txtPatientContact.getText();
            String ageStr = txtAge.getText();
            String medicalHistory = txtMedicalHistory.getText();

            // === REGEX PATTERNS ===
            String nameRegex = "^[A-Za-z ]{2,50}$";
            String contactRegex = "^(070|071|072|074|075|076|077|078)\\d{7}$"; // Sri Lankan format
            String ageRegex = "^[1-9][0-9]{0,2}$";

            // === VALIDATE ===
            if (!name.matches(nameRegex)) {
                new Alert(Alert.AlertType.WARNING, "Invalid name! Only letters & spaces allowed (2–50 characters).").show();
                return;
            }

            if (!contactStr.matches(contactRegex)) {
                new Alert(Alert.AlertType.WARNING, "Invalid contact number! Must be a valid Sri Lankan mobile number (e.g., 0771234567).").show();
                return;
            }

            if (!ageStr.matches(ageRegex)) {
                new Alert(Alert.AlertType.WARNING, "Invalid age! Enter a number between 1 and 999.").show();
                return;
            }

            int contact = Integer.parseInt(contactStr);
            int age = Integer.parseInt(ageStr);

            if (!patientId.isEmpty() && !medicalHistory.isEmpty()) {
                PatientDTO patientDTO = new PatientDTO(patientId, name, contact, age, medicalHistory);
                boolean isSaved = PATIENTBO.savePatient(patientDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Patient Saved Successfully!").show();
                    loadTableData();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to Save Patient!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please fill all the fields!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void searchOnAction(MouseEvent event) {
        String patientId = txtPatientId.getText();

        if (!patientId.isEmpty()) {
            try {
                PatientDTO patientDTO = PATIENTBO.searchPatient(patientId);

                if (patientDTO != null) {
                    txtPatientName.setText(patientDTO.getName());
                    txtPatientContact.setText(String.valueOf(patientDTO.getContactNumber()));
                    txtAge.setText(String.valueOf(patientDTO.getAge()));
                    txtMedicalHistory.setText(patientDTO.getMedicalHistory());

                    ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();

                    PatientTM patientTM = new PatientTM(
                            patientDTO.getId(),
                            patientDTO.getName(),
                            patientDTO.getContactNumber(),
                            patientDTO.getAge(),
                            patientDTO.getMedicalHistory()
                    );
                    patientTMS.add(patientTM);
                    tblPatients.setItems(patientTMS);
                } else {
                    new Alert(Alert.AlertType.WARNING, "Patient Not Found!").show();
                }
            } catch (Exception e) {
//                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while searching!").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please enter a Patient ID to search!").show();
        }
    }

    @FXML
    void tableClickOnAction(MouseEvent event) {
        PatientTM selectedPatient = tblPatients.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            txtPatientId.setText(selectedPatient.getId());
            txtPatientName.setText(selectedPatient.getName());
            txtPatientContact.setText(String.valueOf(selectedPatient.getContactNumber()));
            txtAge.setText(String.valueOf(selectedPatient.getAge()));
            txtMedicalHistory.setText(selectedPatient.getMedicalHistory());
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        try {
            String patientId = txtPatientId.getText();
            String name = txtPatientName.getText();
            String contactStr = txtPatientContact.getText();
            String ageStr = txtAge.getText();
            String medicalHistory = txtMedicalHistory.getText();

            // === REGEX PATTERNS ===
            String nameRegex = "^[A-Za-z ]{2,50}$";
            String contactRegex = "^(070|071|072|074|075|076|077|078)\\d{7}$"; // Sri Lankan format
            String ageRegex = "^[1-9][0-9]{0,2}$";

            // === VALIDATION ===
            if (!name.matches(nameRegex)) {
                new Alert(Alert.AlertType.WARNING, "Invalid name! Only letters & spaces allowed (2–50 characters).").show();
                return;
            }

            if (!contactStr.matches(contactRegex)) {
                new Alert(Alert.AlertType.WARNING, "Invalid contact number! Must be a valid Sri Lankan number (e.g., 0771234567).").show();
                return;
            }

            if (!ageStr.matches(ageRegex)) {
                new Alert(Alert.AlertType.WARNING, "Invalid age! Enter a number between 1 and 999.").show();
                return;
            }

            int contact = Integer.parseInt(contactStr);
            int age = Integer.parseInt(ageStr);

            if (!patientId.isEmpty() && !medicalHistory.isEmpty()) {
                PatientDTO patientDTO = new PatientDTO(patientId, name, contact, age, medicalHistory);
                boolean isUpdated = PATIENTBO.updatePatient(patientDTO);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Patient updated successfully!").show();
                    loadTableData();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update patient!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please fill all fields!").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

}
