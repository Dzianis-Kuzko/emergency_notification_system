package by.pet_project.ens.dao.memory;

import by.pet_project.ens.core.dto.ContactData;
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
            ContactData contactData = new ContactData();
            contactData.setPhoneNumber("+375331236578");
            recipients.put(1, new RecipientDTO(1, "Кузько", "Денис", "РБ", "Минск", contactData));
        }
        {
            ContactData contactData = new ContactData();
            contactData.setTelegram("https://t.me/dk202303");
            recipients.put(2, new RecipientDTO(2, "Немкевич", "Алексей", "РФ", "Москва", contactData));
        }
        {
            ContactData contactData = new ContactData();
            contactData.setViber("viber+375331236578");
            recipients.put(3, new RecipientDTO(3, "Добриденев", "Максим", "Германия", "Берлин", contactData));
        }
        {
            ContactData contactData = new ContactData();
            contactData.setEmail("dkuzko@tut.by");
            recipients.put(4, new RecipientDTO(4, "Иванов", "Илья", "РБ", "Брест", contactData));
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
}
