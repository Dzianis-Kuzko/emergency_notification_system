package by.pet_project.ens.core.dto;

import java.time.LocalDateTime;

public class MessageTemplateDTO {
    private int id;
    private String text;
    private LocalDateTime creationTimestamp;
    private int createdByUserWithID;


    public MessageTemplateDTO() {
    }

    public MessageTemplateDTO(int id, String text, LocalDateTime creationTimestamp, int createdByUserWithID) {
        this.id = id;
        this.text = text;
        this.creationTimestamp = creationTimestamp;
        this.createdByUserWithID = createdByUserWithID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public int getCreatedByUserWithID() {
        return createdByUserWithID;
    }

    public void setCreatedByUserWithID(int createdByUserWithID) {
        this.createdByUserWithID = createdByUserWithID;
    }
}
