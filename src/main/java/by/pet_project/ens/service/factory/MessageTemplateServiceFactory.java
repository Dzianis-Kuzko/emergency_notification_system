package by.pet_project.ens.service.factory;

import by.pet_project.ens.dao.factory.MessageTemplateDaoFactory;
import by.pet_project.ens.service.MessageTemplateService;
import by.pet_project.ens.service.api.IMessageTemplateService;

public class MessageTemplateServiceFactory {
    private static volatile IMessageTemplateService instance;

    private MessageTemplateServiceFactory() {
    }

    public static IMessageTemplateService getInstance(){
        if(instance==null){
            synchronized (MessageTemplateServiceFactory.class){
                if(instance ==null){
                    instance = new MessageTemplateService(MessageTemplateDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
