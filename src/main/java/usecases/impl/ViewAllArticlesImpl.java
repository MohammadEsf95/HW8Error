package usecases.impl;

import config.HibernateUtil;
import entity.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import usecases.usecase.ViewAllArticles;

import java.util.List;

public class ViewAllArticlesImpl implements ViewAllArticles {
    @Override
    public void showAllArticles() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Article ");
        List<Article> articles = query.list();
        System.out.printf("\n%-5s%-20s%-30s\n%s\n", "id", "title", "brief",
                "==================================================================");
        for (Article article : articles) {
            article.printBrief();
        }
        System.out.println("==================================================================");

        session.getTransaction().commit();
        session.close();

    }
}
