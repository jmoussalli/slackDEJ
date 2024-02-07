package fr.moussalli.slackdej.service;

import fr.moussalli.slackdej.entity.Post;
import fr.moussalli.slackdej.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

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

}
