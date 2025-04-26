package lk.ijse.gdse.serenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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

    }

    @FXML
    void sendCodeOnAction(ActionEvent event) {

    }

    @FXML
    void submitButton(ActionEvent event) {

    }

}
