package usecases.impl.adminImpl;

import config.HibernateUtil;
import entity.Role;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

public class AdminDefine {
    public boolean isAdmin(User user){
        boolean isAdmin = false;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Role role = session.load(Role.class,1L);
        List<User> admins = role.getUsers();
        for(User admin:admins){
            if(admin.getId()==user.getId()){
                isAdmin = true;
            }
        }

        session.getTransaction().commit();
        session.close();
        return isAdmin;
    }
}
