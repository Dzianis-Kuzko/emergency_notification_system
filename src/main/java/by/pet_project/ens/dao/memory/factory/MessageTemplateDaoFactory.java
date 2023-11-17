package by.pet_project.ens.dao.memory.factory;

import by.pet_project.ens.dao.api.IMessageTemplateDao;
import by.pet_project.ens.dao.api.IRecipientDao;
import by.pet_project.ens.dao.memory.MessageTemplateMemoryDao;
import by.pet_project.ens.dao.memory.RecipientMemoryDao;

public class MessageTemplateDaoFactory {
    private static volatile IMessageTemplateDao instance;

    private MessageTemplateDaoFactory() {
    }

    public static IMessageTemplateDao getInstance(){
        if(instance==null){
            synchronized (MessageTemplateDaoFactory.class){
                if(instance ==null){
                    instance = new MessageTemplateMemoryDao();
                }
            }
        }
        return instance;
    }

}
