package by.pet_project.ens.dao.api;

import by.pet_project.ens.core.dto.RecipientDTO;

import java.util.List;

public interface IRecipientDao extends ICRUDDao<RecipientDTO> {
    List<Integer> getIds(int createdByUserWithID);


}

