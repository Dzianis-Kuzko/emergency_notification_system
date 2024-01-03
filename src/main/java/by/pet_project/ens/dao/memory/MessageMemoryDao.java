package by.pet_project.ens.dao.memory;

import by.pet_project.ens.core.dto.MessageDTO;
import by.pet_project.ens.dao.api.IMessageDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageMemoryDao implements IMessageDao {
    private final Map<Integer, MessageDTO> messages = new HashMap<>();
    public MessageMemoryDao(){

    }

    @Override
    public List<MessageDTO> get() {
        return new ArrayList<>(this.messages.values());
    }

    @Override
    public MessageDTO get(int id) {
        return this.messages.get(id);
    }

    @Override
    public MessageDTO create(MessageDTO item) {
        this.messages.put(item.getId(), item);

        return item;
    }
}
