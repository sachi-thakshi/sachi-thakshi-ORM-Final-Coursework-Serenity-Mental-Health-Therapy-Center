package lk.ijse.gdse.serenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    private JFXComboBox<?> cmbAssignedProgram;

    @FXML
    private JFXComboBox<?> cmbAvailability;

    @FXML
    private JFXComboBox<?> cmbSpecialization;

    @FXML
    private TableColumn<?, ?> colAssignedProgram;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSpecialization;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTherapistId;

    @FXML
    private AnchorPane mainAnchoPane;

    @FXML
    private TableView<?> therapistTable;

    @FXML
    private JFXTextField txtTherapistContact;

    @FXML
    private JFXTextField txtTherapistGmail;

    @FXML
    private JFXTextField txtTherapistId;

    @FXML
    private JFXTextField txtTherapistName;

    @FXML
    void clickOnAction(MouseEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void refreshOnAction(ActionEvent event) {

    }

    @FXML
    void saveOnAction(ActionEvent event) {

    }

    @FXML
    void searchOnAction(ActionEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

}
