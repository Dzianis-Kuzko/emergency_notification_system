package by.pet_project.ens.core.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class MessageTemplateDTO {
    private int id;
    private String text;
    private LocalDateTime date;

    public MessageTemplateDTO() {
    }

    public MessageTemplateDTO(int id, String text, LocalDateTime date) {
        this.id = id;
        this.text = text;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
