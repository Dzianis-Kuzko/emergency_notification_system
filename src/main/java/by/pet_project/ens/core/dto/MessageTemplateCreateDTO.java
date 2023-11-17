package by.pet_project.ens.core.dto;

public class MessageTemplateCreateDTO {
    private String text;

    public MessageTemplateCreateDTO() {
    }

    public MessageTemplateCreateDTO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
