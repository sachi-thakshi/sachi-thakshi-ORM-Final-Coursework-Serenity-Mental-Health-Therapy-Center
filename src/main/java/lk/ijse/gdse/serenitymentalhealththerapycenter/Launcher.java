package lk.ijse.gdse.serenitymentalhealththerapycenter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
//        stage.getIcons().add(new Image(getClass().getResourceAsStream("/pictures/logo.jpg.png"))); // Open wenakota logo eka penweemt.
        stage.setScene(scene);
        stage.setResizable(false);  // Stage eke size eka change krgn bri kirimat
        stage.initStyle(StageStyle.UNDECORATED);  // Splash Screen eke athi button iwath kirimat
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
