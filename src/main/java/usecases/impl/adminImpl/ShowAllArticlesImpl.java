package usecases.impl.adminImpl;

import config.HibernateUtil;
import entity.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.admin.ShowAllArticles;

import java.util.List;

public class ShowAllArticlesImpl implements ShowAllArticles {
    @Override
    public void showAllArticles() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Article> articleList = session.createQuery("from Article ")
                .list();
        for(Article article: articleList){
            if(article.getIsPublished().equalsIgnoreCase("yes")){
                System.out.println(article);
            }
        }

        session.getTransaction().commit();
        session.close();
    }
}
