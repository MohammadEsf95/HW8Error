package usecases.impl;

import config.HibernateUtil;
import entity.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import usecases.usecase.AddAddress;

import java.util.Scanner;

public class AddAddressImpl implements AddAddress {
    @Override
    public void add() {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        System.out.println("country: ");
        String country = scanner.nextLine();
        System.out.println("city: ");
        String city =scanner.nextLine();
        System.out.println("street: ");
        String street = scanner.nextLine();
        System.out.println("number: ");
        Long number = Long.parseLong(scanner.nextLine());
        Address address = new Address(country,city,street,number);

        session.save(address);
        session.getTransaction().commit();
        session.close();
    }
}
