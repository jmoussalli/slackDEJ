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
    public ResponseEntity<String> addPost(@RequestBody Post post) {
        postService.addPost(post);
        return new ResponseEntity<>("Post ajouté avec succès",HttpStatus.CREATED);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post supprimé avec succès", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<String> updatePost(@RequestBody Post post, @PathVariable long id) {
        Post existingPost = postRepository.findById(id).orElse(null);
        if (existingPost != null) {
            post.setId(id);
            postService.updatePost(post, id);
            return new ResponseEntity<>("Post mis à jour avec succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Post non trouvé avec l'ID : " + id, HttpStatus.NOT_FOUND);
        }
    }
}
