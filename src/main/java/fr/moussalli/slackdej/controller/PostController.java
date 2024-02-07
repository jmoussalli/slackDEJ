package fr.moussalli.slackdej.controller;

import fr.moussalli.slackdej.entity.Post;
import fr.moussalli.slackdej.repository.PostRepository;
import fr.moussalli.slackdej.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPost(@PathVariable long id) {
        Post post = postService.getPostById(id);
        return post != null ? new ResponseEntity<>(post, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/post")
    public ResponseEntity<?> addPost(@RequestBody Post post) {

        if (post.getMessage() == null || post.getMessage().isBlank() ||
                post.getPostDateTime() == null) {

            return new ResponseEntity<>("Tous les champs doivent être remplis !", HttpStatus.BAD_REQUEST);
        }
        Post postAdded = postService.addPost(post);

        return new ResponseEntity<>(postAdded, HttpStatus.CREATED);
    }


    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        Post existingPost = postRepository.findById(id).orElse(null);
        if (existingPost != null) {
            postService.deletePost(id);
            return new ResponseEntity<>("Post avec l'id " + id + " supprimé avec succès", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Post non trouvé avec l'Id : " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<?> updatePost(@RequestBody Post post, @PathVariable long id) {
        Post existingPost = postRepository.findById(id).orElse(null);
        if (existingPost != null) {
            post.setId(id);
            Post postUpdated = postService.updatePost(post, id);
            return new ResponseEntity<>(postUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Post non trouvé avec l'id : " + id, HttpStatus.NOT_FOUND);
        }
    }

}
