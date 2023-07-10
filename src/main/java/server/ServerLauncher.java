package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerLauncher extends Application {
    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("view/server_form.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("Sever");
        stage.centerOnScreen();

        stage.show();
    }

}
