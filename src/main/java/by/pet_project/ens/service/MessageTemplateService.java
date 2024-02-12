package by.pet_project.ens.service;

import by.pet_project.ens.core.dto.MessageTemplateCreateDTO;
import by.pet_project.ens.core.dto.MessageTemplateDTO;
import by.pet_project.ens.dao.api.IMessageTemplateDao;
import by.pet_project.ens.service.api.IMessageTemplateService;

import java.time.LocalDateTime;
import java.util.List;

public class MessageTemplateService implements IMessageTemplateService {
    private final IMessageTemplateDao messageTemplateDao;

    public MessageTemplateService(IMessageTemplateDao messageTemplateDao) {
        this.messageTemplateDao = messageTemplateDao;
    }

    @Override
    public List<MessageTemplateDTO> get() {
        return this.messageTemplateDao.get();
    }

    @Override
    public MessageTemplateDTO get(int id) {
        return this.messageTemplateDao.get(id);

    }

    @Override
    public MessageTemplateDTO create(MessageTemplateCreateDTO item) {
        MessageTemplateDTO dto = new MessageTemplateDTO();

        dto.setText(item.getText());
        dto.setCreationTimestamp(LocalDateTime.now());
        dto.setCreatedByUserWithID(item.getCreatedByUserWithID());

        return this.messageTemplateDao.create(dto);

    }

    @Override
    public List<MessageTemplateDTO> getUserMessageTemplates(int createdByUserWithID) {
        return this.messageTemplateDao.getUserMessageTemplates(createdByUserWithID);
    }
}
