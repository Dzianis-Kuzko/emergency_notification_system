package by.pet_project.ens.core.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MessageDTO {
    private int id;
    private int messageTemplateId;
    private int fromUserId;
    private List<Integer> toRecipientsId;
    private LocalDateTime creationTimestamp;

    public MessageDTO() {
    }

    public MessageDTO(int id, int messageTemplateId, int fromUserId, List<Integer> toRecipientsId, LocalDateTime creationTimestamp) {
        this.id = id;
        this.messageTemplateId = messageTemplateId;
        this.fromUserId = fromUserId;
        this.toRecipientsId = toRecipientsId;
        this.creationTimestamp = creationTimestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Integer> getToRecipientsId() {
        return toRecipientsId;
    }

    public void setToRecipientsId(List<Integer> toRecipientsId) {
        this.toRecipientsId = toRecipientsId;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
