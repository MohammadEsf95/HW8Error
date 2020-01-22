package usecases.impl.adminImpl;

import config.HibernateUtil;
import entity.Role;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.admin.ChangeUserRole;

import java.util.List;
import java.util.Scanner;

public class ChangeUserRoleImpl implements ChangeUserRole {
    @Override
    public void change() {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> users = session.createQuery("from User")
                .list();
//        System.out.println(users.toString());
//        for (User user: users){
//            System.out.println(user.toString());
//        }
        System.out.println("Select a user: ");
        Long id = scanner.nextLong();
        User user = session.load(User.class,id);
        System.out.println("Which role do you want to set (admin = 1, writer = 2): ");
        Long roleId = scanner.nextLong();
        Role role = session.load(Role.class,roleId);
        List<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        session.getTransaction().commit();
        session.close();

    }
}
