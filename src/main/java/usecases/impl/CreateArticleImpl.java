package usecases.impl;

import config.HibernateUtil;
import entity.Article;
import entity.Category;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.CreateArticle;

import java.util.List;
import java.util.Scanner;

public class CreateArticleImpl implements CreateArticle {
    @Override
    public void create(User user, String currentDate) {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean categoryExist = false;
        Long categoryId = null;

        System.out.println("category id: ");

        while (!categoryExist){
            categoryId = Long.parseLong(scanner.nextLine());
            List categoryIds = session.createQuery("select id from Category")
                    .list();
            for (Object obj: categoryIds){
                if(obj==categoryId){
                    categoryExist = true;
                    break;
                }
            }
            if (!categoryExist){
                System.out.println("category not found");
            }
        }



        System.out.println("title: ");
        String title = scanner.nextLine();
        System.out.println("brief: ");
        String brief = scanner.nextLine();
        System.out.println("content: ");
        String content = scanner.nextLine();
//        System.out.println("create date: ");
//        String createDate = currentDate;
        String isPublished = "no";
        String lastUpdateDate = currentDate;

        List<Category> categoryList = session.createQuery("from Category where id=: id")
                .setParameter("id",categoryId)
                .list();
        Category category = categoryList.get(0);
        Article article = new Article(user,category,title,brief,content,currentDate,isPublished);


        session.save(article);
        session.getTransaction().commit();
        session.close();
    }
}
