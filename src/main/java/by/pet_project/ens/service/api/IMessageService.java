package by.pet_project.ens.service.api;

import by.pet_project.ens.core.dto.MessageCreateDTO;
import by.pet_project.ens.core.dto.MessageDTO;
import by.pet_project.ens.dao.api.ICRUDDao;

public interface IMessageService extends ICRUDService<MessageDTO, MessageCreateDTO> {
}
