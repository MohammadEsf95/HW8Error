package entity;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "address")
public class Address extends entity.Entity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country",nullable = false)
    private String country;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "street",nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private Long number;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address() {
    }

    public Address(String country,String city,String street, Long number){
        this.country = country;
        this.city=city;
        this.street = street;
        this.number = number;
    }
}
