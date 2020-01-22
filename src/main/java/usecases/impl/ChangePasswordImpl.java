package usecases.impl;

import config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.ChangePassword;

import javax.persistence.Query;
import java.util.Scanner;

public class ChangePasswordImpl implements ChangePassword {
    @Override
    public void change(String username) {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("Enter your new password: ");
        String newPass = scanner.nextLine();
        Query query = session.createQuery("update User set password = :password where username = : username");
        query.setParameter("username",username);
        query.setParameter("password", newPass);
        query.executeUpdate();


        session.getTransaction().commit();
        session.close();
    }
}
