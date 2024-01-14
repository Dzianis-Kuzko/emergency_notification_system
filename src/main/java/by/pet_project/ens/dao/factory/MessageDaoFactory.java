package by.pet_project.ens.dao.factory;

import by.pet_project.ens.dao.api.IMessageDao;
import by.pet_project.ens.dao.memory.MessageMemoryDao;

public class MessageDaoFactory {
    private static volatile IMessageDao instance;

    private MessageDaoFactory() {
    }

    public static IMessageDao getInstance() {
        if (instance == null) {
            synchronized (MessageDaoFactory.class) {
                if (instance == null) {
                    instance = new MessageMemoryDao();
                }
            }
        }
        return instance;
    }
}
