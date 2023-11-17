package by.pet_project.ens.core.dto;

public class RecipientDTO {
    private int id;
    private String surname;
    private String name;
    private String country;
    private String city;

    private ContactData contactData;

    public RecipientDTO() {
    }

    public RecipientDTO(int id, String surname, String name, String country, String city, ContactData contactData) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.country = country;
        this.city = city;
        this.contactData = contactData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ContactData getContactData() {
        return contactData;
    }

    public void setContactData(ContactData contactData) {
        this.contactData = contactData;
    }
}