package by.pet_project.ens.service.api;

import by.pet_project.ens.core.dto.MessageTemplateCreateDTO;
import by.pet_project.ens.core.dto.MessageTemplateDTO;

import java.util.List;

public interface IMessageTemplateService extends ICRUDService<MessageTemplateDTO, MessageTemplateCreateDTO> {
    List<MessageTemplateDTO> getUserMessageTemplates(int createdByUserWithID);
}
