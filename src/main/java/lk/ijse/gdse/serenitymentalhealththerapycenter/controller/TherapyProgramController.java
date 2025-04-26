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
import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.BOFactory;
import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.TherapyProgramBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.TherapistDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.impl.TherapistDAOImpl;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.TherapyProgramDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.tm.TherapyProgramTM;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class TherapyProgramController {

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
    private JFXComboBox<String> cmbDuration;

    @FXML
    private JFXComboBox<String> cmbProgram;

    @FXML
    private JFXComboBox<String> cmbTherapist;

    @FXML
    private TableColumn<TherapyProgramTM, Integer> colCost;

    @FXML
    private TableColumn<TherapyProgramTM, String> colDescription;

    @FXML
    private TableColumn<TherapyProgramTM, String> colDuration;

    @FXML
    private TableColumn<TherapyProgramTM, String> colName;

    @FXML
    private TableColumn<TherapyProgramTM, String> colProgramId;

    @FXML
    private TableColumn<TherapyProgramTM, String> colTherapists;

    @FXML
    private TableView<TherapyProgramTM> tblPrograms;

    @FXML
    private JFXTextField txtCost;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXTextField txtProgramId;

    private final TherapyProgramBO therapyProgramBO = (TherapyProgramBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPY_PROGRAM);

    public void initialize() {
        try {
            // 1. Create and set the Hibernate session
            Session session = FactoryConfiguration.getInstance().getSession();
            therapyProgramBO.setSession(session); // ✅ Add this line to inject session into DAO

            // 2. Proceed with normal UI setup
            populateComboBoxes();
            refreshPage();
            loadTableData();
            visibleData();
            enterAction();

            // 3. Generate next ID
            String nextProgramId = therapyProgramBO.getNextTherapyProgramId();
            txtProgramId.setText(nextProgramId);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load Page: " + e.getMessage()).show();
            e.printStackTrace(); // helpful for debugging in console
        }
    }


    public void loadTableData() throws Exception {
        ArrayList<TherapyProgramDTO> therapyProgramDTOS = therapyProgramBO.getAllTherapyProgram();
        ObservableList<TherapyProgramTM> therapyProgramTMS = FXCollections.observableArrayList();

        for (TherapyProgramDTO therapyProgramDTO : therapyProgramDTOS) {
            TherapyProgramTM therapyProgramTM = new TherapyProgramTM(
                    therapyProgramDTO.getProgramId(),
                    therapyProgramDTO.getName(),
                    therapyProgramDTO.getDuration(),
                    therapyProgramDTO.getFee(),
                    therapyProgramDTO.getTherapist(),
                    therapyProgramDTO.getDescription()
            );
            therapyProgramTMS.add(therapyProgramTM);
        }
        tblPrograms.setItems(therapyProgramTMS);
    }

    public void populateComboBoxes(){
        ObservableList<String> programes = FXCollections.observableArrayList(
                "Cognitive Behavioral Therapy", "Mindfulness-Based Stress Reduction" ,
                "Dialectical Behavior Therapy" , "Group Therapy Sessions" , "Family Counseling"
        );

        cmbProgram.setItems(programes);

        cmbDuration.setItems(FXCollections.observableArrayList(
                "15 mins", "30 mins", "45 mins", "1 hour", "1 hour 30 mins", "2 hours"
        ));

        TherapistDAO therapistDAO = new TherapistDAOImpl();

        ArrayList<String> therapistIds = therapistDAO.getTherapist();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(therapistIds);
        cmbTherapist.setItems(observableList);
    }

    public void visibleData() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colTherapists.setCellValueFactory(new PropertyValueFactory<>("therapist"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    public void refreshPage() {

        String nextProgramId = null;
        try {
            nextProgramId = therapyProgramBO.getNextTherapyProgramId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        txtProgramId.setText(nextProgramId);
        cmbProgram.setValue(null);
        txtDesc.setText("");
        txtCost.setText("");
        cmbDuration.setValue(null);
        cmbTherapist.setValue(null);
    }

    public void enterAction() {

        cmbProgram.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                cmbDuration.requestFocus();
            }
        });

        cmbDuration.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                txtCost.requestFocus();
            }
        });

        txtCost.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                cmbTherapist.requestFocus();
            }
        });

        cmbTherapist.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                txtDesc.requestFocus();
            }
        });

        txtDesc.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                btnSave.fire();
            }
        });
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        TherapyProgramTM selectedTherapyProgram = tblPrograms.getSelectionModel().getSelectedItem();

        if (selectedTherapyProgram != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Deletion");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Are you sure you want to delete patient ID: " + selectedTherapyProgram.getProgramId() + "?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    boolean isDeleted = therapyProgramBO.deleteTherapyProgram(selectedTherapyProgram.getProgramId());

                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Therapy Program deleted successfully!").show();
                        loadTableData();
                        refreshPage();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete the Therapy Program!").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a Therapy Program to delete.").show();
        }
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
        try{
            String programId = txtProgramId.getText();
            String programName = cmbProgram.getValue();
            String duration = cmbDuration.getValue();
            BigDecimal fee = new BigDecimal(txtCost.getText());
            String description = txtDesc.getText();
            String therapistId = cmbTherapist.getValue();

            if (!programId.isEmpty() && !programName.isEmpty() && !duration.isEmpty() && fee != null  && !description.isEmpty() && !therapistId.isEmpty()) {
                TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(
                        programId,
                        programName,
                        duration,
                        fee,
                        therapistId,
                        description

                );

                boolean isSaved = therapyProgramBO.saveTherapyProgram(therapyProgramDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Therapy_Program Saved Successfully!").show();
                    loadTableData();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to Save Therapy_Program!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please fill all the fields with valid data!").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid numeric values for Age and Contact Number!").show();
        }
    }

    @FXML
    void searchOnAction(ActionEvent event) {
        String therapyProgramId = txtProgramId.getText();

        if (!therapyProgramId.isEmpty()) {
            try {
                TherapyProgramDTO therapyProgramDTO = therapyProgramBO.searchTherapyProgram(therapyProgramId);

                if (therapyProgramDTO != null) {
                    txtProgramId.setText(therapyProgramDTO.getProgramId());
                    cmbProgram.setValue(therapyProgramDTO.getName());
                    cmbDuration.setValue(therapyProgramDTO.getDuration());
                    txtCost.setText(therapyProgramDTO.getFee().toString());
                    txtDesc.setText(therapyProgramDTO.getDescription());
                    cmbTherapist.setValue(therapyProgramDTO.getTherapist());

                    ObservableList<TherapyProgramTM> therapyProgramTMS = FXCollections.observableArrayList();

                    TherapyProgramTM therapyProgramTM = new TherapyProgramTM(
                            therapyProgramDTO.getProgramId(),
                            therapyProgramDTO.getName(),
                            therapyProgramDTO.getDuration(),
                            therapyProgramDTO.getFee(),
                            therapyProgramDTO.getTherapist(),
                            therapyProgramDTO.getDescription()
                    );

                    therapyProgramTMS.add(therapyProgramTM);
                    tblPrograms.setItems(therapyProgramTMS);

                } else {
                    new Alert(Alert.AlertType.WARNING, "Therapy _ Program Not Found!").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "An error occurred while searching!").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please enter a Therapy_Programm ID to search!").show();
        }
    }

    @FXML
    void tableClickOnAction(MouseEvent event) {
        TherapyProgramTM selectedTherapyProgram = tblPrograms.getSelectionModel().getSelectedItem();

        if (selectedTherapyProgram != null) {
            txtProgramId.setText(selectedTherapyProgram.getProgramId());
            cmbProgram.setValue(selectedTherapyProgram.getName());
            txtDesc.setText(selectedTherapyProgram.getDescription());
            cmbDuration.setValue(selectedTherapyProgram.getDuration());
            cmbTherapist.setValue(selectedTherapyProgram.getTherapist());
            txtCost.setText(String.valueOf(selectedTherapyProgram.getFee()));
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        try{
            String programId = txtProgramId.getText();
            String programName = cmbProgram.getValue();
            String duration = cmbDuration.getValue();
            BigDecimal fee = new BigDecimal(txtCost.getText());
            String description = txtDesc.getText();
            String therapistId = cmbTherapist.getValue();

            if (!programId.isEmpty() && !programName.isEmpty() && !duration.isEmpty() && fee != null  && !description.isEmpty() && !therapistId.isEmpty()) {
                TherapyProgramDTO therapyProgramDTO = new TherapyProgramDTO(
                        programId,
                        programName,
                        duration,
                        fee,
                        therapistId,
                        description
                );

                boolean isUpdated = therapyProgramBO.updateTherapyProgram(therapyProgramDTO);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Therapy_Program Update Successfully!").show();
                    loadTableData();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to Update Therapy_Program!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please fill all the fields with valid data!").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid numeric values for Age and Contact Number!").show();
        }
    }

}
