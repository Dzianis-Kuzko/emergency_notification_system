package by.pet_project.ens.core.dto;

public class MessageTemplateCreateDTO {
    private String text;
    private int createdByUserID;

    public MessageTemplateCreateDTO() {
    }

    public MessageTemplateCreateDTO(String text, int createdByUserID) {
        this.text = text;
        this.createdByUserID = createdByUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCreatedByUserID() {
        return createdByUserID;
    }

    public void setCreatedByUserID(int createdByUserID) {
        this.createdByUserID = createdByUserID;
    }
}
