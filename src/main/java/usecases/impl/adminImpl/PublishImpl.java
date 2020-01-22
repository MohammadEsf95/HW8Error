package usecases.impl.adminImpl;

import config.HibernateUtil;
import entity.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.admin.Publish;

import java.util.List;

public class PublishImpl implements Publish {
    @Override
    public void publish(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean isPublished = false;

        List<Long> articles = session.createQuery("select id from Article")
                .list();
        for (Long articleId:articles){
            if(articleId == id){
                Article article = session.load(Article.class,articleId);
                isPublished = true;
                if(isPublished){
                    article.setIsPublished("yes");
                }
            }
        }
        session.getTransaction().commit();
        session.close();
    }
}
