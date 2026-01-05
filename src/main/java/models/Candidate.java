package models;

public class Candidate {
    private Politician politician;
    private Election election;
    private String partyInThisElection;
    private int votes;

    public Candidate(Politician politician, Election election, String partyInThisElection, int votes)
    { this.politician = politician;
        this.election = election;
        this.partyInThisElection = partyInThisElection;
        this.votes = votes;
    }

    public Politician getPolitician() {
        return politician;
    }

    public void setPolitician(Politician politician) {
        this.politician = politician;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public String getPartyInThisElection() {
        return partyInThisElection;
    }

    public void setPartyInThisElection(String partyInThisElection) {
        this.partyInThisElection = partyInThisElection;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "politician=" + politician +
                ", election=" + election +
                ", partyInThisElection='" + partyInThisElection + '\'' +
                ", votes=" + votes +
                '}';
    }
}
