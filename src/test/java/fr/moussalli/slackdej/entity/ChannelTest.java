package fr.moussalli.slackdej.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelTest {

    @Test
    void getName() {
        Channel channel = new Channel();
        channel.setName("test");
        assertEquals("test", channel.getName());
    }

    @Test
    void getUser() {
        User user = new User("John Doe", "jon@doe.org");
        Channel channel = new Channel("test", user);
        assertEquals(user, channel.getUser());
    }

    @Test
    void getPosts() {
        Channel channel = new Channel("test");
        Post post = new Post("Hello !", Date.valueOf(LocalDate.now()));
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        channel.setPosts(posts);
        assertEquals(posts, channel.getPosts());
    }

}
