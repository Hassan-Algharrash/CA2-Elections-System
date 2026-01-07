package controllers;
import models.*;
import utils.Utilities;

public class ElectionAPI {
    HashTable<String, Politician> politicians = new HashTable<>(10);
    HashTable<String, Election> elections = new HashTable<>(5);


    public Politician addPolitician(Politician politician) {
        if (politician == null) {
            System.out.println("Invalid Input");
            return null;
        }

        String politicianId = IdGenerator.newPoliticianId();
        politician.setId(politicianId);

        politicians.put(politicianId, politician);

        System.out.println("Calling put() with key = " + politicianId);
        politicians.put(politicianId, politician);
        System.out.println("HashTable size after put = " + politicians.size());
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
        if (election == null) return null;

        String electionId = IdGenerator.newElectionId();
        election.setId(electionId);

        elections.put(electionId, election);
        return election;
    }


    public Election editElection(String electionId, Election newElection) {
        if (newElection == null) {
            System.err.println("New election is null");
            return  null;
        }

        Election election = elections.get(electionId);

        if (election != null) {
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

    public void addCandidateToElection(String electionId, Candidate candidate) {

        if(electionId == null || electionId.trim().isEmpty() || candidate == null) {
            System.err.println("Invalid Input");
            return;
        }

        Election election = elections.get(electionId);

        if (election == null) {
            System.err.println("Election not found " + electionId);
            return;
        }

        election.getCandidates().add(candidate);

        System.out.println("Added candidate to election " + electionId);
    }

    public void editCandidateInfo(String electionId, Candidate updatedCandidate) {
        if (electionId == null || updatedCandidate == null) {
            System.err.println("Invalid Input");
            return;
        }

        Election election = elections.get(electionId);
        if (election == null) {
            System.err.println("Election not found " + electionId);
            return;
        }

        //get list of candidates in the election
        DynamicArray<Candidate> candidates = election.getCandidates();

        for (int i = 0; i < candidates.size(); i++) {
            Candidate existing = candidates.get(i);

            //find match by politician id
            if (existing.getPolitician().getId().equals(updatedCandidate.getPolitician().getId())) {

                //update the fields that are  changing
                existing.setVotes(updatedCandidate.getVotes());
                existing.setPartyInThisElection(updatedCandidate.getPartyInThisElection());

                System.out.println("Candidate info updated for " + existing.getPolitician().getName());
                return;
            }
        }
        //if theres no match
        System.err.println("Candidate info not found " + electionId);
    }

    public void deleteCandidateFromElection(String electionId, String candidateId) {
        if (electionId == null || candidateId == null) {
            System.err.println("Invalid Input");
            return;
        }

        Election election = elections.get(electionId);

        if (election == null) {
            System.err.println("Election not found " + electionId);
            return;
        }

        DynamicArray<Candidate> candidates = election.getCandidates();

        for (int i = 0; i < candidates.size(); i++) {
            Candidate existing = candidates.get(i);

            if (existing.getPolitician().getId().equals(candidateId)) {
                candidates.removeAtIndex(i);

                System.out.println("Candidate info deleted for " + existing.getPolitician().getName());
                return;
            }
        }
        System.err.println("Candidate info not found " + electionId);
    }

    public DynamicArray<Politician> searchPoliticians(String name, String party, String county) {
        //new array to store the searches results
        DynamicArray<Politician> results = new DynamicArray<>();

        //get all politicians in the hashtable
        DynamicArray<Politician> politicians = this.politicians.values();

        for (int i = 0; i < politicians.size(); i++) {
            Politician politician = politicians.get(i);

            //assumes it matches unless proven otherwise
            //doing this instead of assuming it isnt a match because we want all fields to match
            boolean found = true;

            //name search (partial)
            if (name!=null && !name.trim().isEmpty()) {
                if(!politician.getName().toLowerCase().contains(name.toLowerCase())) {
                    found = false;
                }
            }

            //party search (exact)
            if(party!=null && !party.trim().isEmpty()) {
                if (!politician.getCurrentParty().equalsIgnoreCase(party)) {
                    found = false;
                }
            }

            //county search (exact)
            if(county!=null && !county.trim().isEmpty()) {
                if (!politician.getHomeCounty().equalsIgnoreCase(county)) {
                    found = false;
                }
            }
            if (found) {
                results.add(politician);
            }
        }
        return results;
    }

    public DynamicArray<Election> searchElections(String id, ElectionType type,String location, Integer year) {
        DynamicArray<Election> results = new DynamicArray<>();
        DynamicArray<Election> elections = this.elections.values();

        for (int i = 0; i < elections.size(); i++) {
            Election election = elections.get(i);

            boolean found = true;

            if (id!=null && !id.trim().isEmpty()) {
                if(!election.getId().toLowerCase().contains(id.toLowerCase())) {
                    found = false;
                }
            }
            if(type!=null && !type.equals(election.getType())) {
                found = false;
            }

            if(location!=null && !location.trim().isEmpty()) {
                if(!election.getLocation().toLowerCase().contains(location.toLowerCase())) {
                    found = false;
                }
            }

            if(year!=null && !year.equals(election.getYear())) {
                found = false;
            }
            if (found) {
                results.add(election);
            }
        }
        return results;
    }

    //todo test this not sure if it works
    public void sortPoliticians(String name) {
        DynamicArray<Politician> politicians = this.politicians.values();

        Comparator<Politician> politicianComparator = (a,b) -> a.getName().compareTo(name);

        Utilities.insertionSort(politicians.toArray(), politicianComparator);
    }

    public DynamicArray<Election> getAllElections() {
        return elections.values();
    }

    public DynamicArray<Politician> getAllPoliticians() {
        return politicians.values();
    }


}
