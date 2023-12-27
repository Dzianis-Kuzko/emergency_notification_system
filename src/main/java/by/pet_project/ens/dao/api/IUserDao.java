package by.pet_project.ens.dao.api;

import by.pet_project.ens.core.dto.UserDTO;

public interface IUserDao extends ICRUDDao<UserDTO> {
    UserDTO get(String login);

}
