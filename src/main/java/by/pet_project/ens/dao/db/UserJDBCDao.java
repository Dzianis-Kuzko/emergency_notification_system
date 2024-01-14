package by.pet_project.ens.dao.db;

import by.pet_project.ens.core.dto.Role;
import by.pet_project.ens.core.dto.UserDTO;
import by.pet_project.ens.dao.api.AccessDataException;
import by.pet_project.ens.dao.api.IUserDao;
import by.pet_project.ens.dao.db.ds.DataBaseConnectionFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDao implements IUserDao {
    @Override
    public List<UserDTO> get() {
        List<UserDTO> userDTOList = new ArrayList<>();
        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id, " +
                             "login, " +
                             "password, " +
                             "first_name, " +
                             "middle_name, " +
                             "last_name, " +
                             "birthday, " +
                             "registration_timestamp, " +
                             "role_id " +
                             "FROM app.users;");
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                UserDTO userDTO = initializeUser(rs);
                userDTOList.add(userDTO);
            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return userDTOList;
    }

    @Override
    public UserDTO get(int id) {
        UserDTO userDTO = null;
        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id, " +
                             "login, " +
                             "password, " +
                             "first_name, " +
                             "middle_name, " +
                             "last_name, " +
                             "birthday, " +
                             "registration_timestamp, " +
                             "role_id " +
                             "FROM app.users " +
                             "WHERE id = ?;");) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {
                    userDTO = initializeUser(rs);
                }

            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return userDTO;
    }

    @Override
    public UserDTO create(UserDTO item) {
        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO app.users(" +
                             "login, " +
                             "password, " +
                             "first_name, " +
                             "middle_name, " +
                             "last_name, " +
                             "birthday, " +
                             "registration_timestamp, " +
                             "role_id)" +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                             "RETURNING id;")) {

            preparedStatement.setString(1, item.getLogin());
            preparedStatement.setString(2, item.getPassword());
            preparedStatement.setString(3, item.getFirstName());
            preparedStatement.setString(4, item.getMiddleName());
            preparedStatement.setString(5, item.getLastName());
            preparedStatement.setDate(6, Date.valueOf(item.getBirthday()));
            preparedStatement.setTimestamp(7, Timestamp.valueOf(item.getRegistrationTimestamp()));
            preparedStatement.setInt(8, item.getRole().getRole_id());

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    item.setId(rs.getInt("id"));
                }
            }

        } catch (
                SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }
        return item;
    }

    @Override
    public UserDTO get(String login) {
        UserDTO userDTO = null;
        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id, " +
                             "login, " +
                             "password, " +
                             "first_name, " +
                             "middle_name, " +
                             "last_name, " +
                             "birthday, " +
                             "registration_timestamp, " +
                             "role_id " +
                             "FROM app.users " +
                             "WHERE login = ?;");) {

            preparedStatement.setString(1, login);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {
                    userDTO = initializeUser(rs);
                }

            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return userDTO;
    }

    private static UserDTO initializeUser(ResultSet rs) throws SQLException {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(rs.getInt("id"));
        userDTO.setLogin(rs.getString("login"));
        userDTO.setPassword(rs.getString("password"));
        userDTO.setFirstName(rs.getString("first_name"));
        userDTO.setMiddleName(rs.getString("middle_name"));
        userDTO.setLastName(rs.getString("last_name"));
        userDTO.setBirthday(rs.getTimestamp("birthday").toLocalDateTime().toLocalDate());
        userDTO.setRegistrationTimestamp(rs.getTimestamp("registration_timestamp").toLocalDateTime());
        userDTO.setRole(Role.fromRoleId(rs.getInt("role_id")));

        return userDTO;
    }
}
