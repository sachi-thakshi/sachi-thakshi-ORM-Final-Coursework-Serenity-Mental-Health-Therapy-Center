package lk.ijse.gdse.serenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class UserController {

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
    private JFXComboBox<?> cmbUserRole;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private TableView<?> tblUsers;

    @FXML
    private JFXTextField txtUserId;

    @FXML
    private JFXTextField txtUsername;

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
    void tableClickOnAction(MouseEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

}
