package by.pet_project.ens.core.dto;

public class RecipientCreateDTO {

    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private Contact contact;
    private int createdByUserWithID;

    public RecipientCreateDTO() {
    }

    public RecipientCreateDTO(String firstName, String lastName, String country, String city, Contact contact, int createdByUserWithID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.contact = contact;
        this.createdByUserWithID = createdByUserWithID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public int getCreatedByUserWithID() {
        return createdByUserWithID;
    }

    public void setCreatedByUserWithID(int createdByUserWithID) {
        this.createdByUserWithID = createdByUserWithID;
    }
}