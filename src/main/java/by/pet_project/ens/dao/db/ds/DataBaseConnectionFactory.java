package by.pet_project.ens.dao.db.ds;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnectionFactory {
    private static final String FILE_WITH_PROPERTIES = "db.properties";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL_PARAM_NAME = "db_url";

    static {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Ошибка, драйвер не найден", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream in = classLoader.getResourceAsStream(FILE_WITH_PROPERTIES)) {

            properties.load(in);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл " + FILE_WITH_PROPERTIES + " не найден.", e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении из файла " + FILE_WITH_PROPERTIES, e);
        }

        return DriverManager.getConnection(properties.getProperty(DB_URL_PARAM_NAME), properties);
    }
}
