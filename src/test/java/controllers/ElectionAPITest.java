package controllers;

import models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectionAPITest {

    @Test
    void addPolitician() {
        ElectionAPI api = new ElectionAPI();

        Politician p = new Politician(null, "John", "1990-01-01", "FG", "Waterford", "url");
        Politician added = api.addPolitician(p);

        assertNotNull(added);
        assertEquals("John", added.getName());
        assertEquals(1, api.getAllPoliticians().size());
    }

    @Test
    void editPolitician() {
        ElectionAPI api = new ElectionAPI();

        Politician p = api.addPolitician(new Politician(null, "John", "1990", "FG", "Waterford", "url"));

        Politician updated = new Politician(null, "Johnny", "1990", "FF", "Cork", "newurl");
        api.editPolitician(p.getId(), updated);

        Politician result = api.getAllPoliticians().get(0);

        assertEquals("Johnny", result.getName());
        assertEquals("FF", result.getCurrentParty());
        assertEquals("Cork", result.getHomeCounty());
    }

    @Test
    void deletePolitician() {
        ElectionAPI api = new ElectionAPI();

        Politician p = api.addPolitician(new Politician(null, "John", "1990", "FG", "Waterford", "url"));
        api.deletePolitician(p.getId());

        assertEquals(0, api.getAllPoliticians().size());
    }

    @Test
    void addCandidateToElection() {
        ElectionAPI api = new ElectionAPI();

        Politician p = api.addPolitician(new Politician(null, "John", "1990", "FG", "Waterford", "url"));
        Election e = api.addElection(new Election(null, ElectionType.GENERAL, "Waterford", 2020, 5));

        Candidate c = new Candidate(p, e, "FG", 1000);
        api.addCandidateToElection(e.getId(), c);

        assertEquals(1, e.getCandidates().size());
        assertEquals(c, e.getCandidates().get(0));
    }

    @Test
    void searchPoliticians() {
        ElectionAPI api = new ElectionAPI();

        api.addPolitician(new Politician(null, "John", "1990", "FG", "Waterford", "url"));
        api.addPolitician(new Politician(null, "Jane", "1980", "FF", "Cork", "url"));

        DynamicArray<Politician> results = api.searchPoliticians("John", "", "");

        assertEquals(1, results.size());
        assertEquals("John", results.get(0).getName());
    }

    @Test
    void sortPoliticians() {
        ElectionAPI api = new ElectionAPI();

        api.addPolitician(new Politician(null, "Charlie", "1990", "FG", "Waterford", "url"));
        api.addPolitician(new Politician(null, "Alice", "1990", "FG", "Waterford", "url"));

        DynamicArray<Politician> sorted =
                api.sortPoliticians(PoliticianSortType.NAME, SortAlgo.INSERTION);

        assertEquals("Alice", sorted.get(0).getName());
        assertEquals("Charlie", sorted.get(1).getName());
    }


    @Test
    void sortCandidatesByVotes() {
        ElectionAPI api = new ElectionAPI();

        Politician p1 = api.addPolitician(new Politician(null, "A", "1990", "FG", "Waterford", "url"));
        Politician p2 = api.addPolitician(new Politician(null, "B", "1990", "FG", "Waterford", "url"));

        Election e = api.addElection(new Election(null, ElectionType.GENERAL, "Waterford", 2020, 5));

        api.addCandidateToElection(e.getId(), new Candidate(p1, e, "FG", 100));
        api.addCandidateToElection(e.getId(), new Candidate(p2, e, "FG", 500));

        DynamicArray<Candidate> sorted =
                api.sortCandidatesByVotes(e.getId(), SortAlgo.MERGE);

        assertEquals(500, sorted.get(0).getVotes());
        assertEquals(100, sorted.get(1).getVotes());
    }

}
