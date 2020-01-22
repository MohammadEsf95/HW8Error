package usecases.impl;

import config.HibernateUtil;
import entity.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.EditArticleText;

import java.util.List;
import java.util.Scanner;

public class EditArticleTextImpl implements EditArticleText {
    @Override
    public void edit(Long id, String lastUpdateDate) {
        Scanner scanner = new Scanner(System.in);
        boolean idExist = false;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        List<Long> idList = session.createQuery("select id from Article")
                .list();
        for (Long articleId:idList){
            if(id == articleId){
                idExist = true;
            }else {
                System.out.println("id not found!");
            }
        }
        Article article = session.load(Article.class,id);
        if (idExist) {
            System.out.println("enter column's name ( title | brief | content ):");
            String columnName = scanner.nextLine();

            if (columnName.equalsIgnoreCase("title")) {
                System.out.println("enter new title: ");
                String newTitle = scanner.nextLine();
                article.setTitle(newTitle);

            } else if (columnName.equalsIgnoreCase("brief")) {
                System.out.println("enter new brief: ");
                String newBrief = scanner.nextLine();
                article.setBrief(newBrief);

            } else if (columnName.equalsIgnoreCase("content")) {
                System.out.println("enter new content: ");
                String newContent = scanner.nextLine();
                article.setContent(newContent);

            } else {
                System.out.println("invalid column name !!!");
            }
            article.setLastUpdateDate(lastUpdateDate);
            session.update(article);
        }
        session.getTransaction().commit();
        session.close();
    }
}
