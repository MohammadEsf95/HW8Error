package usecases.impl.adminImpl;

import config.HibernateUtil;
import entity.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.admin.Unpublish;

import java.util.List;

public class UnpublishImpl implements Unpublish {
    @Override
    public void publish(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean isPublished = true;

        List<Long> articles = session.createQuery("select id from Article")
                .list();
        for (Long articleId:articles){
            if(articleId == id){
                Article article = session.load(Article.class,articleId);
                isPublished = false;
                if(isPublished){
                    article.setIsPublished("no");
                }
            }
        }
        session.getTransaction().commit();
        session.close();
    }
}
