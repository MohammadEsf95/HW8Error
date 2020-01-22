package usecases.impl.adminImpl;

import config.HibernateUtil;
import entity.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.admin.CreateTag;

import java.util.Scanner;

public class CreateTagImpl implements CreateTag {
    @Override
    public void createTag() {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("Tag title: ");
        String tagTitle ="#" + scanner.nextLine();
        Tag tag = new Tag();
        tag.setTitle(tagTitle);

        session.save(tag);
        session.getTransaction().commit();
        session.close();
    }
}
