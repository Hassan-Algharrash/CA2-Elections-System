package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ElectionController implements Initializable {
    private ElectionAPI electionAPI = new ElectionAPI();

    private ObservableList<Election> electionList = FXCollections.observableArrayList();
    private ObservableList<Politician> politicianList = FXCollections.observableArrayList();
    private ObservableList<Candidate> candidateList = FXCollections.observableArrayList();


    @FXML
    private ListView<Election> electionListView;
    @FXML
    private ListView<Politician> politicianListView;
    @FXML
    private ListView<Candidate> candidateListView;

    @FXML
    private TextField searchPolNameField;
    @FXML
    private TextField searchPolPartyField;
    @FXML
    private TextField searchPolCountyField;

    @FXML
    private TextField searchElectionIdField;
    @FXML
    private ChoiceBox<ElectionType> searchElectionTypeField;
    @FXML
    private TextField searchElectionLocationField;
    @FXML
    private TextField searchElectionYearField;

    @FXML
    private ChoiceBox<PoliticianSortType> sortPoliticianChoiceBox;
    @FXML
    private ChoiceBox<ElectionSortType> sortElectionChoiceBox;
    @FXML
    private ChoiceBox<ElectionType> sortElectionTypeChoiceBox;
    @FXML
    private ChoiceBox<Election> sortCandidateChoiceBox;
    @FXML
    private ChoiceBox<SortAlgo> sortAlgorithmChoiceBox;


    @FXML
    private ListView<Object> searchResultsListView;
    @FXML
    private ListView<Object> sortResultsListView;



    public ElectionController() {
        try {
            electionAPI = electionAPI.load();
        } catch (Exception e) {
            System.err.println("Error Loading File: " + e.getMessage());
        }
    }

    public void setAPI(ElectionAPI api) {
        this.electionAPI = api;
    }

    @FXML
    private void addElection() {
        ChoiceDialog<ElectionType> inputElectionType = new ChoiceDialog<>();
        inputElectionType.setTitle("Add Election");
        inputElectionType.setHeaderText("Choose Election Type");
        inputElectionType.setContentText("Election Type:");
        inputElectionType.getItems().addAll(ElectionType.values());
        ElectionType electionType = inputElectionType.showAndWait().orElse(null);
        if (electionType == null) return;

        TextInputDialog inputLocation = new TextInputDialog();
        inputLocation.setTitle("Add Election");
        inputLocation.setHeaderText("Enter Election Location");
        inputLocation.setContentText("Location:");
        String location = inputLocation.showAndWait().orElse("");
        if (location.isEmpty()) return;

        TextInputDialog inputYear = new TextInputDialog();
        inputYear.setTitle("Add Election");
        inputYear.setHeaderText("Enter Election Year");
        inputYear.setContentText("Year:");
        String yearStr = inputYear.showAndWait().orElse(null);
        if (yearStr.isEmpty()) return;
        int year = Integer.parseInt(yearStr);

        TextInputDialog inputSeats = new TextInputDialog();
        inputSeats.setTitle("Add Election");
        inputSeats.setHeaderText("Enter Election Seats");
        inputSeats.setContentText("Seats:");
        String seatsStr = inputSeats.showAndWait().orElse("");
        if (seatsStr.isEmpty()) return;
        int seats = Integer.parseInt(seatsStr);

        Election election = electionAPI.addElection
                (new Election(null, electionType, location, year, seats));

        if (election != null) {
            electionList.add(election);

        }
    }

    @FXML
    private void editElection() {
        Election election = electionListView.getSelectionModel().getSelectedItem();
        if (election == null) return;

        ChoiceDialog<ElectionType> inputElectionType = new ChoiceDialog<>();
        inputElectionType.setTitle("Add Election");
        inputElectionType.setHeaderText("Choose Election Type");
        inputElectionType.setContentText("Election Type:");
        inputElectionType.getItems().addAll(ElectionType.values());
        ElectionType electionType = inputElectionType.showAndWait().orElse(null);
        if (electionType == null) return;

        TextInputDialog inputLocation = new TextInputDialog();
        inputLocation.setTitle("Add Election");
        inputLocation.setHeaderText("Enter Election Location");
        inputLocation.setContentText("Location:");
        String location = inputLocation.showAndWait().orElse("");
        if (location.isEmpty()) return;

        TextInputDialog inputYear = new TextInputDialog();
        inputYear.setTitle("Add Election");
        inputYear.setHeaderText("Enter Election Year");
        inputYear.setContentText("Year:");
        String yearStr = inputYear.showAndWait().orElse("");
        if (yearStr.isEmpty()) return;
        int year = Integer.parseInt(yearStr);


        TextInputDialog inputSeats = new TextInputDialog();
        inputSeats.setTitle("Add Election");
        inputSeats.setHeaderText("Enter Election Seats");
        inputSeats.setContentText("Seats:");
        String seatsStr = inputSeats.showAndWait().orElse("");
        if (seatsStr.isEmpty()) return;
        int seats = Integer.parseInt(seatsStr);

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
        if (election == null) return;

        electionAPI.deleteElection(election.getId());
        electionListView.getItems().remove(election);
    }

    @FXML
    private void addPolitician() {

        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Add Politician");
        nameDialog.setHeaderText("Enter Politician Name");
        nameDialog.setContentText("Name:");
        String name = nameDialog.showAndWait().orElse("");
        if (name.isEmpty()) return;

        TextInputDialog dobDialog = new TextInputDialog();
        dobDialog.setTitle("Add Politician");
        dobDialog.setHeaderText("Enter Date of Birth");
        dobDialog.setContentText("DOB (e.g. 1990-05-12):");
        String dob = dobDialog.showAndWait().orElse("");
        if (dob.isEmpty()) return;

        TextInputDialog partyDialog = new TextInputDialog();
        partyDialog.setTitle("Add Politician");
        partyDialog.setHeaderText("Enter Current Party");
        partyDialog.setContentText("Party:");
        String party = partyDialog.showAndWait().orElse("");
        if (party.isEmpty()) return;

        TextInputDialog countyDialog = new TextInputDialog();
        countyDialog.setTitle("Add Politician");
        countyDialog.setHeaderText("Enter Home County");
        countyDialog.setContentText("County:");
        String county = countyDialog.showAndWait().orElse("");
        if (county.isEmpty()) return;

        TextInputDialog photoDialog = new TextInputDialog();
        photoDialog.setTitle("Add Politician");
        photoDialog.setHeaderText("Enter Photo URL");
        photoDialog.setContentText("URL:");
        String photoUrl = photoDialog.showAndWait().orElse("");
        if (photoUrl.isEmpty()) return;

        Politician p = electionAPI.addPolitician(
                new Politician(null, name, dob, party, county, photoUrl)
        );

        if (p != null) {
            politicianList.add(p);
        }
    }

    @FXML
    private void editPolitician() {
        Politician p = (Politician) politicianListView.getSelectionModel().getSelectedItem();
        if (p == null) return;

        TextInputDialog nameDialog = new TextInputDialog(p.getName());
        nameDialog.setTitle("Edit Politician");
        nameDialog.setHeaderText("Update Name");
        nameDialog.setContentText("Name:");
        String name = nameDialog.showAndWait().orElse("");
        if (name.isEmpty()) return;

        TextInputDialog dobDialog = new TextInputDialog(p.getDateOfBirth());
        dobDialog.setTitle("Edit Politician");
        dobDialog.setHeaderText("Update Date of Birth");
        dobDialog.setContentText("DOB:");
        String dob = dobDialog.showAndWait().orElse("");
        if (dob.isEmpty()) return;

        TextInputDialog partyDialog = new TextInputDialog(p.getCurrentParty());
        partyDialog.setTitle("Edit Politician");
        partyDialog.setHeaderText("Update Party");
        partyDialog.setContentText("Party:");
        String party = partyDialog.showAndWait().orElse("");
        if (party.isEmpty()) return;

        TextInputDialog countyDialog = new TextInputDialog(p.getHomeCounty());
        countyDialog.setTitle("Edit Politician");
        countyDialog.setHeaderText("Update Home County");
        countyDialog.setContentText("County:");
        String county = countyDialog.showAndWait().orElse("");
        if (county.isEmpty()) return;

        TextInputDialog photoDialog = new TextInputDialog(p.getPhotoUrl());
        photoDialog.setTitle("Edit Politician");
        photoDialog.setHeaderText("Update Photo URL");
        photoDialog.setContentText("URL:");
        String photoUrl = photoDialog.showAndWait().orElse("");
        if (photoUrl.isEmpty()) return;

        p.setName(name);
        p.setDateOfBirth(dob);
        p.setCurrentParty(party);
        p.setHomeCounty(county);
        p.setPhotoUrl(photoUrl);

        electionAPI.editPolitician(p.getId(), p);
        politicianListView.refresh();
    }

    @FXML
    private void deletePolitician() {
        Politician p = (Politician) politicianListView.getSelectionModel().getSelectedItem();
        if (p == null) return;

        electionAPI.deletePolitician(p.getId());
        politicianList.remove(p);
    }

    @FXML
    private void addCandidate() {

        // 1. Choose Politician
        ChoiceDialog<Politician> choosePol = new ChoiceDialog<>();
        choosePol.setTitle("Add Candidate");
        choosePol.setHeaderText("Select Politician");
        choosePol.getItems().addAll(politicianList);
        Politician pol = choosePol.showAndWait().orElse(null);
        if (pol == null) return;

        // 2. Choose Election
        ChoiceDialog<Election> chooseElection = new ChoiceDialog<>();
        chooseElection.setTitle("Add Candidate");
        chooseElection.setHeaderText("Select Election");
        chooseElection.getItems().addAll(electionList);
        Election election = chooseElection.showAndWait().orElse(null);
        if (election == null) return;

        // 3. Party in this election
        TextInputDialog partyDialog = new TextInputDialog(pol.getCurrentParty());
        partyDialog.setTitle("Add Candidate");
        partyDialog.setHeaderText("Enter Party for This Election");
        partyDialog.setContentText("Party:");
        String party = partyDialog.showAndWait().orElse("");
        if (party.isEmpty()) return;

        // 4. Votes
        TextInputDialog votesDialog = new TextInputDialog("0");
        votesDialog.setTitle("Add Candidate");
        votesDialog.setHeaderText("Enter Starting Votes");
        votesDialog.setContentText("Votes:");
        String votesStr = votesDialog.showAndWait().orElse("");
        if (votesStr.isEmpty()) return;
        int votes = Integer.parseInt(votesStr);

        // 5. Create Candidate
        Candidate c = new Candidate(pol, election, party, votes);

        // 6. Add to API
        electionAPI.addCandidateToElection(election.getId(), c);

        // 7. Add to UI list
        candidateList.add(c);
    }


    @FXML
    private void editCandidate() {
        Candidate c = candidateListView.getSelectionModel().getSelectedItem();
        if (c == null) return;

        // Party
        TextInputDialog partyDialog = new TextInputDialog(c.getPartyInThisElection());
        partyDialog.setTitle("Edit Candidate");
        partyDialog.setHeaderText("Update Party");
        partyDialog.setContentText("Party:");
        String party = partyDialog.showAndWait().orElse("");
        if (party.isEmpty()) return;

        // Votes
        TextInputDialog votesDialog = new TextInputDialog(String.valueOf(c.getVotes()));
        votesDialog.setTitle("Edit Candidate");
        votesDialog.setHeaderText("Update Votes");
        votesDialog.setContentText("Votes:");
        String votesStr = votesDialog.showAndWait().orElse("");
        if (votesStr.isEmpty()) return;
        int votes = Integer.parseInt(votesStr);

        // Update object
        c.setPartyInThisElection(party);
        c.setVotes(votes);

        // Update API
        electionAPI.editCandidateInfo(c.getElection().getId(), c);

        candidateListView.refresh();
    }


    @FXML
    private void deleteCandidate() {
        Candidate c = candidateListView.getSelectionModel().getSelectedItem();
        if (c == null) return;

        electionAPI.deleteCandidateFromElection(
                c.getElection().getId(),
                c.getPolitician().getId()
        );

        candidateList.remove(c);
    }


    @FXML
    // loop through all elections check each candidate list,
    // and if the politician shows up add that election to the results.
    private DynamicArray<Election> getElectionsForPolitician(Politician p) {
        DynamicArray<Election> electionsForPolitician = new DynamicArray<>();

        DynamicArray<Election> allElections = electionAPI.getAllElections();

        for (int i = 0; i < allElections.size(); i++) {
            Election e = allElections.get(i);

            DynamicArray<Candidate> candidates = e.getCandidates();
            for (int j = 0; j < candidates.size(); j++) {
                Candidate c = candidates.get(j);

                if (c.getPolitician().getId().equals(p.getId())) {
                    electionsForPolitician.add(e);
                    break; // found them in this election, move on
                }
            }
        }

        return electionsForPolitician;
    }


    @FXML
    private void searchPoliticians() {

        String name = searchPolNameField.getText();
        String party = searchPolPartyField.getText();
        String county = searchPolCountyField.getText();

        DynamicArray<Politician> results = electionAPI.searchPoliticians(name, party, county);

        ObservableList<Object> list = FXCollections.observableArrayList();

        for (int i = 0; i < results.size(); i++) {
            Politician p = results.get(i);

            StringBuilder sb = new StringBuilder();
            sb.append(p.getName())
                    .append(" (")
                    .append(p.getCurrentParty())
                    .append(", ")
                    .append(p.getHomeCounty())
                    .append(")\n");

            DynamicArray<Election> elections = getElectionsForPolitician(p);

            for (int j = 0; j < elections.size(); j++) {
                Election e = elections.get(j);
                sb.append("   • ")
                        .append(e.getYear())
                        .append(" ")
                        .append(e.getType())
                        .append(" – ")
                        .append(e.getLocation())
                        .append("\n");
            }

            list.add(sb.toString());
        }

        searchResultsListView.setItems(list);
    }


    @FXML
    private void searchElections() {

        String id = searchElectionIdField.getText();
        ElectionType type = searchElectionTypeField.getValue();
        String location = searchElectionLocationField.getText();

        Integer year = null;
        if (!searchElectionYearField.getText().trim().isEmpty()) {
            year = Integer.parseInt(searchElectionYearField.getText().trim());
        }

        DynamicArray<Election> results = electionAPI.searchElections(id, type, location, year);

        ObservableList<Object> list = FXCollections.observableArrayList();
        for (int i = 0; i < results.size(); i++) {
            list.add(results.get(i));
        }

        searchResultsListView.setItems(list);

        System.out.println("SearchResultsListView = " + searchResultsListView);
        System.out.println("Search returned " + results.size() + " items");
    }

    @FXML
    private void sortPoliticians() {
        PoliticianSortType sortType = sortPoliticianChoiceBox.getValue();

        SortAlgo algo = sortAlgorithmChoiceBox.getValue();

        DynamicArray<Politician> results = electionAPI.sortPoliticians(sortType, algo);


        ObservableList<Object> list = FXCollections.observableArrayList();
        for (int i = 0; i < results.size(); i++) {
            list.add(results.get(i));
        }

        sortResultsListView.setItems(list);

        //added this just make sure its sorting what we want
        System.out.println("Sorted " + results.size() + " politicians by " + sortType);
    }

    @FXML
    private void sortElections() {
        ElectionSortType sortType = sortElectionChoiceBox.getValue();
        SortAlgo algo = sortAlgorithmChoiceBox.getValue();

        if (sortType == null) {
            System.err.println("No sort type selected");
            return;
        }

        //  the API sort
        DynamicArray<Election> results = electionAPI.sortElections(sortType, algo);

        // DynamicArray → ObservableList
        ObservableList<Object> list = FXCollections.observableArrayList();
        for (int i = 0; i < results.size(); i++) {
            list.add(results.get(i));
        }

        // Display in your results ListView
        sortResultsListView.setItems(list);

        System.out.println("Sorted " + results.size() + " elections by " + sortType);
    }


    @FXML
    private void sortCandidates() {

        Election selectedElection = sortCandidateChoiceBox.getSelectionModel().getSelectedItem();
        SortAlgo algo = sortAlgorithmChoiceBox.getValue();

        if (selectedElection == null) {
            System.err.println("No election selected for candidate sorting");
            return;
        }

        String selectedElectionId = selectedElection.getId();

        DynamicArray<Candidate> results = electionAPI.sortCandidatesByVotes(selectedElectionId, algo);

        ObservableList<Object> list = FXCollections.observableArrayList();
        for (int i = 0; i < results.size(); i++) {
            list.add(results.get(i));
        }

        sortResultsListView.setItems(list);
    }


    @FXML
    private void save() {
        try {
            electionAPI.save();
        } catch (Exception e) {
            System.err.println("Error Saving File: " + e.getMessage());
        }
    }

    public void loadInitialData() {
        // load elections
        electionList.clear();
        DynamicArray<Election> allElections = electionAPI.getAllElections();
        for (int i = 0; i < allElections.size(); i++) {
            electionList.add(allElections.get(i));
        }

        // load politicians
        politicianList.clear();
        DynamicArray<Politician> allPoliticians = electionAPI.getAllPoliticians();
        for (int i = 0; i < allPoliticians.size(); i++) {
            politicianList.add(allPoliticians.get(i));
        }
        refreshAll();
    }

    private void populateListViews() {
        for (int i = 0; i < electionAPI.getAllElections().size(); i++) {
            Election election = electionAPI.getAllElections().get(i);
            electionList.add(election);
            electionListView.getItems().add(election);
        }

        for (int i = 0; i < electionAPI.getAllPoliticians().size(); i++) {
            Politician politician = electionAPI.getAllPoliticians().get(i);
            politicianList.add(politician);
            politicianListView.getItems().add(politician);
        }

        for (int i = 0; i < electionAPI.getAllElections().size(); i++) {
            Election election = electionAPI.getAllElections().get(i);
            electionList.add(election);
            electionListView.getItems().add(election);

            for (int j = 0; j < electionAPI.getAllElections().get(i).getCandidates().size(); j++) {
                Candidate candidate = electionAPI.getAllElections().get(i).getCandidates().get(j);
                candidateList.add(candidate);
                candidateListView.getItems().add(candidate);
            }
        }
    }

    private void refreshAll() {
        electionListView.refresh();
        politicianListView.refresh();
        candidateListView.refresh();

    }

    @FXML
    private void clear() {
        electionAPI.clear();

        electionList.clear();
        politicianList.clear();
        candidateList.clear();

        electionListView.getItems().clear();
        politicianListView.getItems().clear();
        candidateListView.getItems().clear();

        searchResultsListView.getItems().clear();

        System.out.println("Cleared all data");
    }

    private void updateCandidateList(Election election) {
        candidateList.clear();

        DynamicArray<Candidate> candidates = election.getCandidates();
        for (int i = 0; i < candidates.size(); i++) {
            candidateList.add(candidates.get(i));
        }

        candidateListView.refresh();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        electionListView.setItems(electionList);
        politicianListView.setItems(politicianList);
        candidateListView.setItems(candidateList);
        searchElectionTypeField.getItems().addAll(ElectionType.values());
        sortPoliticianChoiceBox.getItems().addAll(PoliticianSortType.values());
        sortElectionChoiceBox.getItems().addAll(ElectionSortType.values());
        sortElectionTypeChoiceBox.getItems().addAll(ElectionType.values());
        sortAlgorithmChoiceBox.getItems().addAll(SortAlgo.values());
        sortAlgorithmChoiceBox.setValue(SortAlgo.INSERTION); // default


        electionListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selectedElection) -> {
            if (selectedElection != null) {
                updateCandidateList(selectedElection);
            }
        });


        // load elections, politicians, candidates from save file
        populateListViews();

        // populate the ChoiceBox (after data is loaded)
        sortCandidateChoiceBox.setItems(electionList);
    }



}
