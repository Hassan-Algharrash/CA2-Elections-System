package models;

public class IdGenerator {

    // counters that keep track of how many ids weve made
    private static int politicianCounter = 1;
    private static int electionCounter = 1;

    // makes a new ID for a politician like "P1" and "P2"
    public static String newPoliticianId() {
        return "P" + (politicianCounter++);
    }

    //same thing but for elections
    public static String newElectionId() {
        return "E" + (electionCounter++);
    }
}
