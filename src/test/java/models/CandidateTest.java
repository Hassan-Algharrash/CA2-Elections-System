package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CandidateTest {

    @Test
    void getPolitician() {
        Politician p = new Politician("P1", "John Doe", "1990-01-01", "Fine Gael", "Waterford", "url");
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);

        Candidate c = new Candidate(p, e, "Fine Gael", 1000);

        assertEquals(p, c.getPolitician());
    }

    @Test
    void setPolitician() {
        Politician p1 = new Politician("P1", "John Doe", "1990-01-01", "FG", "Waterford", "url");
        Politician p2 = new Politician("P2", "Jane Doe", "1985-01-01", "FF", "Cork", "url");

        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);
        Candidate c = new Candidate(p1, e, "FG", 500);

        c.setPolitician(p2);

        assertEquals(p2, c.getPolitician());
    }

    @Test
    void getElection() {
        Politician p = new Politician("P1", "John Doe", "1990-01-01", "FG", "Waterford", "url");
        Election e = new Election("E1", ElectionType.LOCAL, "Cork", 2019, 3);

        Candidate c = new Candidate(p, e, "FG", 200);

        assertEquals(e, c.getElection());
    }

    @Test
    void setElection() {
        Politician p = new Politician("P1", "John Doe", "1990-01-01", "FG", "Waterford", "url");

        Election e1 = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);
        Election e2 = new Election("E2", ElectionType.LOCAL, "Cork", 2019, 3);

        Candidate c = new Candidate(p, e1, "FG", 100);
        c.setElection(e2);

        assertEquals(e2, c.getElection());
    }

    @Test
    void getPartyInThisElection() {
        Politician p = new Politician("P1", "John Doe", "1990-01-01", "FG", "Waterford", "url");
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);

        Candidate c = new Candidate(p, e, "Fine Gael", 100);

        assertEquals("Fine Gael", c.getPartyInThisElection());
    }

    @Test
    void setPartyInThisElection() {
        Politician p = new Politician("P1", "John Doe", "1990-01-01", "FG", "Waterford", "url");
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);

        Candidate c = new Candidate(p, e, "FG", 100);
        c.setPartyInThisElection("Independent");

        assertEquals("Independent", c.getPartyInThisElection());
    }

    @Test
    void getVotes() {
        Politician p = new Politician("P1", "John Doe", "1990-01-01", "FG", "Waterford", "url");
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);

        Candidate c = new Candidate(p, e, "FG", 1234);

        assertEquals(1234, c.getVotes());
    }

    @Test
    void setVotes() {
        Politician p = new Politician("P1", "John Doe", "1990-01-01", "FG", "Waterford", "url");
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);

        Candidate c = new Candidate(p, e, "FG", 500);
        c.setVotes(999);

        assertEquals(999, c.getVotes());
    }

    @Test
    void testToString() {
        Politician p = new Politician("P1", "John Doe", "1990-01-01", "FG", "Waterford", "url");
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);

        Candidate c = new Candidate(p, e, "FG", 1000);

        String result = c.toString();

        assertTrue(result.contains("John Doe"));
        assertTrue(result.contains("FG"));
        assertTrue(result.contains("1000"));
    }
}
