package by.pet_project.ens.dao.db.ds;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnectionFactory {
    private static final String FILE_WITH_PROPERTIES = "db.properties";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL_PARAM_NAME = "db_url";
    private static ComboPooledDataSource cpds;

    static {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream in = classLoader.getResourceAsStream(FILE_WITH_PROPERTIES)) {

            properties.load(in);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл " + FILE_WITH_PROPERTIES + " не найден.", e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении из файла " + FILE_WITH_PROPERTIES, e);
        }

        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(DB_DRIVER); //loads the jdbc driver
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        cpds.setJdbcUrl(properties.getProperty(DB_URL_PARAM_NAME));
        cpds.setProperties(properties);


    }

    private DataBaseConnectionFactory() {

    }

    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
}
