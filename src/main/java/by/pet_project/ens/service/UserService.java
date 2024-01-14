package by.pet_project.ens.service;

import by.pet_project.ens.core.dto.UserCreateDTO;
import by.pet_project.ens.core.dto.UserDTO;
import by.pet_project.ens.dao.api.IUserDao;
import by.pet_project.ens.service.api.IUserService;

import java.time.LocalDateTime;
import java.util.List;

public class UserService implements IUserService {
    public final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDTO> get() {
        return this.userDao.get();
    }

    @Override
    public UserDTO get(int id) {
        return this.userDao.get(id);
    }

    @Override
    public UserDTO create(UserCreateDTO item) {
        UserDTO dto = new UserDTO();
        dto.setId(-1);
        dto.setLogin(item.getLogin());
        dto.setPassword(item.getPassword());
        dto.setFirstName(item.getFirstName());
        dto.setMiddleName(item.getMiddleName());
        dto.setLastName(item.getLastName());
        dto.setBirthday(item.getBirthday());
        dto.setRegistrationTimestamp(LocalDateTime.now());
        dto.setRole(item.getRole());

        return userDao.create(dto);
    }

    @Override
    public boolean authenticate(String login, String password) {
        UserDTO userDTO = userDao.get(login);

        if (userDTO != null && userDTO.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDTO get(String login) {
        return userDao.get(login);
    }
}
