package by.pet_project.ens.dao.memory.factory;

import by.pet_project.ens.dao.api.IRecipientDao;
import by.pet_project.ens.dao.memory.RecipientMemoryDao;

public class RecipientDaoFactory {
    private static volatile IRecipientDao instance;

    private RecipientDaoFactory() {
    }

    public static IRecipientDao getInstance(){
        if(instance==null){
            synchronized (RecipientDaoFactory.class){
                if(instance ==null){
                    instance = new RecipientMemoryDao();
                }
            }
        }
        return instance;
    }
}
