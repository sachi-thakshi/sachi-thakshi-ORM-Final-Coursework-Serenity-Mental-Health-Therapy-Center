package lk.ijse.gdse.serenitymentalhealththerapycenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TherapyProgramController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colTherapists;

    @FXML
    private ImageView imgSearch;

    @FXML
    private TableView<?> tblPrograms;

    @FXML
    private TextField txtCost;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtProgramId;

    @FXML
    private TextField txtProgramName;

    @FXML
    private TextField txtTherapist;

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
    void searchOnAction(MouseEvent event) {

    }

    @FXML
    void tableClickOnAction(MouseEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

}
