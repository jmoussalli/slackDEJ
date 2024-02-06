package fr.moussalli.slackdej.entity;

import fr.moussalli.slackdej.repository.ChannelRepository;
import fr.moussalli.slackdej.repository.PostRepository;
import fr.moussalli.slackdej.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChannelRepository channelRepository;


//    @BeforeAll
//    public static void loadEnvVariables() throws IOException {
//        Path envPath = Paths.get(".env");
//        Stream<String> lines = Files.lines(envPath);
//        lines.forEach(line -> {
//            String[] parts = line.split("=", 2);
//            if (parts.length == 2) {
//                System.setProperty(parts[0], parts[1]);
//            }
//        });
//    }

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
        User user = new User("John Doe", "jon@doe.org");
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

}
