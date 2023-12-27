package by.pet_project.ens.dao.memory;

import by.pet_project.ens.core.dto.Role;
import by.pet_project.ens.core.dto.UserDTO;
import by.pet_project.ens.dao.api.IUserDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserMemoryDao implements IUserDao {

    private final Map<Integer, UserDTO> users = new HashMap<>();

    public UserMemoryDao() {
        {
            LocalDate birthday = LocalDate.of(1990, 02, 03);
            LocalDateTime regDate = LocalDateTime.of(2023, 12, 20, 15, 18, 40);
            UserDTO user = new UserDTO(1, "kuzko", "11111", "Денис", "Кузько",
                    "Александрович", birthday, regDate, Role.ADMIN);
            users.put(user.getId(), user);
        }
        {
            LocalDate birthday = LocalDate.of(1991, 03, 16);
            LocalDateTime regDate = LocalDateTime.of(2023, 11, 21, 14, 0, 30);
            UserDTO user = new UserDTO(2, "kta", "44444", "Татьяна", "Добриденева",
                    "Александровна", birthday, regDate, Role.USER);
            users.put(user.getId(), user);
        }
        {
            LocalDate birthday = LocalDate.of(2000, 06, 01);
            LocalDateTime regDate = LocalDateTime.now();
            UserDTO user = new UserDTO(3, "kda", "2222", "Ольга", "Иванец",
                    "Александровна", birthday, regDate, Role.USER);
            users.put(user.getId(), user);
        }


    }

    @Override
    public List<UserDTO> get() {
        return new ArrayList<>(this.users.values());
    }

    @Override
    public UserDTO get(int id) {
        return this.users.get(id);
    }

    @Override
    public UserDTO create(UserDTO item) {
        this.users.put(item.getId(), item);
        return item;
    }

    @Override
    public UserDTO get(String login) {

        UserDTO userDTO = null;
        for (Map.Entry<Integer, UserDTO> entry : users.entrySet()) {
            if (entry.getValue().getLogin().equals( login)) {
                userDTO = entry.getValue();
                break;
            }
        }

        return userDTO;
    }
}
