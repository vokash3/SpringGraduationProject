/*
 *     Copyright © Kashin Vladimir Olegovich (Wain Key), 2020.
 *     This project is a part of a graduation qualification work of Bauman Moscow State Technical University (BMSTU) student.
 *     If You use this project or a part of it for your own purposes please make sure that You indicated authorship.
 */

package org.wainkey.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "org.wainkey")
@EnableTransactionManagement //позволяет использовать TransactionManager для управления транзакциями.
@PropertySource(value = "classpath:db.properties") // подключение файла свойств, который мы недавно создавали.
public class HibernateConfig {
    private Environment environment; //для того, чтобы получить свойства из property файла.

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment; //для того, чтобы получить свойства из property файла.
    }

    /***
     * этот метод нужен чтобы представить свойства Hibernate в виде объекта Properties
     * @return
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        return properties;
    }

    /***
     * используется для создания соединения с БД. Это альтернатива DriverManager
     *
     * Небольшое замечание насчет DataSource.
     * В документации сказано, что использовать стандартную реализацию,
     * а именно DriverManagerDataSource, не рекомендуется,
     * т.к. это только замена нормального пула соединений
     * и в целом подходит только для тестов и всего такого.
     * Для нормального приложения предпочтительнее использовать
     * какую-нибудь DBCP библиотеку. Используем томкатовскую (в pom.xml)
     * @return
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    /***
     * для создания сессий, с помощью которых осуществляются операции с объектами-сущностями.
     * Здесь мы устанавливаем источник данных, свойства Hibernate
     * и в каком пакете нужно искать классы-сущности.
     * @return
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("org.wainkey.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }
    /***
     * для настройки менеджера транзакций.
     * @return
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}