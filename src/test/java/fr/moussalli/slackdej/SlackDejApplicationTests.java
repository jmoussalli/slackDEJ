package fr.moussalli.slackdej;

import fr.moussalli.slackdej.entity.Channel;
import fr.moussalli.slackdej.entity.User;
import fr.moussalli.slackdej.repository.ChannelRepository;
import fr.moussalli.slackdej.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SlackDejApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Test
    void addUser() {
        User user1 = new User("jeff", "jef@jef.com");
        userRepository.save(user1);
    }

    @Test
    void addUserPostChannel() {
        User user1 = new User("jeff", "jef@jef.com");
        userRepository.save(user1);

        Channel channel1 = new Channel();
        channel1.setName("canal 1");
       // channel1.setUser(user1);

        channelRepository.save(channel1);
    }


}
