package by.pet_project.ens.dao.factory;

import by.pet_project.ens.dao.api.IUserDao;
import by.pet_project.ens.dao.db.UserJDBCDao;

public class UserDaoFactory {
    private static volatile IUserDao instance;

    private UserDaoFactory() {

    }

    public static IUserDao getInstance() {
        if (instance == null) {
            synchronized (UserDaoFactory.class) {
                if (instance == null) {
                    instance = new UserJDBCDao();
                }
            }
        }
        return instance;
    }
}
