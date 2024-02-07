package fr.moussalli.slackdej.entity;

import fr.moussalli.slackdej.entity.Channel;
import fr.moussalli.slackdej.entity.Post;
import fr.moussalli.slackdej.entity.User;
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

    @Test // le channel a besoin d'un user pour être sauvé dans la BDD car NOT NULL
    void addChannelUser() {
        User user1 = new User("John P", "johnP@jpj.com");
        userRepository.save(user1);

        List<Channel> channels = user1.getChannels();
        Channel channel1 = new Channel();
        channel1.setUser(user1);
        channel1.setName("canal1");
        channelRepository.save(channel1);
        channels.add(channel1);
        user1.setChannels(channels);
        userRepository.save(user1);

    }

    @Test  // Attention à l'ordre des save
    void addPostUser(){
        User user1 = new User("John P", "johnP@jpj.com");
        userRepository.save(user1);

        List<Post> posts = user1.getPosts();
        Post post1 = new Post("bjr", Date.valueOf(LocalDate.now()));
        posts.add(post1);
        user1.setPosts(posts);
        userRepository.save(user1);






        user1.setPosts(posts);
        userRepository.save(user1);



    }
}
