package by.pet_project.ens.dao.factory;

import by.pet_project.ens.dao.api.IMessageTemplateDao;
import by.pet_project.ens.dao.db.MessageTemplateJDBCDao;

public class MessageTemplateDaoFactory {
    private static volatile IMessageTemplateDao instance;

    private MessageTemplateDaoFactory() {
    }

    public static IMessageTemplateDao getInstance() {
        if (instance == null) {
            synchronized (MessageTemplateDaoFactory.class) {
                if (instance == null) {
                    instance = new MessageTemplateJDBCDao();
                }
            }
        }
        return instance;
    }

}
