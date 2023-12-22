package by.pet_project.ens.service.factory;

import by.pet_project.ens.dao.memory.factory.UserDaoFactory;
import by.pet_project.ens.service.UserService;
import by.pet_project.ens.service.api.IUserService;

public class UserServiceFactory {
    private static volatile IUserService instance;

    private UserServiceFactory() {
    }

    public static IUserService getInstance() {
        if (instance == null) {
            synchronized (UserServiceFactory.class) {
                if (instance == null) {
                    instance = new UserService(UserDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
