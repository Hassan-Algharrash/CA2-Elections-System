package Main;

import controllers.ElectionAPI;
import controllers.ElectionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Candidate;
import models.Election;
import models.ElectionType;
import models.Politician;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        ElectionAPI api = new ElectionAPI(); // shared instance


        // example adds so we dont have to keep adding them
        Election e1 = api.addElection(new Election(
                null,
                ElectionType.GENERAL,
                "Dublin",
                2020,
                5
        ));

        Election e2 = api.addElection(new Election(
                null,
                ElectionType.LOCAL,
                "Waterford",
                2024,
                3
        ));

        Election e3 = api.addElection(new Election(
                null,
                ElectionType.PRESIDENTIAL,
                "Cork",
                2023,
                1
        ));

        Politician p1 = api.addPolitician(new Politician(
                null,
                "John Murphy",
                "1980-04-12",
                "Independent",
                "Waterford",
                "https://example.com/john.jpg"
        ));

        Politician p2 = api.addPolitician(new Politician(
                null,
                "Sarah O’Connor",
                "1975-09-30",
                "Green Party",
                "Dublin",
                "https://example.com/sarah.jpg"
        ));

        Politician p3 = api.addPolitician(new Politician(
                null,
                "Michael Byrne",
                "1990-01-15",
                "Labour",
                "Cork",
                "https://example.com/michael.jpg"
        ));

        api.addCandidateToElection(e1.getId(), new Candidate(
                p1,
                e1,
                "Independent",
                1200
        ));

        api.addCandidateToElection(e1.getId(), new Candidate(
                p2,
                e1,
                "Green Party",
                980
        ));

        api.addCandidateToElection(e2.getId(), new Candidate(
                p3,
                e2,
                "Labour",
                450
        ));




        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/elections-system.fxml"));
        Parent root = loader.load();

        // Inject the shared API into the controller
        ElectionController controller = loader.getController();
        controller.setAPI(api);
        controller.loadInitialData();

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