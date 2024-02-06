package fr.moussalli.slackdej;

import fr.moussalli.slackdej.entity.Channel;
import fr.moussalli.slackdej.entity.Post;
import fr.moussalli.slackdej.entity.User;
import fr.moussalli.slackdej.repository.ChannelRepository;
import fr.moussalli.slackdej.repository.PostRepository;
import fr.moussalli.slackdej.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class PostTests {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Test
    void addPostUser() {
        User user1 = new User("jeff", "jef@jef.com");
        userRepository.save(user1);

        Post post1 = new Post("Bonjour, premier post !", new Date());
        post1.setUser(user1);

        postRepository.save(post1);
    }


    @Test
    void addPostChannel() {
        Channel channel1 = new Channel();
        channel1.setName("canal 1");
        channelRepository.save(channel1);

        Post post1 = new Post("Bonjour, premier post dans le canal 1 !", new Date());
        post1.setChannel(channel1);

        postRepository.save(post1);
    }

}

