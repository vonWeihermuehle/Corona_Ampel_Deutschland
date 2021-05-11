package net.mbmedia.db;

import net.mbmedia.Konfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Properties;

import static net.mbmedia.Konfiguration.*;


public class BaseJPA {

    private static final Logger log = LoggerFactory.getLogger(BaseJPA.class);

    protected static EntityManagerFactory entityManagerFactory;

    public void setup(){
        if(entityManagerFactory == null){
            entityManagerFactory = createEntityManagerFactory();
        }
    }

    public EntityManager getEntityManager(){
        setup();
        return entityManagerFactory.createEntityManager();
    }

    public void closeEntitiyManager(EntityManager em){
        while(em.isOpen())
        {
            em.close();
        }
    }

    public EntityTransaction startTransaction(EntityManager em){
        EntityTransaction et = em.getTransaction();
        et.begin();
        return et;
    }


    @Autowired
    private Environment env;


    public EntityManagerFactory createEntityManagerFactory(){
        Properties properties = new Properties();
        properties.put("javax.persistence.provider", "org.hibernate.ejb.HibernatePersistence");
        properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
        properties.put("hibernate.connection.username", USER);
        properties.put("hibernate.connection.password", PASSWORD);
        properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.put("hibernate.connection.url", JDBC);
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.show_sql", false);
        properties.put("hibernate.format_sql", "true");

        return Persistence.createEntityManagerFactory("persistence", properties);
    }

}
