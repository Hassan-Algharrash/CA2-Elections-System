package models;
import controllers.DynamicArray;

public class Election {

    private String id;
    private ElectionType type;
    private String location;
    private int year;
    private int seats;

    private DynamicArray<Candidate> candidates = new DynamicArray<>();

    // Constructor
    public Election(String id, ElectionType type, String location, int year, int seats) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.year = year;
        this.seats = seats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ElectionType getType() {
        return type;
    }

    public void setType(ElectionType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public DynamicArray<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(DynamicArray<Candidate> candidates) {
        this.candidates = candidates;
    }

    @Override
    public String toString() {
        return id + " — " + type + " — " + location + " (" + year + ")";
    }

}
