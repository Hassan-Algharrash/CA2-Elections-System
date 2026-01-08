package controllers;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;
import utils.Utilities;

import java.io.File;

import java.io.*;

public class ElectionAPI {
    HashTable<String, Politician> politicians = new HashTable<>(10);
    HashTable<String, Election> elections = new HashTable<>(5);

    private final File file = new File("elections-system-save.xml");
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

    public DynamicArray<Politician> sortPoliticians(PoliticianSortType type, SortAlgo algo) {
        DynamicArray<Politician> politicians = this.politicians.values();

        // Copy into array
        Object[] pArray = new Object[politicians.size()];
        for (int i = 0; i < politicians.size(); i++) {
            pArray[i] = politicians.get(i);
        }

        // choose comparator based on sort type
        Comparator<Politician> comparator;

        switch (type) {
            case NAME:
                comparator = (a, b) -> a.getName().compareToIgnoreCase(b.getName());
                break;
            case PARTY:
                comparator = (a, b) -> a.getCurrentParty().compareToIgnoreCase(b.getCurrentParty());
                break;
            case COUNTY:
                comparator = (a, b) -> a.getHomeCounty().compareToIgnoreCase(b.getHomeCounty());
                break;
            default:
                return politicians;
        }

        // Choose algorithm
        if (algo == SortAlgo.INSERTION) {
            Utilities.insertionSort(pArray, comparator);
        } else {
            Utilities.mergeSort(pArray, comparator);
        }

        // Convert back to DynamicArray
        DynamicArray<Politician> sortedResults = new DynamicArray<>();
        for (Object obj : pArray) {
            sortedResults.add((Politician) obj);
        }

        return sortedResults;
    }


    public DynamicArray<Election> sortElections(ElectionSortType sortType, SortAlgo algo) {

        DynamicArray<Election> elections = this.elections.values();

        // copy only the used elements (avoid nulls)
        Object[] eArray = new Object[elections.size()];
        for (int i = 0; i < elections.size(); i++) {
            eArray[i] = elections.get(i);
        }

        Comparator<Election> comparator;

        switch (sortType) {
            case YEAR:
                comparator = (a, b) -> Integer.compare(a.getYear(), b.getYear());
                break;

            case LOCATION:
                comparator = (a, b) -> a.getLocation().compareToIgnoreCase(b.getLocation());
                break;

            case SEATS:
                comparator = (a, b) -> Integer.compare(a.getSeats(), b.getSeats());
                break;

            default:
                return elections; // no sorting
        }

        if (algo == SortAlgo.INSERTION) {
            Utilities.insertionSort(eArray, comparator);
        } else if (algo == SortAlgo.MERGE) {
            Utilities.mergeSort(eArray, comparator);
        }

        DynamicArray<Election> sortedResults = new DynamicArray<>();
        for (Object obj : eArray) {
            sortedResults.add((Election) obj);
        }

        return sortedResults;
    }


    public DynamicArray<Candidate> sortCandidatesByVotes(String electionId, SortAlgo algo) {
        Election election = elections.get(electionId);
        if (election == null) return new DynamicArray<>();

        DynamicArray<Candidate> candidates = election.getCandidates();
        Object[] cArray = new Object[candidates.size()];
        for (int i = 0; i < candidates.size(); i++) {
            cArray[i] = candidates.get(i);
        }

        System.out.println("=== DEBUG ARRAY BEFORE SORT ===");
        for (int i = 0; i < cArray.length; i++) {
            System.out.println(i + ": " + cArray[i]);
        }


        Comparator<Candidate> voteComparator =
                (a, b) -> Integer.compare(a.getVotes(), b.getVotes());

        if (algo == SortAlgo.INSERTION) {
            Utilities.insertionSort(cArray, voteComparator);
        } else if (algo == SortAlgo.MERGE) {
            Utilities.mergeSort(cArray, voteComparator);
        }


        for (int i = 0; i < cArray.length / 2; i++) {
            Object temp = cArray[i];
            cArray[i] = cArray[cArray.length - 1 - i];
            cArray[cArray.length - 1 - i] = temp; }
        DynamicArray<Candidate> sortedResults = new DynamicArray<>();
        for (Object obj : cArray) {
            sortedResults.add((Candidate) obj);
        }

        return sortedResults;
    }

    public DynamicArray<Election> getAllElections() {
        return elections.values();
    }

    public DynamicArray<Politician> getAllPoliticians() {
        return politicians.values();
    }


    public void save() throws Exception {
        var xstream = new XStream(new DomDriver());
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
        os.writeObject(this);
        os.close();
    }

    public ElectionAPI load() throws Exception {
        //list of classes that you wish to include in the serialization, separated by a comma
        Class<?>[] classes = new Class[] {
                ElectionAPI.class,
                Candidate.class,
                Election.class,
                Politician.class };

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialization to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));

        ElectionAPI electionAPI = (ElectionAPI) in.readObject();
        in.close();

        return electionAPI;
    }



    public boolean clear() {
        if (file.exists()) {
            return file.delete();
        }

        return false;
    }
}
