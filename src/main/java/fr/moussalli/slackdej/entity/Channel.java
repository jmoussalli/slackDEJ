package fr.moussalli.slackdej.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "channels")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy ="channel" ,cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @ManyToOne
    private User user;

    public Channel() {
    }

    public Channel(String name) {
        this.name = name;
    }

    public Channel(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
       return user;
   }

    public void setUser(User user) {
         this.user = user;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", posts=" + posts +
                ", user=" + user +
                '}';
    }
}
