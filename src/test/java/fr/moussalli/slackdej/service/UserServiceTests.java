/*
package fr.moussalli.slackdej.service;



import fr.moussalli.slackdej.entity.User;
import fr.moussalli.slackdej.repository.ChannelRepository;
import fr.moussalli.slackdej.repository.PostRepository;
import fr.moussalli.slackdej.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

        @Autowired
        UserRepository userRepository;

        @Autowired
        ChannelRepository channelRepository;

        @Autowired
        PostRepository postRepository;

        @Test
        void getAllTest() {
            List<User> users = new ArrayList<>();
            User user1 = new User("John P", "johnP@jpj.com");
            userRepository.save(user1);

            User user2 = new User("Jack L", "jackL@jcl.com");
            userRepository.save(user2);

            User user3 = new User("Jimmy H", "jimH@jpj.com");
            userRepository.save(user3);

            User user4 = new User("Joe P", "joeP@jpj.com");
            userRepository.save(user4);

            users = userRepository.findAll();

        }

            @Test
            void getOneByIdTest(){
                User user1 = new User("John P", "johnP@jpj.com");
                userRepository.save(user1);

                User user2 = new User("Jack L", "jackL@jcl.com");
                userRepository.save(user2);

                User user3 = new User("Jimmy H", "jimH@jpj.com");
                userRepository.save(user3);

                User user4 = new User("Joe P", "joeP@jpj.com");
                userRepository.save(user4);

                System.out.println(userRepository.findById(2));
            }


        }
*/