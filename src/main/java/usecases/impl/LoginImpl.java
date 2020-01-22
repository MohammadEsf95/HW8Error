package usecases.impl;

import config.HibernateUtil;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.Login;

import java.util.List;
import java.util.Scanner;

public class LoginImpl implements Login {
    @Override
    public User login() {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        List dbPass = session.createQuery("select password from User where username=: username")
                .setParameter("username", username)
                .list();
        List dbUsername = session.createQuery("from User where username= :username").
                setParameter("username",username)
                .list();

        if(dbUsername.size()==1&& password.equals(dbPass.get(0))){
            user =(User) dbUsername.get(0);
        }


        session.getTransaction().commit();
        session.close();
        return user;
    }
}
