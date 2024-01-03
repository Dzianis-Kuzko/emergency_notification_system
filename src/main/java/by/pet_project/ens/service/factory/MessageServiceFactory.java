package by.pet_project.ens.service.factory;

import by.pet_project.ens.dao.memory.factory.MessageDaoFactory;
import by.pet_project.ens.dao.memory.factory.RecipientDaoFactory;
import by.pet_project.ens.service.MessageService;
import by.pet_project.ens.service.api.IMessageService;

public class MessageServiceFactory {
    private static volatile IMessageService instance;

    private MessageServiceFactory() {
    }

    public static IMessageService getInstance() {
        if (instance == null) {
            synchronized (MessageServiceFactory.class) {
                if (instance == null) {
                    instance = new MessageService(MessageDaoFactory.getInstance(), RecipientDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
