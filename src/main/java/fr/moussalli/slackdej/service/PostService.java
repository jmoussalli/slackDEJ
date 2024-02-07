package fr.moussalli.slackdej.service;

import fr.moussalli.slackdej.entity.Channel;
import fr.moussalli.slackdej.entity.Post;
import fr.moussalli.slackdej.entity.User;
import fr.moussalli.slackdej.repository.ChannelRepository;
import fr.moussalli.slackdej.repository.PostRepository;
import fr.moussalli.slackdej.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChannelRepository channelRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

    public Post addPost(Post post) {
        postRepository.save(post);
        return post;
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    public Post updatePost(Post post, long id) {
        postRepository.save(post);
        return post;
    }



    public Post addPostToUser(Post post, User user) {
        user.addPost(post);
        post.setUser(user);

        userRepository.save(user);
        return postRepository.save(post);
    }

    public Post addPostToChannel(Post post, Channel channel) {
        channel.addPost(post);
        post.setChannel(channel);

        channelRepository.save(channel);
        return postRepository.save(post);
    }

    public Post addPostToUserAndChannel(Post post, User user, Channel channel) {
        user.addPost(post);
        post.setUser(user);
        userRepository.save(user);

        channel.addPost(post);
        post.setChannel(channel);
        channelRepository.save(channel);

        return postRepository.save(post);
    }

}
