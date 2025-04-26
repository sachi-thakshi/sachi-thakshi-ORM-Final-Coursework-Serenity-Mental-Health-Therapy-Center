package lk.ijse.gdse.serenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.BOFactory;
import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.PaymentBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.TherapySessionDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.impl.TherapySessionDAOImpl;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.PaymentDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.TherapySessionDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.tm.PaymentTM;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

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
    private ComboBox<String> cmbPaymentMethod;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private TableColumn<PaymentTM, BigDecimal> colAmount;

    @FXML
    private TableColumn<PaymentTM, BigDecimal> colBalance;

    @FXML
    private TableColumn<PaymentTM, LocalDate> colDate;

    @FXML
    private TableColumn<PaymentTM, String> colPatient;

    @FXML
    private TableColumn<PaymentTM, BigDecimal> colPayedAmound;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentId;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentMethod;

    @FXML
    private TableColumn<PaymentTM, String> colSessionId;

    @FXML
    private TableColumn<PaymentTM, String> colStatus;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<PaymentTM> tblAppointments;

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


    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);

    public void initialize() {
        try {
            ShowComboBoxDetails();
            refreshPage();
            loadTableData();
            visibleData();

            String nextTherapistId = paymentBO.getNextPaymentId();
            txtPaymentId.setText(nextTherapistId);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Id Genaration Failed: " + e.getMessage()).show();
        }
    }

    private void refreshPage() throws Exception {
        String nextTherapistId = paymentBO.getNextPaymentId();
        txtPaymentId.setText(nextTherapistId);
        txtSessionId.setText("");
        txtPatientName.setText("");
        txtAmount.setText("");
        cmbPaymentMethod.setValue(null);
        cmbStatus.setValue(null);
        txtBalance.setText("");
        txtPayedAmound.setText("");
        datePicker.setValue(null);
    }

    private void loadTableData() throws Exception {
        ArrayList<PaymentDTO> paymentDTOS = paymentBO.getAllPayments();
        ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();

        for (PaymentDTO paymentDTO : paymentDTOS) {
            PaymentTM paymentTM = new PaymentTM(
                    paymentDTO.getId(),
                    paymentDTO.getSessionId(),
                    paymentDTO.getPatientName(),
                    paymentDTO.getAmount(),
                    paymentDTO.getPaymentMethod(),
                    paymentDTO.getPaymentDate(),
                    paymentDTO.getStatus(),
                    paymentDTO.getPaidAmount(),
                    paymentDTO.getBalance()
            );
            paymentTMS.add(paymentTM);
        }
        tblAppointments.setItems(paymentTMS);
    }

    private void ShowComboBoxDetails() throws SQLException {

        ObservableList<String> methods = FXCollections.observableArrayList(
                "Cash",
                "Credit/Debit Card",
                "Bank Transfer",
                "Cheque"
        );

        cmbPaymentMethod.setItems(methods);

        ObservableList<String> status = FXCollections.observableArrayList(
                "Pending", "Completed", "Failed",
                "Refunded"
        );

        cmbStatus.setItems(status);
    }

    private void visibleData() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPayedAmound.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
    }

    @FXML
    void cancelOnAction(ActionEvent event) {
        PaymentTM selectedPayment = tblAppointments.getSelectionModel().getSelectedItem();

        if (selectedPayment != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Deletion");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Are you sure you want to Cansel Payment : " + selectedPayment.getId() + "?");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    boolean isDeleted = paymentBO.deletePayment(selectedPayment.getId());

                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Payment deleted successfully!").show();
                        loadTableData();
                        refreshPage();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete the Payemnt!").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a Payment to delete.").show();
        }
    }

    @FXML
    void generateBillOnAction(ActionEvent event) {

    }

    @FXML
    void loadOnAction(ActionEvent event) {
        try {
            String selectedSessionId = txtSessionId.getText();

            if (selectedSessionId == null || selectedSessionId.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please select a session ID first.").show();
                return;
            }

            System.out.println("Selected Session ID: " + selectedSessionId);

            TherapySessionDAO therapySessionDAO = new TherapySessionDAOImpl();
            TherapySessionDTO session = therapySessionDAO.getSession(selectedSessionId);

            if (session != null) {
                txtPatientName.setText(session.getPatientName());
            } else {
                new Alert(Alert.AlertType.WARNING, "No Session found!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load patient: " + e.getMessage()).show();
        }
    }

    @FXML
    void payOnAction(ActionEvent event) {
        try{
            String paymentId = txtPaymentId.getText();
            String sessionId = txtSessionId.getText();
            String patientName = txtPatientName.getText();
            BigDecimal amount = new BigDecimal(txtAmount.getText());
            String paymentMethod = cmbPaymentMethod.getValue();
            LocalDate paymentDate = datePicker.getValue();
            String status = cmbStatus.getValue();
            BigDecimal paidAmount = new BigDecimal(txtPayedAmound.getText());
            BigDecimal balance = new BigDecimal(txtBalance.getText());



            if (!paymentId.isEmpty() && !sessionId.isEmpty() && !patientName.isEmpty() && amount != null  && !paymentMethod.isEmpty() && !status.isEmpty() && paidAmount != null && balance != null && paymentDate != null) {
                PaymentDTO paymentDTO = new PaymentDTO(
                        paymentId,
                        sessionId,
                        patientName,
                        amount,
                        paymentMethod,
                        paymentDate,
                        status,
                        paidAmount,
                        balance

                );

                boolean isSaved = paymentBO.savePayment(paymentDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Payment Process Saved Successfully!").show();
                    loadTableData();
                    refreshPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to Save Payment Process!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please fill all the fields with valid data!").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
    void searchOnAction(ActionEvent event) {
        String paymentId = txtPaymentId.getText();

        if (!paymentId.isEmpty()) {
            try{
                PaymentDTO paymentDTO = paymentBO.searchPayment(paymentId);

                if (paymentDTO !=null) {
                    txtPaymentId.setText(paymentDTO.getId());
                    txtSessionId.setText(paymentDTO.getSessionId());
                    txtPatientName.setText(paymentDTO.getPatientName());
                    txtAmount.setText(String.valueOf(paymentDTO.getAmount()));
                    cmbStatus.setValue(paymentDTO.getStatus());
                    cmbPaymentMethod.setValue(paymentDTO.getPaymentMethod());
                    datePicker.setValue(paymentDTO.getPaymentDate());
                    txtBalance.setText(String.valueOf(paymentDTO.getBalance()));
                    txtPayedAmound.setText(String.valueOf(paymentDTO.getPaidAmount()));

                    ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();

                    PaymentTM paymentTM = new PaymentTM(
                            paymentDTO.getId(),
                            paymentDTO.getSessionId(),
                            paymentDTO.getPatientName(),
                            paymentDTO.getAmount(),
                            paymentDTO.getPaymentMethod(),
                            paymentDTO.getPaymentDate(),
                            paymentDTO.getStatus(),
                            paymentDTO.getPaidAmount(),
                            paymentDTO.getBalance()
                    );
                    paymentTMS.add(paymentTM);
                    tblAppointments.setItems(paymentTMS);
                } else {
                    new Alert(Alert.AlertType.WARNING, "Payment Not Found!").show();
                }
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "An error occurred while searching!").show();            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please enter a Payment ID to search!").show();
        }
    }

    @FXML
    void calOnAction(ActionEvent event) {
        try {
            if (txtAmount.getText().isEmpty() || txtPayedAmound.getText().isEmpty()) {
                throw new IllegalArgumentException("Amount and paid amount cannot be empty.");
            }

            BigDecimal amount = new BigDecimal(txtAmount.getText().trim());
            BigDecimal paidAmount = new BigDecimal(txtPayedAmound.getText().trim());

            if (amount.compareTo(BigDecimal.ZERO) < 0 || paidAmount.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Values must be positive.");
            }

            BigDecimal balance = paidAmount.subtract(amount); // balance = change to return

            if (balance.compareTo(BigDecimal.ZERO) < 0) {
                // Not enough payment
                txtBalance.setText("0.00");
                new Alert(Alert.AlertType.WARNING,
                        "Not enough payment. You still owe: Rs. " +
                                balance.abs().setScale(2, RoundingMode.HALF_UP)
                ).show();
            } else {
                // Paid enough or overpaid – show the balance (change)
                txtBalance.setText(balance.setScale(2, RoundingMode.HALF_UP).toString());
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid number format. Enter numeric values only.").show();
        } catch (IllegalArgumentException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


}
