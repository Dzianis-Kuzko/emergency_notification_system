package by.pet_project.ens.service;

import by.pet_project.ens.core.dto.MessageCreateDTO;
import by.pet_project.ens.core.dto.MessageDTO;
import by.pet_project.ens.dao.api.IMessageDao;
import by.pet_project.ens.dao.api.IRecipientDao;
import by.pet_project.ens.service.api.IMessageService;

import java.time.LocalDateTime;
import java.util.List;

public class MessageService implements IMessageService {

    private final IMessageDao messageDao;
    private final IRecipientDao recipientDao;

    public MessageService(IMessageDao messageDao, IRecipientDao recipientDao) {
        this.messageDao = messageDao;
        this.recipientDao = recipientDao;
    }


    @Override
    public List<MessageDTO> get() {
        return this.messageDao.get();
    }

    @Override
    public MessageDTO get(int id) {
        return this.messageDao.get(id);
    }

    @Override
    public MessageDTO create(MessageCreateDTO item) {
        int maxCurrentId = 0;
        if (this.messageDao.get().size() != 0) {
            maxCurrentId = this.get().stream().mapToInt(MessageDTO::getId).max().orElseThrow();
        }

        MessageDTO dto = new MessageDTO();
        dto.setId(maxCurrentId + 1);
        dto.setMessageTemplateId(item.getMessageTemplateId());
        dto.setFromUserId(item.getFromUserId());
        dto.setCreationTimestamp(LocalDateTime.now());

        dto.setToRecipientsId(recipientDao.getIds(item.getFromUserId()));

        this.messageDao.create(dto);

        return dto;
    }
}
