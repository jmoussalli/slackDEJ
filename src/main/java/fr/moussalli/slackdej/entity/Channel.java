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

    @Column(nullable = false)
    private Boolean isDeletable = true;

    @OneToMany(mappedBy ="channel" ,cascade = CascadeType.ALL, orphanRemoval = true)
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

    public Channel(String name, User user, List<Post> posts) {
        this.name = name;
        this.user = user;
        this.posts = posts;
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

    public Boolean isDeletable() {
        return isDeletable;
    }

    public void setisDeletable(Boolean isDeletable) {
        this.isDeletable = isDeletable;
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

    public List<Post> addPost(Post post) {
        this.posts.add(post);
        return this.posts;
    }

    public void removePost(Post post) {
        posts.remove(post);
        post.setChannel(null);
    }

    public void updatePosts(List<Post> updatedPosts) {
        this.getPosts().clear();
        updatedPosts.forEach(this::addPost);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDeletable=" + isDeletable +
                ", posts=" + posts +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel channel)) return false;

        if (!getName().equals(channel.getName())) return false;
        if (!getPosts().equals(channel.getPosts())) return false;
        return getUser().equals(channel.getUser());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getPosts().hashCode();
        result = 31 * result + getUser().hashCode();
        return result;
    }
}
