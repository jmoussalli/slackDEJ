package fr.moussalli.slackdej.entity;

import fr.moussalli.slackdej.repository.ChannelRepository;
import fr.moussalli.slackdej.repository.PostRepository;
import fr.moussalli.slackdej.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    void addUser() {
        User user1 = new User("John P", "johnP@jpj.com");
        userRepository.save(user1);
    }

    @Test
    void addChannelUser() {
        User user1 = new User("John P", "johnP@jpj.com");

        List<Channel> channels = user1.getChannels();
        Channel channel1 = new Channel();
        channels.add(channel1);
        user1.setChannels(channels);
        userRepository.save(user1);

    }

    @Test
    void addPostUser() {
        User user1 = new User("John P", "johnP@jpj.com");
        Post post1 = new Post();
        List<Post> posts = user1.getPosts();
        posts.add(post1);
        user1.setPosts(posts);
        userRepository.save(user1);


    }
}
