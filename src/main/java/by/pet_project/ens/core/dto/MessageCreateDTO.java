package by.pet_project.ens.core.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MessageCreateDTO {
    private int messageTemplateId;
    private int fromUserId;

    public MessageCreateDTO() {
    }

    public MessageCreateDTO(int messageTemplateId, int fromUserId) {
        this.messageTemplateId = messageTemplateId;
        this.fromUserId = fromUserId;
    }

    public int getMessageTemplateId() {
        return messageTemplateId;
    }

    public void setMessageTemplateId(int messageTemplateId) {
        this.messageTemplateId = messageTemplateId;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }
}
