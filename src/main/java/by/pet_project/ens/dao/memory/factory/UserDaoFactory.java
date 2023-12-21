package by.pet_project.ens.dao.memory.factory;

import by.pet_project.ens.dao.api.IUserDao;
import by.pet_project.ens.dao.memory.UserMemoryDao;

public class UserDaoFactory {
    private static volatile IUserDao instance;

    private UserDaoFactory() {

    }

    public static IUserDao getInstance() {
        if (instance == null) {
            synchronized (UserDaoFactory.class) {
                if (instance == null) {
                    instance = new UserMemoryDao();
                }
            }
        }
        return instance;
    }
}
