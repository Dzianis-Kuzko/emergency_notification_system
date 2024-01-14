package by.pet_project.ens.service;

import by.pet_project.ens.core.dto.RecipientCreateDTO;
import by.pet_project.ens.core.dto.RecipientDTO;
import by.pet_project.ens.dao.api.IRecipientDao;
import by.pet_project.ens.service.api.IRecipientService;

import java.util.List;

public class RecipientService implements IRecipientService {
    private final IRecipientDao recipientDao;

    public RecipientService(IRecipientDao recipientDao) {
        this.recipientDao = recipientDao;
    }

    @Override
    public List<RecipientDTO> get() {
        return this.recipientDao.get();
    }

    @Override
    public RecipientDTO get(int id) {
        return this.recipientDao.get(id);
    }

    @Override
    public RecipientDTO create(RecipientCreateDTO item) {
        RecipientDTO dto = new RecipientDTO();
        dto.setId(-1); // реальное значение будет установлено в DAO !!! может нужно создать отдельное CreateDTO для DAO
        dto.setFirstName(item.getFirstName());
        dto.setLastName(item.getLastName());
        dto.setCountry(item.getCountry());
        dto.setCity(item.getCity());
        dto.setContact(item.getContact());
        dto.setCreatedByUserWithID(item.getCreatedByUserWithID());
        return recipientDao.create(dto);
    }
}