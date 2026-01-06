package Controller;
import models.*;

public class ElectionAPI {
    HashTable<String, Politician> politicians = new HashTable<>(10);
    HashTable<String, Election> elections = new HashTable<>(5);

    public Politician addPolitician(Politician politician) {
        if (politician == null) {
            System.out.println("Invalid Input");
            return null;
        }

        String politicianId = IdGenerator.newPoliticianId();
        politicians.put(politicianId, politician);

        return politicians.get(politicianId);
    }

    public Politician editPolitician(String politicianId, Politician newPolitician) {
        if (newPolitician == null){
            System.err.println("New politician is null");
            return null;
        }

        Politician politician = politicians.get(politicianId);

        if (politician != null) {
            politician.setCurrentParty(newPolitician.getCurrentParty());
            politician.setId(newPolitician.getId());
            politician.setName(newPolitician.getName());
            politician.setPhotoUrl(newPolitician.getPhotoUrl());
            politician.setHomeCounty(newPolitician.getHomeCounty());
            politician.setDateOfBirth(newPolitician.getDateOfBirth());
        }

        return politician;
    }

    public void deletePolitician(String politicianId) {
        if (politicianId.isEmpty()) {
            System.err.println("Invalid Input");
            return;
        }

        politicians.remove(politicianId);
    }

    public Election addElection(Election election) {
        if (election == null) {
            System.err.println("Invalid Input");
            return  null;
        }

        String electionId = IdGenerator.newElectionId();
        elections.put(electionId, election);

        return elections.get(electionId);
    }

    public Election editElection(String electionId, Election newElection) {
        if (newElection == null) {
            System.err.println("New election is null");
            return  null;
        }

        Election election = elections.get(electionId);

        if (election != null) {
            election.setId(newElection.getId());
            election.setLocation(newElection.getLocation());
            election.setSeats(newElection.getSeats());
            election.setType(newElection.getType());
            election.setYear(newElection.getYear());
        }

        return election;
    }

    public void deleteElection(String electionId) {
        if (electionId.isEmpty()) {
            System.err.println("Invalid Input");
            return;
        }

        elections.remove(electionId);
    }

    // todo implement these
    public void addCandidateToElection(String electionId, Candidate candidate) {
    }

    public void editCandidateInfo(String electionId, Candidate candidate) {
    }

    public void deleteCandidateFromElection(String electionId, String candidateId) {
    }

    public Politician searchPolitician(String name) {
        if (name.isEmpty()) {
            System.err.println("Invalid Input");
            return  null;
        }

        //todo make hashtable interable or just use for loop?
        for (Politician politician : politicians) {
            if (name.toLowerCase().contains(politician.getName().toLowerCase() )) {
                return politician;
            }
        }

        return null;
    }
}
