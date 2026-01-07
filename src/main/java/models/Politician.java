package models;

public class Politician {

    private String id;
    private String name;
    private String dateOfBirth;   // You can switch to LocalDate later if you want
    private String currentParty;
    private String homeCounty;
    private String photoUrl;

    // Constructor
    public Politician(String id, String name, String dateOfBirth,
                      String currentParty, String homeCounty, String photoUrl) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.currentParty = currentParty;
        this.homeCounty = homeCounty;
        this.photoUrl = photoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCurrentParty() {
        return currentParty;
    }

    public void setCurrentParty(String currentParty) {
        this.currentParty = currentParty;
    }

    public String getHomeCounty() {
        return homeCounty;
    }

    public void setHomeCounty(String homeCounty) {
        this.homeCounty = homeCounty;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return name + " (" + currentParty + ", " + homeCounty + ")";
    }


}
