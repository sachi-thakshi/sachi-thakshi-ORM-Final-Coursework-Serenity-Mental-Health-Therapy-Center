package lk.ijse.gdse.serenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.BOFactory;
import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.UserBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.UserDTO;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private Button btnSingIn;

    @FXML
    private ImageView iconHide;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblUserName1;

    @FXML
    private ImageView lblpasswordEye;

    @FXML
    private Hyperlink linkFogetPassword;

    @FXML
    private Hyperlink linkSignIn;

    @FXML
    private AnchorPane mainAnchorPane;


    @FXML
    private JFXTextField txtPassword;

    @FXML
    private TextField txtPassword1;

    @FXML
    private TextField txtUserName;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @FXML
    void enterPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void fogetPassword(ActionEvent event) {

    }

    @FXML
    void showHide(MouseEvent event) {

    }

    @FXML
    void signIn(ActionEvent event) {
        String Name = txtUserName.getText();
        String Password = txtPassword.getText();

        if (Name.isEmpty() || Password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill youer data").show();
        }

        try {
            boolean isAuthenticated = userBO.authenticateUser(Name, Password);
            if (isAuthenticated) {
                UserDTO user = userBO.getUserByUsername(Name);
                navigateToDashboard(user.getRole());
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid username or password!").show();
            }
        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    private void navigateToDashboard(String role) {
        try {
            String fxmlPath;
            String title;

            if ("ADMIN".equalsIgnoreCase(role)) {
                fxmlPath = "/view/AdminDashboard.fxml";
                title = "Owner Dashboard";
            } else if ("RECEPTIONIST".equalsIgnoreCase(role)) {
                fxmlPath = "/view/ReceptionistDashboard.fxml";
                title = "Receptionist Dashboard";
            } else {
                new Alert(Alert.AlertType.ERROR, "Unauthorized role").show();
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnSingIn.getScene().getWindow();

            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.setTitle(title);
            currentStage.centerOnScreen();
            currentStage.show();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load dashboard").show();
            e.printStackTrace();
        }
    }

    @FXML
    void signUp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RegistrationPage.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnSingIn.getScene().getWindow();

            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.centerOnScreen();
            currentStage.show();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            e.printStackTrace();
        }
    }

}
