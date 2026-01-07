package Main;

import controllers.ElectionAPI;
import controllers.ElectionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        ElectionAPI api = new ElectionAPI(); // shared instance

        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/elections-system.fxml"));
        Parent root = loader.load();

        // Inject the shared API into the controller
        ElectionController controller = loader.getController();
        controller.setAPI(api);

        Scene scene = new Scene(root, 920, 580);
        stage.setTitle("Elections Information System");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}