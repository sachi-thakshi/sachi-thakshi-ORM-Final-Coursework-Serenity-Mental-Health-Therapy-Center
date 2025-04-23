package lk.ijse.gdse.serenitymentalhealththerapycenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ReceptionistDashvoardController {

    @FXML
    private Button btnEmployeeVitherapySessionViewew;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnPatientView;

    @FXML
    private Button btnPaymentView;

    @FXML
    private Button btnTherapyProgramView;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Pane pasetedReceptionistPane;

    @FXML
    void logOut(ActionEvent event) {

    }

    @FXML
    void patientView(ActionEvent event) {
        navigateToPage("/view/Patient.fxml");
    }

    @FXML
    void paymentView(ActionEvent event) {
        navigateToPage("/view/Payment.fxml");

    }

    @FXML
    void therapyProgramView(ActionEvent event) {
        navigateToPage("/view/TherapyProgram.fxml");

    }

    @FXML
    void therapySessionView(ActionEvent event) {
        navigateToPage("/view/TherapySession.fxml");

    }
    void navigateToPage(String fxml){
        try {
            pasetedReceptionistPane.getChildren().clear();
            pasetedReceptionistPane.getChildren().add(FXMLLoader.load(getClass().getResource(fxml)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
