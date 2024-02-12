package by.pet_project.ens.dao.db;

import by.pet_project.ens.core.dto.MessageTemplateDTO;
import by.pet_project.ens.dao.api.AccessDataException;
import by.pet_project.ens.dao.api.IMessageTemplateDao;
import by.pet_project.ens.dao.db.ds.DataBaseConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageTemplateJDBCDao implements IMessageTemplateDao {
    @Override
    public List<MessageTemplateDTO> get() {
        List<MessageTemplateDTO> messageTemplateDTOList = new ArrayList<>();
        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id, " +
                             "text, " +
                             "creation_timestamp, " +
                             "created_by_user_with_id " +
                             "FROM app.message_templates;");
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                MessageTemplateDTO messageTemplateDTO = initializeMessageTemplate(rs);
                messageTemplateDTOList.add(messageTemplateDTO);
            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return messageTemplateDTOList;
    }

    @Override
    public MessageTemplateDTO get(int id) {
        MessageTemplateDTO messageTemplateDTO = null;

        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id, " +
                             "text, " +
                             "creation_timestamp, " +
                             "created_by_user_with_id " +
                             "FROM app.message_templates " +
                             "WHERE id = ?;")) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {
                    messageTemplateDTO = initializeMessageTemplate(rs);
                }

            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return messageTemplateDTO;
    }

    @Override
    public MessageTemplateDTO create(MessageTemplateDTO item) {
        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO app.message_templates( text, " +
                             "creation_timestamp, " +
                             "created_by_user_with_id) " +
                             "VALUES (?, ?, ?) " +
                             "RETURNING id;")) {

            preparedStatement.setString(1, item.getText());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(item.getCreationTimestamp()));
            preparedStatement.setInt(3, item.getCreatedByUserWithID());

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    item.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }
        return item;
    }

    @Override
    public List<MessageTemplateDTO> getUserMessageTemplates(int createdByUserWithID) {
        List<MessageTemplateDTO> messageTemplateDTOList = new ArrayList<>();

        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id, " +
                             "text, " +
                             "creation_timestamp, " +
                             "created_by_user_with_id " +
                             "FROM app.message_templates " +
                             "WHERE created_by_user_with_id = ?;")) {

            preparedStatement.setInt(1, createdByUserWithID);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    MessageTemplateDTO messageTemplateDTO = initializeMessageTemplate(rs);
                    messageTemplateDTOList.add(messageTemplateDTO);
                }
            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return messageTemplateDTOList;
    }

    private static MessageTemplateDTO initializeMessageTemplate(ResultSet rs) throws SQLException {
        MessageTemplateDTO messageTemplateDTO = new MessageTemplateDTO();

        messageTemplateDTO.setId(rs.getInt("id"));
        messageTemplateDTO.setText(rs.getString("text"));
        messageTemplateDTO.setCreationTimestamp(rs.getTimestamp("creation_timestamp").toLocalDateTime());
        messageTemplateDTO.setCreatedByUserWithID(rs.getInt("created_by_user_with_id"));

        return messageTemplateDTO;
    }
}
