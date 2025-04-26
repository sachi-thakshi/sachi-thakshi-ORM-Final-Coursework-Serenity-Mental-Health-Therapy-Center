package lk.ijse.gdse.serenitymentalhealththerapycenter.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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

public class RegistrationPageController {

    @FXML
    private JFXButton btnCreate;

    @FXML
    private JFXComboBox<String> cmbRole;

    @FXML
    private ImageView iconHide;

    @FXML
    private ImageView iconHide1;

    @FXML
    private JFXButton linkLogIn;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private JFXTextField txtConfirmPassword;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUserId;

    @FXML
    private JFXTextField txtUserName;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    public void initialize() {
        cmbRole.getItems().addAll("ADMIN", "RECEPTIONIST");
    }

    @FXML
    void createAccount(ActionEvent event) {
        try {
            String id = txtUserId.getText();
            String username = txtUserName.getText();
            String password = txtPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();
            String role = cmbRole.getValue();

            if (id.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || role == null) {
                new Alert(Alert.AlertType.ERROR, "Fill all fields!!").show();

                return;
            }

            if (!password.equals(confirmPassword)) {
                new Alert(Alert.AlertType.WARNING, "Passwords do not match!!").show();

                return;
            }

            UserDTO userDTO = new UserDTO(id, username, password, role);
            boolean isSaved = userBO.saveUser(userDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "User registered successfully!").show();

//                clearFields();

                mainAnchorPane.getChildren().clear();
                mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml")));

            } else {
                new Alert(Alert.AlertType.ERROR, "User already exists or registration failed! " ).show();

            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong " + e.getMessage()).show();

            e.printStackTrace();
        }
    }

    @FXML
    void logIn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginPage.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) linkLogIn.getScene().getWindow();

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
