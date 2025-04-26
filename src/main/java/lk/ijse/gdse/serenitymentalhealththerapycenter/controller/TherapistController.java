package lk.ijse.gdse.serenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.BOFactory;
import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.TherapistBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.TherapistDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.tm.TherapistTM;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.Therapist;

import java.util.ArrayList;
import java.util.Optional;

public class TherapistController {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnRefresh;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox<String> cmbAssignedProgram;

    @FXML
    private JFXComboBox<String> cmbAvailability;

    @FXML
    private JFXComboBox<String> cmbSpecialization;

    @FXML
    private TableColumn<TherapistTM, String> colAssignedProgram;

    @FXML
    private TableColumn<TherapistTM, Integer> colContact;

    @FXML
    private TableColumn<TherapistTM, String> colName;

    @FXML
    private TableColumn<TherapistTM, String> colSpecialization;

    @FXML
    private TableColumn<TherapistTM, String> colAvailability;

    @FXML
    private TableColumn<TherapistTM, String> colTherapistId;

    @FXML
    private AnchorPane mainAnchoPane;

    @FXML
    private TableView<TherapistTM> therapistTable;

    @FXML
    private JFXTextField txtTherapistContact;

    @FXML
    private JFXTextField txtTherapistId;

    @FXML
    private JFXTextField txtTherapistName;

