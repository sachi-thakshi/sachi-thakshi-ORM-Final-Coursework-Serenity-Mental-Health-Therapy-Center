package lk.ijse.gdse.serenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PaymentController {

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnGenerateBill;

    @FXML
    private JFXButton btnLoad;

    @FXML
    private JFXButton btnPay;

    @FXML
    private JFXButton btnRefresh;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private ComboBox<?> cmbPaymentMethod;

    @FXML
    private ComboBox<?> cmbStatus;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPatient;

    @FXML
    private TableColumn<?, ?> colPayedAmound;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPaymentMethod;

    @FXML
    private TableColumn<?, ?> colSessionId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStatus2;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<?> tblAppointments;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXTextField txtBalance;

    @FXML
    private JFXTextField txtPatientName;

    @FXML
    private JFXTextField txtPayedAmound;

    @FXML
    private JFXTextField txtPaymentId;

    @FXML
    private JFXTextField txtSessionId;


    @FXML
    void cancelOnAction(ActionEvent event) {

    }

    @FXML
    void generateBillOnAction(ActionEvent event) {

    }

    @FXML
    void loadOnAction(ActionEvent event) {

    }

    @FXML
    void payOnAction(ActionEvent event) {

    }

    @FXML
    void refreshOnAction(ActionEvent event) {

    }

    @FXML
    void searchOnAction(ActionEvent event) {

    }

}
