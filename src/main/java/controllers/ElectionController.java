package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import models.Election;
import models.ElectionType;
import models.IdGenerator;

import java.net.URL;
import java.util.ResourceBundle;

public class ElectionController implements Initializable {
    private ElectionAPI electionAPI;

    @FXML
    private ListView<Election> electionListView, politicianListView, candidateListView;

    public ElectionController() {
        this.electionAPI = new ElectionAPI();
    }

    @FXML
    private void addElection() {
        ChoiceDialog<ElectionType> inputElectionType = new ChoiceDialog<>();
        inputElectionType.setTitle("Add Election");
        inputElectionType.setHeaderText("Choose Election Type");
        inputElectionType.setContentText("Election Type:");
        inputElectionType.getItems().addAll(ElectionType.values());
        ElectionType electionType = inputElectionType.showAndWait().orElse(null);

        TextInputDialog inputLocation = new TextInputDialog();
        inputLocation.setTitle("Add Election");
        inputLocation.setHeaderText("Enter Election Location");
        inputLocation.setContentText("Location:");
        String location = inputLocation.showAndWait().orElse("");

        TextInputDialog inputYear = new TextInputDialog();
        inputYear.setTitle("Add Election");
        inputYear.setHeaderText("Enter Election Year");
        inputYear.setContentText("Year:");
        int year = Integer.parseInt(inputYear.showAndWait().orElse(""));

        TextInputDialog inputSeats = new TextInputDialog();
        inputSeats.setTitle("Add Election");
        inputSeats.setHeaderText("Enter Election Seats");
        inputSeats.setContentText("Seats:");
        int seats = Integer.parseInt(inputSeats.showAndWait().orElse(""));

        Election election = electionAPI.addElection(new Election(IdGenerator.newElectionId(), electionType, location, year, seats));

        if (election != null) {
            electionListView.getItems().add(election);
        }
    }

    @FXML
    private void editElection() {
        Election election = electionListView.getSelectionModel().getSelectedItem();

        ChoiceDialog<ElectionType> inputElectionType = new ChoiceDialog<>();
        inputElectionType.setTitle("Add Election");
        inputElectionType.setHeaderText("Choose Election Type");
        inputElectionType.setContentText("Election Type:");
        inputElectionType.getItems().addAll(ElectionType.values());
        ElectionType electionType = inputElectionType.showAndWait().orElse(null);

        TextInputDialog inputLocation = new TextInputDialog();
        inputLocation.setTitle("Add Election");
        inputLocation.setHeaderText("Enter Election Location");
        inputLocation.setContentText("Location:");
        String location = inputLocation.showAndWait().orElse("");

        TextInputDialog inputYear = new TextInputDialog();
        inputYear.setTitle("Add Election");
        inputYear.setHeaderText("Enter Election Year");
        inputYear.setContentText("Year:");
        int year = Integer.parseInt(inputYear.showAndWait().orElse(""));

        TextInputDialog inputSeats = new TextInputDialog();
        inputSeats.setTitle("Add Election");
        inputSeats.setHeaderText("Enter Election Seats");
        inputSeats.setContentText("Seats:");
        int seats = Integer.parseInt(inputSeats.showAndWait().orElse(""));

        election.setType(electionType);
        election.setLocation(location);
        election.setYear(year);
        election.setSeats(seats);

        electionAPI.editElection(election.getId(), election);
        electionListView.refresh();
    }

    @FXML
    private void deleteElection() {
        Election election = electionListView.getSelectionModel().getSelectedItem();
        electionAPI.deleteElection(election.getId());
        electionListView.getItems().remove(election);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
