package by.pet_project.ens.service;

import by.pet_project.ens.core.dto.MessageTemplateCreateDTO;
import by.pet_project.ens.core.dto.MessageTemplateDTO;
import by.pet_project.ens.core.dto.RecipientDTO;
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
        int maxCurrentId = this.get().stream().mapToInt(MessageTemplateDTO::getId).max().orElseThrow();

        MessageTemplateDTO dto = new MessageTemplateDTO();
        dto.setId(maxCurrentId + 1);
        dto.setText(item.getText());
        dto.setDate(LocalDateTime.now());
        return this.messageTemplateDao.create(dto);

    }
}