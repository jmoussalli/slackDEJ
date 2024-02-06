package fr.moussalli.slackdej.controller;


import fr.moussalli.slackdej.entity.Post;
import fr.moussalli.slackdej.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/post/{id}")
    public Post getPost(@PathVariable long id) {
        return postService.getPostById(id);
    }

    @PostMapping("/post")
    public void addPost(@RequestBody Post post) {
        postService.addPost(post);
    }

    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }

    @PutMapping("/post/{id}")
    public void updatePost(@RequestBody Post post, @PathVariable long id) {
        postService.updatePost(post, id);
    }
}

