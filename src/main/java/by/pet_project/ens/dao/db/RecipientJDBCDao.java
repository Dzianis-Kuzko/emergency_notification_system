package by.pet_project.ens.dao.db;

import by.pet_project.ens.core.dto.Contact;
import by.pet_project.ens.core.dto.RecipientDTO;
import by.pet_project.ens.dao.api.AccessDataException;
import by.pet_project.ens.dao.api.IRecipientDao;
import by.pet_project.ens.dao.db.ds.DataBaseConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipientJDBCDao implements IRecipientDao {
    @Override
    public List<RecipientDTO> get() {
        List<RecipientDTO> recipientDTOList = new ArrayList<>();
        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id, " +
                             "first_name, " +
                             "last_name, " +
                             "country, " +
                             "city, " +
                             "created_by_user_with_id, " +
                             "phone_number, " +
                             "email, " +
                             "telegram, " +
                             "viber " +
                             "FROM app.recipients;");
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                RecipientDTO recipientDTO = initializeRecipient(rs);
                recipientDTOList.add(recipientDTO);
            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return recipientDTOList;
    }

    @Override
    public RecipientDTO get(int id) {
        RecipientDTO recipientDTO = null;

        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id, " +
                             "first_name, " +
                             "last_name, " +
                             "country, " +
                             "city, " +
                             "created_by_user_with_id, " +
                             "phone_number, " +
                             "mail, " +
                             "telegram, " +
                             "viber " +
                             "FROM app.recipients " +
                             "WHERE id = ?;")) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {
                    recipientDTO = initializeRecipient(rs);
                }

            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return recipientDTO;
    }

    @Override
    public RecipientDTO create(RecipientDTO item) {
        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO app.recipients(first_name, " +
                             "last_name, " +
                             "country, " +
                             "city, " +
                             "created_by_user_with_id, " +
                             "phone_number, " +
                             "email, " +
                             "telegram, " +
                             "viber) " +
                             "VALUES( ?, ?,?,?,?,?,?,?,?)" +
                             "RETURNING id;")) {

            preparedStatement.setString(1, item.getFirstName());
            preparedStatement.setString(2, item.getLastName());
            preparedStatement.setString(3, item.getCountry());
            preparedStatement.setString(4, item.getCity());
            preparedStatement.setInt(5, item.getCreatedByUserWithID());
            preparedStatement.setString(6, item.getContact().getPhoneNumber());
            preparedStatement.setString(7, item.getContact().getEmail());
            preparedStatement.setString(8, item.getContact().getTelegram());
            preparedStatement.setString(9, item.getContact().getViber());

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
    public List<Integer> getIds(int createdByUserWithID) {
        List<Integer> idList = new ArrayList<>();
        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id " +
                             "FROM app.recipients " +
                             "WHERE created_by_user_with_id = ?;")) {

            preparedStatement.setInt(1, createdByUserWithID);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    idList.add(rs.getInt("id"));
                }
            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return idList;
    }

    private static RecipientDTO initializeRecipient(ResultSet rs) throws SQLException {
        RecipientDTO recipientDTO = new RecipientDTO();
        recipientDTO.setId(rs.getInt("id"));
        recipientDTO.setFirstName(rs.getString("first_name"));
        recipientDTO.setLastName(rs.getString("last_name"));
        recipientDTO.setCountry(rs.getString("country"));
        recipientDTO.setCity(rs.getString("city"));
        recipientDTO.setCreatedByUserWithID(rs.getInt("created_by_user_with_id"));

        Contact contact = new Contact();
        contact.setPhoneNumber(rs.getString("phone_number"));
        contact.setEmail(rs.getString("email"));
        contact.setTelegram(rs.getString("telegram"));
        contact.setViber(rs.getString("viber"));
        recipientDTO.setContact(contact);

        return recipientDTO;
    }

}
