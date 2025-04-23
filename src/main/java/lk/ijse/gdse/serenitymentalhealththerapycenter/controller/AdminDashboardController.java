package lk.ijse.gdse.serenitymentalhealththerapycenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnPaymentView;

    @FXML
    private Button btnTherapyProgramView;

    @FXML
    private Button btnUserView;

    @FXML
    private Button btntherapySessionView;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Pane pasetedReceptionistPane;

    @FXML
    private Button therapistView;

    @FXML
    void logOut(ActionEvent event) {
        navigateToPage("/view/LoginPage.fxml");
    }

    @FXML
    void paymentView(ActionEvent event) {
        navigateToPage("/view/PaymentPage.fxml");
    }

    @FXML
    void therapistView(ActionEvent event) {
        navigateToPage("/view/Therapist.fxml");
    }

    @FXML
    void therapyProgramView(ActionEvent event) {
        navigateToPage("/view/TherapyProgram.fxml");

    }

    @FXML
    void therapySessionView(ActionEvent event) {
        navigateToPage("/view/TherapySession.fxml");

    }

    @FXML
    void userView(ActionEvent event) {
        navigateToPage("/view/User.fxml");

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
