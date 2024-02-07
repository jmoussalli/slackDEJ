package fr.moussalli.slackdej.entity;

import fr.moussalli.slackdej.repository.ChannelRepository;
import fr.moussalli.slackdej.repository.PostRepository;
import fr.moussalli.slackdej.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ChannelTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Test
    void getName() {
        Channel channel = new Channel();
        channel.setName("test");
        Channel channelSaved = channelRepository.save(channel);
        assertEquals("test", channelSaved.getName());
    }

    @Test
    void getUser() {
        User user = new User("John Doe", "jon@doe.org");
        userRepository.save(user);
        Channel channel = new Channel("test", user);
        Channel channelSaved = channelRepository.save(channel);
        assertEquals(user, channelSaved.getUser());
    }

    @Test
    void getPosts() {
        User user = new User("John Doe", "john@doe.org");
        userRepository.save(user);
        Channel channel = new Channel("test", user);
        Channel channelSaved = channelRepository.save(channel);
        Post post = new Post("Hello !", Date.valueOf(LocalDate.now()));
        postRepository.save(post);
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        channel.setPosts(posts); // Associate the channel with the posts
        channelRepository.save(channel);
        assertEquals(posts, channel.getPosts());
    }

    @Test
    void addChannelToUser() {
        Channel channel = new Channel();
        channel.setName("testAddChannelToUser");
        Channel channelSaved = channelRepository.save(channel);
        User user = new User("John Doe", "john@doe.org");
        User userSaved = user.addChannel(channelSaved);
        userRepository.save(user);
        assertTrue(userSaved.getChannels().contains(channelSaved));
    }

    @Test
    void addChannelToPost() {
        Channel channel = new Channel();
        channel.setName("testAddChannelToUser");
        Channel channelSaved = channelRepository.save(channel);

        Post post = new Post("Hello ! testAddChannelToUser", Date.valueOf(LocalDate.now()));
        post.setChannel(channelSaved);
        postRepository.save(post);

        channel.getPosts().add(post); // Associate the channel with the posts
        channelRepository.save(channel);

        assertEquals(post.getChannel(),channelSaved);
    }

}
