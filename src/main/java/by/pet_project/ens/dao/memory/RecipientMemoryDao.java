package by.pet_project.ens.dao.memory;

import by.pet_project.ens.core.dto.Contact;
import by.pet_project.ens.core.dto.RecipientDTO;
import by.pet_project.ens.dao.api.IRecipientDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipientMemoryDao implements IRecipientDao {
    private final Map<Integer, RecipientDTO> recipients = new HashMap<>();

    public RecipientMemoryDao() {
        {
            Contact contact = new Contact();
            contact.setPhoneNumber("+375331236578");
            recipients.put(1, new RecipientDTO(1, "Денис", "Кузько", "РБ", "Минск", contact, 2));
        }
        {
            Contact contact = new Contact();
            contact.setTelegram("https://t.me/dk202303");
            recipients.put(2, new RecipientDTO(2, "Алексей", "Нмкевич", "РФ", "Москва", contact, 3));
        }
        {
            Contact contact = new Contact();
            contact.setViber("viber+375331236578");
            recipients.put(3, new RecipientDTO(3, "Максим", "Кзько", "Германия", "Берлин", contact, 2));
        }
        {
            Contact contact = new Contact();
            contact.setEmail("dkuzko@tut.by");
            recipients.put(4, new RecipientDTO(4, "Илья", "Иванов", "РБ", "Брест", contact, 1));
        }

    }

    @Override
    public List<RecipientDTO> get() {
        return new ArrayList<>(this.recipients.values());
    }

    @Override
    public RecipientDTO get(int id) {
        return this.recipients.get(id);
    }

    @Override
    public RecipientDTO create(RecipientDTO item) {
        this.recipients.put(item.getId(), item);
        return item;
    }

    @Override
    public List<Integer> getIds(int createdByUserWithID) {
        List<Integer> recipientIdList = new ArrayList<>();
        RecipientDTO recipientDTO = null;
        for (Map.Entry entry : recipients.entrySet()) {

            recipientDTO = (RecipientDTO) entry.getValue();

            if (recipientDTO.getCreatedByUserWithID() == createdByUserWithID) {
                recipientIdList.add(recipientDTO.getId());
            }
        }

        return recipientIdList;
    }
}
