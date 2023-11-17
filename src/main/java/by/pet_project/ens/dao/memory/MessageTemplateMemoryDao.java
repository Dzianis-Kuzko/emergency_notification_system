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
        this.messageTemplates.put(1, new MessageTemplateDTO(1, "Внимание", LocalDateTime.now()));
        this.messageTemplates.put(2, new MessageTemplateDTO(2, "Осторожно", LocalDateTime.now()));
        this.messageTemplates.put(1, new MessageTemplateDTO(1, "Тревога", LocalDateTime.now()));
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
}