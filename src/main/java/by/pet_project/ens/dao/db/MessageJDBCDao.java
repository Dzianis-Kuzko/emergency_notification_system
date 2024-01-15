package by.pet_project.ens.dao.db;

import by.pet_project.ens.core.dto.MessageDTO;
import by.pet_project.ens.dao.api.AccessDataException;
import by.pet_project.ens.dao.api.IMessageDao;
import by.pet_project.ens.dao.db.ds.DataBaseConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageJDBCDao implements IMessageDao {
    @Override
    public List<MessageDTO> get() {
        List<MessageDTO> messageDTOList = new ArrayList<>();
        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT " +
                             "id, " +
                             "message_template_id, " +
                             "from_user_id, " +
                             "creation_timestamp " +
                             "FROM app.messages;");
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                MessageDTO messageDTO = initializeMessage(rs);
                messageDTOList.add(messageDTO);
            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return messageDTOList;
    }

    @Override
    public MessageDTO get(int id) {
        MessageDTO messageDTO = null;

        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id, " +
                             "message_template_id, " +
                             "from_user_id, " +
                             "creation_timestamp " +
                             "FROM app.messages " +
                             "WHERE id = ?;")) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {
                    messageDTO = initializeMessage(rs);
                }

            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return messageDTO;
    }

    @Override
    public MessageDTO create(MessageDTO item) {
        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatementMessages = connection.prepareStatement(
                     "INSERT INTO app.messages(" +
                             "message_template_id, " +
                             "from_user_id, " +
                             "creation_timestamp) " +
                             "VALUES ( ?, ?, ?) " +
                             "RETURNING id;")) {

            preparedStatementMessages.setInt(1, item.getMessageTemplateId());
            preparedStatementMessages.setInt(2, item.getFromUserId());
            preparedStatementMessages.setTimestamp(3, Timestamp.valueOf(item.getCreationTimestamp()));

            try (ResultSet rs = preparedStatementMessages.executeQuery()) {
                if (rs.next()) {
                    item.setId(rs.getInt("id"));
                }
            }

            for (int recipientId : item.getToRecipientsId()) {
                try (PreparedStatement preparedStatementMessageRecipient = connection.prepareStatement(
                        "INSERT INTO app.message_recipient( " +
                                "message_id, " +
                                "recipient_id) " +
                                "VALUES (?, ?);")) {
                    preparedStatementMessageRecipient.setInt(1, item.getId());
                    preparedStatementMessageRecipient.setInt(2, recipientId);
                    preparedStatementMessageRecipient.execute();

                }
            }
        } catch (
                SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }
        return item;
    }

    private static MessageDTO initializeMessage(ResultSet rs) throws SQLException {
        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setId(rs.getInt("id"));
        messageDTO.setMessageTemplateId(rs.getInt("message_template_id"));
        messageDTO.setFromUserId(rs.getInt("from_user_id"));
        messageDTO.setToRecipientsId(getToRecipientsId(messageDTO.getId()));
        messageDTO.setCreationTimestamp(rs.getTimestamp("creation_timestamp").toLocalDateTime());

        return messageDTO;
    }

    private static List<Integer> getToRecipientsId(int id) throws SQLException {
        List<Integer> toRecipientsId = new ArrayList<>();
        try (Connection connection = DataBaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT recipient_id " +
                             "FROM app.message_recipient " +
                             "WHERE message_id = ?;")) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Integer recipientId = rs.getInt("recipient_id");
                    toRecipientsId.add(recipientId);
                }
            }
        }
        return toRecipientsId;
    }
}