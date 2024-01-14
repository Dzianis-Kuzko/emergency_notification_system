package by.pet_project.ens.dao.memory;

import by.pet_project.ens.core.dto.MessageTemplateDTO;
import by.pet_project.ens.dao.api.IMessageTemplateDao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageTemplateMemoryDao implements IMessageTemplateDao {
    private final Map<Integer, MessageTemplateDTO> messageTemplates = new HashMap<>();

    public MessageTemplateMemoryDao() {
        this.messageTemplates.put(1, new MessageTemplateDTO(1, "Внимание", LocalDateTime.now(), 2));
        this.messageTemplates.put(2, new MessageTemplateDTO(2, "Осторожно", LocalDateTime.now(), 2));
        this.messageTemplates.put(3, new MessageTemplateDTO(3, "Тревога", LocalDateTime.now(), 3));
    }

    @Override
    public List<MessageTemplateDTO> get() {
        return new ArrayList<>(this.messageTemplates.values());
    }

    @Override
    public MessageTemplateDTO get(int id) {
        return this.messageTemplates.get(id);

    }

    @Override
    public MessageTemplateDTO create(MessageTemplateDTO item) {
        this.messageTemplates.put(item.getId(), item);
        return item;
    }

    @Override
    public List<MessageTemplateDTO> getUserMessages(int createdByUserWithID) {
        List<MessageTemplateDTO> messageTemplateDTOList = new ArrayList<>();
        MessageTemplateDTO dto = null;
        for (Map.Entry<Integer, MessageTemplateDTO> entry : messageTemplates.entrySet()) {
            if (entry.getValue().getCreatedByUserWithID() == createdByUserWithID) {
                dto = entry.getValue();
                messageTemplateDTOList.add(dto);
            }
        }
        return messageTemplateDTOList;
    }
}
