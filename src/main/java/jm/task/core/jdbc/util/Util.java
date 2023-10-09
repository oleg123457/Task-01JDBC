package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    // реализуйте настройку соеденения с БД
    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
}