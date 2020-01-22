package usecases.impl.adminImpl;

import config.HibernateUtil;
import entity.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.admin.DeleteArticle;

import java.util.List;

public class DeleteArticleImpl implements DeleteArticle {
    @Override
    public void deleteArticle(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Long> articles = session.createQuery("select id from Article ")
                .list();
        for (Long articleId: articles){
            if(articleId == id){
                Article article = session.load(Article.class,articleId);
                session.remove(article);
                System.out.println("Article has removed successfully");
            }else {
                System.out.println("Article not found");
            }
        }

        session.getTransaction().commit();
        session.close();
    }
}