    private final TherapistBO therapistBO = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);

    public void initialize() {
        try {
            ShowComboBoxDetails();
            refreshPage();
            loadTableData();
            visibleData();
            changeFocus();

            String nextTherapistId = therapistBO.getNextTherapistId();
            txtTherapistId.setText(nextTherapistId);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Id Genaration Failed: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    public void changeFocus() {

        txtTherapistId.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                txtTherapistName.requestFocus();
            }
        });

        txtTherapistName.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                cmbSpecialization.requestFocus();
            }
        });

        cmbSpecialization.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                cmbAvailability.requestFocus();
            }
        });

        cmbAvailability.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                txtTherapistContact.requestFocus();
            }
        });

        txtTherapistContact.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                cmbAssignedProgram.requestFocus();
            }
        });

        cmbAssignedProgram.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                btnSave.fire();
            }
        });
    }

    public void refreshPage() {
        try{
            String nextTherapistId = therapistBO.getNextTherapistId();
            txtTherapistId.setText(nextTherapistId);
            txtTherapistName.setText("");
            txtTherapistContact.setText("");
            cmbAvailability.setValue(null);
            cmbSpecialization.setValue(null);
            cmbAssignedProgram.setValue(null);
        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Id Genaration Failed:" + e.getMessage()).show();
        }
    }

    public void loadTableData() throws Exception {
        ArrayList<TherapistDTO> therapistDTOS = therapistBO.getAllTherapist();
        ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();

        for (TherapistDTO therapistDTO : therapistDTOS) {
            TherapistTM therapistTM = new TherapistTM(
                    therapistDTO.getTherapistId(),
                    therapistDTO.getName(),
                    therapistDTO.getSpecialization(),
                    therapistDTO.getAvailability(),
                    therapistDTO.getContactNumber(),
                    therapistDTO.getAssignedProgram());
            therapistTMS.add(therapistTM);
        }
        therapistTable.setItems(therapistTMS);
    }
    public void visibleData() {
        colTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colAssignedProgram.setCellValueFactory(new PropertyValueFactory<>("assignedProgram"));
    }

    public void ShowComboBoxDetails() {
        ObservableList<String> availability = FXCollections.observableArrayList(
                "Available",
                "Unavailable",
                "On Leave",
                "Busy"
        );

        cmbAvailability.setItems(availability);

        ObservableList<String> programes = FXCollections.observableArrayList(
                "Cognitive Behavioral Therapy", "Mindfulness-Based Stress Reduction" ,
                "Dialectical Behavior Therapy" , "Group Therapy Sessions" , "Family Counseling"
        );

        cmbAssignedProgram.setItems(programes);

        ObservableList<String> specialization = FXCollections.observableArrayList(
                "Child Psychology",
                "Depression & Anxiety",
                "Addiction Recovery",
                "Trauma Therapy",
                "Relationship Counseling",
                "PTSD Specialist"
        );

        cmbSpecialization.setItems(specialization);
    }

    @FXML
    void clickOnAction(MouseEvent event) {
        TherapistTM selectedTherapist = therapistTable.getSelectionModel().getSelectedItem();

        if (selectedTherapist != null) {
            txtTherapistId.setText(selectedTherapist.getTherapistId());
            txtTherapistName.setText(selectedTherapist.getName());
            cmbSpecialization.setValue(selectedTherapist.getSpecialization());
            cmbAssignedProgram.setValue(selectedTherapist.getAssignedProgram());
            cmbAvailability.setValue(selectedTherapist.getAvailability());
            txtTherapistContact.setText(String.valueOf(selectedTherapist.getContactNumber()));
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        TherapistTM selectedTherapist = therapistTable.getSelectionModel().getSelectedItem();

        if (selectedTherapist != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Deletion");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Are you sure you want to delete Therapist : " + selectedTherapist.getTherapistId() + "?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    boolean isDeleted = therapistBO.deleteTherapist(selectedTherapist.getTherapistId());

                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Therapist deleted successfully!").show();
                        loadTableData();
                        refreshPage();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete the Therapist!").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a Therapist to delete.").show();
        }
    }

    @FXML
    void refreshOnAction(ActionEvent event) {
        try {
            refreshPage();
            loadTableData();
            visibleData();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        try {
            String therapistId = txtTherapistId.getText();
            String therapistName = txtTherapistName.getText();
            String contact = txtTherapistContact.getText();  // Contact number as string
            String specialization = cmbSpecialization.getValue();
            String program = cmbAssignedProgram.getValue();
            String availability = cmbAvailability.getValue();

            // Regex for Sri Lankan phone numbers
            String sriLankaPhoneRegex = "^(?:\\+94|0)(?:7\\d{8}|1\\d{8})$";

            // Validate if the contact number matches the Sri Lankan format
            if (!contact.matches(sriLankaPhoneRegex)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Sri Lankan contact number! Please enter a valid phone number.").show();
                return;
            }

            if (!therapistId.isEmpty() && !therapistName.isEmpty() && specialization != null && program != null && availability != null) {
                // Convert contact to an integer (assuming it's valid after regex check)
                int contactNumber = Integer.parseInt(contact);

                TherapistDTO therapistDTO = new TherapistDTO(therapistId, therapistName, specialization, availability, contactNumber, program);

                boolean isSaved = therapistBO.saveTherapist(therapistDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Therapist Saved Successfully!").show();
                    loadTableData();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to Save Therapist!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please fill all the fields with valid data!").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid numeric value for Contact Number!").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void searchOnAction(ActionEvent event) {
        String therapistId = txtTherapistId.getText();

        if (!therapistId.isEmpty()) {
            try{
                TherapistDTO therapistDTO = therapistBO.searchTherapist(therapistId);

                if (therapistDTO !=null) {
                    txtTherapistName.setText(therapistDTO.getName());
                    txtTherapistContact.setText(String.valueOf(therapistDTO.getContactNumber()));
                    cmbSpecialization.setValue(therapistDTO.getSpecialization());
                    cmbAssignedProgram.setValue(therapistDTO.getAssignedProgram());
                    cmbAvailability.setValue(therapistDTO.getAvailability());

                    ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();

                    TherapistTM therapistTM = new TherapistTM(
                            therapistDTO.getTherapistId(),
                            therapistDTO.getName(),
                            therapistDTO.getSpecialization(),
                            therapistDTO.getAvailability(),
                            therapistDTO.getContactNumber(),
                            therapistDTO.getAssignedProgram());
                    therapistTMS.add(therapistTM);
                    therapistTable.setItems(therapistTMS);
                } else {
                    new Alert(Alert.AlertType.WARNING, "Therapist Not Found!").show();
                }
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "An error occurred while searching!").show();            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please enter a Therapist ID to search!").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        try {
            String therapistId = txtTherapistId.getText();
            String therapistName = txtTherapistName.getText();
            String contact = txtTherapistContact.getText();  // Contact number as string
            String specialization = cmbSpecialization.getValue();
            String program = cmbAssignedProgram.getValue();
            String availability = cmbAvailability.getValue();

            // Regex for Sri Lankan phone numbers
            String sriLankaPhoneRegex = "^(?:\\+94|0)(?:7\\d{8}|1\\d{8})$";

            // Validate if the contact number matches the Sri Lankan format
            if (!contact.matches(sriLankaPhoneRegex)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Sri Lankan contact number! Please enter a valid phone number.").show();
                return;
            }

            if (!therapistId.isEmpty() && !therapistName.isEmpty() && specialization != null && program != null && availability != null) {
                // Convert contact to an integer (assuming it's valid after regex check)
                int contactNumber = Integer.parseInt(contact);

                TherapistDTO therapistDTO = new TherapistDTO(therapistId, therapistName, specialization, availability, contactNumber, program);

                boolean isUpdated = therapistBO.updateTherapist(therapistDTO);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Therapist Updated Successfully!").show();
                    loadTableData();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to Update Therapist!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please fill all the fields with valid data!").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid numeric value for Contact Number!").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void enterOnAction(ActionEvent actionEvent) {
        searchOnAction(actionEvent);
    }
}
