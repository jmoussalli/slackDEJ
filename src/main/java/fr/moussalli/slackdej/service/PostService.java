package fr.moussalli.slackdej.service;

import fr.moussalli.slackdej.entity.Post;
import fr.moussalli.slackdej.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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

    public void addPost(Post post) {
        postRepository.save(post);
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    public void updatePost(Post post, long id) {
//        Optional<Post> optionalPost = postRepository.findById(id);
//        if (optionalPost.isPresent()) {
////            Post existingPost = optionalPost.get();
////            existingPost.setMessage(post.getTitle());
////            existingPost.setPostDateTime(post.getContent());
//            // You can update other fields as needed
//
//            // Save the updated post
//            postRepository.save(existingPost);
//        }
    }

}
