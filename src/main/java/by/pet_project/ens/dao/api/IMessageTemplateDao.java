package by.pet_project.ens.dao.api;

import by.pet_project.ens.core.dto.MessageTemplateDTO;

import java.util.List;

public interface IMessageTemplateDao extends ICRUDDao <MessageTemplateDTO> {
    List<MessageTemplateDTO> getUserMessages(int id);
}
