package by.pet_project.ens.core.dto;

public class Contact {
    private String phoneNumber;
    private String email;
    private String telegram;
    private String viber;

    public Contact() {
        this.phoneNumber = "";
        this.email = "";
        this.telegram = "";
        this.viber = "";
    }

    public Contact(String phoneNumber, String email, String telegram, String viber) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.telegram = telegram;
        this.viber = viber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getViber() {
        return viber;
    }

    public void setViber(String viber) {
        this.viber = viber;
    }
}
