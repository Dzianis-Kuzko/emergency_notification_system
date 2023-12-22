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
        int maxCurrentId = this.get().stream().mapToInt(UserDTO::getId).max().orElseThrow();
        UserDTO dto = new UserDTO();
        dto.setId(maxCurrentId + 1);
        dto.setLogin(item.getLogin());
        dto.setPassword(item.getPassword());
        dto.setFirstName(item.getFirstName());
        dto.setMiddleName(item.getMiddleName());
        dto.setLastName(item.getLastName());
        dto.setBirthday(item.getBirthday());
        dto.setRegistrationDateTime(LocalDateTime.now());
        dto.setRole(item.getRole());

        return userDao.create(dto);
    }
}
