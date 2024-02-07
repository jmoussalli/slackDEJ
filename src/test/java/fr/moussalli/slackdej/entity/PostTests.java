package fr.moussalli.slackdej.entity;

import fr.moussalli.slackdej.repository.ChannelRepository;
import fr.moussalli.slackdej.repository.PostRepository;
import fr.moussalli.slackdej.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PostTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Test
    void testAddPostToUser() {
        User user1 = new User("jeff", "jef@jef.com");
        userRepository.save(user1);

        Post post1 = new Post("Bonjour, premier post !", new Date());
        post1.setUser(user1);

        postRepository.save(post1);
    }


    @Test
    void testAddPostToUserAndChannel() {
        Channel channel1 = new Channel();
        channel1.setName("canal 1");
        channelRepository.save(channel1);

        User user1 = new User("jeff", "jef@jef.com");
        userRepository.save(user1);

        Post post1 = new Post("Bonjour, premier post dans le canal 1 !", new Date());
        post1.setChannel(channel1);
        post1.setUser(user1);

        postRepository.save(post1);
    }

    @Test
    void testFindAllPosts() {
        List<Post> postList = postRepository.findAll();

        if (postList.isEmpty()) {
            System.out.println("Aucun post trouvé");
        } else {
            for (Post post : postList) {
                System.out.println(post);
            }
        }
    }

    @Test
    void testFindPostById() {
        Optional<Post> postOptional = postRepository.findById(1L);
        if(postOptional.isEmpty())
            System.out.println("id non trouvé");
        else {
            Post post = postOptional.get();
            System.out.println(post);
        }
    }

    @Test
    void testUpdatePostById() {

        Optional<Post> postOptional = postRepository.findById(13L);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setMessage("message message 2");
            postRepository.save(post);
        }
    }

    @Test
    void testDeletePostById() {
        postRepository.deleteById(2L);
    }




}
