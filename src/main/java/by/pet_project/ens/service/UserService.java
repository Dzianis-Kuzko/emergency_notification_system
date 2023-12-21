package by.pet_project.ens.service;

import by.pet_project.ens.dao.api.IUserDao;

public class UserService {
    public final IUserDao userDao;


    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }
}
