package by.pet_project.ens.service.api;

import by.pet_project.ens.core.dto.RecipientCreateDTO;
import by.pet_project.ens.core.dto.RecipientDTO;

import java.util.List;

public interface IRecipientService extends ICRUDService<RecipientDTO, RecipientCreateDTO>{
    List<RecipientDTO> getUserRecipients(int createdByUserWithID);

}
