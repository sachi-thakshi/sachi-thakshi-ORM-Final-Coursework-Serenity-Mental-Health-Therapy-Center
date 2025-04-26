package lk.ijse.gdse.serenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PasswordRecoveryPageController {

    @FXML
    private JFXButton btnSendCode;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private AnchorPane fogetPasswordAnchorPane;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ImageView imgPane;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblForgetPassword;

    @FXML
    private Label lblName;

    @FXML
    private Label lblReturn;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void backOnAction(MouseEvent event) {
        try {
            fogetPasswordAnchorPane.getChildren().clear();
            fogetPasswordAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void sendCodeOnAction(ActionEvent event) {

    }

    @FXML
    void submitButton(ActionEvent event) {

    }

}
