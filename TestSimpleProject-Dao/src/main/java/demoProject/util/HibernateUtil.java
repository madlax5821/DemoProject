package demoProject.util;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

import java.util.Properties;

public class HibernateUtil {
    private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory;

    private HibernateUtil(){};
    private static HibernateUtil hibernateUtil;
    public static HibernateUtil getHibernateUtil(){
        if (hibernateUtil==null){
            hibernateUtil = new HibernateUtil();
        }
        return hibernateUtil;
    }
    static{
        try {
            sessionFactory = HibernateUtil.loadSessionFactory();
        }catch (Exception e){
            logger.error("Exception while initializing hibernate util...");
            e.printStackTrace();
        }
    }
    private static SessionFactory loadSessionFactory() {
        String dbDriver = System.getProperty("database.driver");
        String dbDialect = System.getProperty("database.dialect");
        String dbUrl = System.getProperty("database.url");
        String dbUserName = System.getProperty("database.userName");
        String dbPassword = System.getProperty("database.password");
        String[] modelPackage = {"demoProject.model"};

        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, dbDriver);
        settings.put(Environment.DIALECT, dbDialect);
        settings.put(Environment.URL, dbUrl);
        settings.put(Environment.USER, dbUserName);
        settings.put(Environment.PASS, dbPassword);
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "validate");
        configuration.setProperties(settings);

        EntityScanner.scanPackages(modelPackage).addTo(configuration);
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        ServiceRegistry serviceRegistry = registryBuilder.applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
    public static SessionFactory getSessionFactory(){
        return loadSessionFactory();
    }

    public static Session getSession(){
        Session session = null;
        try{
            session = sessionFactory.openSession();
        }catch (Throwable t){
            logger.error("Exception while getting session..");
            t.printStackTrace();
        }
        if (session==null){
            logger.error("session is discovered null");
        }
        return session;
    }
}
