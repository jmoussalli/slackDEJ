package fr.moussalli.slackdej.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Date postDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    @JsonIgnore
    private Channel channel;


    public Post() {
    }

    public Post(String message, Date postDateTime) {
        this.message = message;
        this.postDateTime = postDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(Date postDateTime) {
        this.postDateTime = postDateTime;
    }

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public Channel getChannel() {return channel;}

    public void setChannel(Channel channel) {this.channel = channel;}

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", postDateTime=" + postDateTime +
                '}';
    }
}

