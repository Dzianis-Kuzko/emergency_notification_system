package by.pet_project.ens.service.factory;

import by.pet_project.ens.dao.factory.RecipientDaoFactory;
import by.pet_project.ens.service.RecipientService;
import by.pet_project.ens.service.api.IRecipientService;

public class RecipientServiceFactory {
    private static volatile IRecipientService instance;

    private RecipientServiceFactory() {
    }

    public static IRecipientService getInstance(){
        if(instance==null){
            synchronized (RecipientServiceFactory.class){
                if(instance ==null){
                    instance = new RecipientService(RecipientDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
