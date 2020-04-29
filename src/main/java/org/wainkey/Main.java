/*
 *     Copyright © Kashin Vladimir Olegovich (Wain Key), 2020.
 *     This project is a part of a graduation qualification work of Bauman Moscow State Technical University (BMSTU) student.
 *     If You use this project or a part of it for your own purposes please make sure that You indicated authorship.
 */

package org.wainkey;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerFactory;
import sun.rmi.runtime.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/***
 * Тест подключения к БД
 */

public class Main {
    public static void main(String... args) throws FileNotFoundException {
//        FileInputStream file = new FileInputStream("C:\\Users\\Владимир\\Documents\\IDEA Projects\\graduationProject\\src\\main\\resources\\log4j.properties");
//        PropertyConfigurator.configure(file);
        Logger LOG = Logger.getLogger(Main.class);
        LOG.getAllAppenders();
        String url = "";
        String user = "";
        String password = "";
        try {
            LOG.info("Подключаю файл db.properties");
            FileInputStream fis = new FileInputStream("src/main/resources/db.properties");
            Properties properties = new Properties();
            properties.load(fis);
            url = properties.getProperty("jdbc.url");
            user = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");

            LOG.info("Попытка подключения к БД...");
            Connection connection = DriverManager.getConnection(url, user, password);
            LOG.info("Подключено успешно!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOG.error("Файл свойств не найден!");
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Не могу загрузить properties!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            LOG.error(throwables);
        }
    }
}
