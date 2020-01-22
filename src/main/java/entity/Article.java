package entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Article")
public class Article extends entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "brief")
    private String brief;

    @Column(name = "content")
    private String content;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "last_update_date")
    private String lastUpdateDate;

    @Column(name = "publish_date")
    private String publishDate;

    @Column(name = "is_published")
    private String isPublished;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "article_tags",
            joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    List<Tag> tags = new ArrayList<>();

    public Article() {
    }

    public Article(User user,Category category,String title,String brief,String content,String createDate, String isPublished){
        this.user = user;
        this.category = category;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.createDate = createDate;
        this.isPublished = isPublished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(String isPublished) {
        this.isPublished = isPublished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                ", lastUpdateDate='" + lastUpdateDate + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", isPublished='" + isPublished + '\'' +
                ", user=" + user +
                ", category=" + category +
                '}';
    }

    public void printBrief(){
        System.out.printf("%-5d%-20s%-30s\n",id,title,brief);
    }
}
