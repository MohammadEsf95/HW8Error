package usecases.impl;

import config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.ViewArticles;

import java.util.List;
import java.util.Scanner;

public class ViewArticlesImpl implements ViewArticles {
    @Override
    public void showAll(Long id) {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List showArticles = session.createQuery("from Article where User .id =: id")
                .setParameter("id",id)
                .list();
        if (showArticles.size() > 0) {
            System.out.println("\n" +
                    " Article\n==========================================");
            for (Object article : showArticles) {
                System.out.println(article.toString());
            }
            System.out.println("==========================================");
        } else {
            System.out.println("Article not found");
        }



        session.getTransaction().commit();
        session.close();
    }
}
