package models;

import controllers.DynamicArray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElectionTest {

    @Test
    void getId() {
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);
        assertEquals("E1", e.getId());
    }

    @Test
    void setId() {
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);
        e.setId("NEW_ID");
        assertEquals("NEW_ID", e.getId());
    }

    @Test
    void getType() {
        Election e = new Election("E1", ElectionType.LOCAL, "Cork", 2019, 3);
        assertEquals(ElectionType.LOCAL, e.getType());
    }

    @Test
    void setType() {
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);
        e.setType(ElectionType.EUROPEAN);
        assertEquals(ElectionType.EUROPEAN, e.getType());
    }

    @Test
    void getLocation() {
        Election e = new Election("E1", ElectionType.GENERAL, "Dublin", 2024, 10);
        assertEquals("Dublin", e.getLocation());
    }

    @Test
    void setLocation() {
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);
        e.setLocation("Galway");
        assertEquals("Galway", e.getLocation());
    }

    @Test
    void getYear() {
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);
        assertEquals(2020, e.getYear());
    }

    @Test
    void setYear() {
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);
        e.setYear(1999);
        assertEquals(1999, e.getYear());
    }

    @Test
    void getSeats() {
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 4);
        assertEquals(4, e.getSeats());
    }

    @Test
    void setSeats() {
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 4);
        e.setSeats(7);
        assertEquals(7, e.getSeats());
    }

    @Test
    void getCandidates() {
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);
        assertNotNull(e.getCandidates());
        assertEquals(0, e.getCandidates().size());
    }

    @Test
    void setCandidates() {
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);

        DynamicArray<Candidate> arr = new DynamicArray<>();
        Politician p = new Politician("P1", "JJ", "2000-01-01", "FG", "Waterford", "url");
        Candidate c = new Candidate(p, e, "FG", 100);

        arr.add(c);
        e.setCandidates(arr);

        assertEquals(1, e.getCandidates().size());
        assertEquals(c, e.getCandidates().get(0));
    }

    @Test
    void testToString() {
        Election e = new Election("E1", ElectionType.GENERAL, "Waterford", 2020, 5);
        String result = e.toString();

        assertTrue(result.contains("E1"));
        assertTrue(result.contains("Waterford"));
        assertTrue(result.contains("2020"));
    }
}
