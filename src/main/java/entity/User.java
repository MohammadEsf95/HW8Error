package entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "national_code",nullable = false,length = 10)
    private Long nationalCode;

    @Column(name = "birthday_date",nullable = false,length = 10)
    private String birthday;

    @Column(name = "password",nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Article> articleList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    List<Role> roles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Address> addresses = new ArrayList<>();

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public User(){}

    public User(String username, Long nationalCode, String birthday, String password){
        this.username = username;
        this.nationalCode = nationalCode;
        this.birthday = birthday;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Long nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nationalCode=" + nationalCode +
                ", birthday='" + birthday + '\'' +
                ", password='" + password + '\'' +
                ", articleList=" + articleList +
                '}';
    }
}
