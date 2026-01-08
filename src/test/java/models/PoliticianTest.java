package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PoliticianTest {

    @Test
    void getId() {
        Politician p = new Politician("P1", "John", "1990-01-01", "FG", "Waterford", "url");
        assertEquals("P1", p.getId());
    }

    @Test
    void setId() {
        Politician p = new Politician("P1", "John", "1990-01-01", "FG", "Waterford", "url");
        p.setId("NewID");
        assertEquals("NewID", p.getId());
    }

    @Test
    void getName() {
        Politician p = new Politician("P1", "Alice", "1980-05-10", "FF", "Cork", "url");
        assertEquals("Alice", p.getName());
    }

    @Test
    void setName() {
        Politician p = new Politician("P1", "Old Name", "1980-05-10", "FF", "Cork", "url");
        p.setName("New Name");
        assertEquals("New Name", p.getName());
    }

    @Test
    void getDateOfBirth() {
        Politician p = new Politician("P1", "John", "1975-12-12", "FG", "Dublin", "url");
        assertEquals("1975-12-12", p.getDateOfBirth());
    }

    @Test
    void setDateOfBirth() {
        Politician p = new Politician("P1", "John", "1975-12-12", "FG", "Dublin", "url");
        p.setDateOfBirth("2000-01-01");
        assertEquals("2000-01-01", p.getDateOfBirth());
    }

    @Test
    void getCurrentParty() {
        Politician p = new Politician("P1", "John", "1990-01-01", "Green", "Galway", "url");
        assertEquals("Green", p.getCurrentParty());
    }

    @Test
    void setCurrentParty() {
        Politician p = new Politician("P1", "John", "1990-01-01", "Green", "Galway", "url");
        p.setCurrentParty("Independent");
        assertEquals("Independent", p.getCurrentParty());
    }

    @Test
    void getHomeCounty() {
        Politician p = new Politician("P1", "John", "1990-01-01", "FG", "Limerick", "url");
        assertEquals("Limerick", p.getHomeCounty());
    }

    @Test
    void setHomeCounty() {
        Politician p = new Politician("P1", "John", "990-01-01", "FG", "Limerick", "url");
        p.setHomeCounty("Mayo");
        assertEquals("Mayo", p.getHomeCounty());
    }

    @Test
    void getPhotoUrl() {
        Politician p = new Politician("P1", "John", "990-01-01", "FG", "Limerick", "photo.jpg");
        assertEquals("photo.jpg", p.getPhotoUrl());
    }

    @Test
    void setPhotoUrl() {
        Politician p = new Politician("P1", "John", "990-01-01", "FG", "Limerick", "old.jpg");
        p.setPhotoUrl("new.jpg");
        assertEquals("new.jpg", p.getPhotoUrl());
    }

    @Test
    void testToString() {
        Politician p = new Politician("P1", "JJ", "1990-01-01", "FG", "Waterford", "url");
        String result = p.toString();

        assertTrue(result.contains("JJ"));
        assertTrue(result.contains("FG"));
        assertTrue(result.contains("Waterford"));
    }
}
