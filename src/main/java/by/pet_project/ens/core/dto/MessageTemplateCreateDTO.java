package by.pet_project.ens.core.dto;

public class MessageTemplateCreateDTO {
    private String text;
    private int createdByUserWithID;

    public MessageTemplateCreateDTO() {
    }

    public MessageTemplateCreateDTO(String text, int createdByUserWithID) {
        this.text = text;
        this.createdByUserWithID = createdByUserWithID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCreatedByUserWithID() {
        return createdByUserWithID;
    }

    public void setCreatedByUserWithID(int createdByUserWithID) {
        this.createdByUserWithID = createdByUserWithID;
    }
}
