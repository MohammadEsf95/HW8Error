package usecases.impl.adminImpl;

import config.HibernateUtil;
import entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.admin.CreateCategory;

import java.util.Scanner;

public class CreateCategoryImpl implements CreateCategory {
    @Override
    public void createCategory() {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("Enter category title: ");
        String catTitle = scanner.nextLine();
        System.out.println("Enter category description: ");
        String catDes = scanner.nextLine();
        Category category = new Category(catTitle,catDes);

        session.save(category);
        session.getTransaction().commit();
        session.close();
    }
}
