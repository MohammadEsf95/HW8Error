package usecases.impl;

import config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.StarterUsecase;

public class StarterUsecaseImpl implements StarterUsecase {
    @Override
    public void start() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("Hello");

        session.getTransaction().commit();
        session.close();
    }
}
