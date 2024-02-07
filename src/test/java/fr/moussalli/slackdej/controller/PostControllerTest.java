package fr.moussalli.slackdej.controller;

import fr.moussalli.slackdej.entity.Post;
import fr.moussalli.slackdej.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @Test
    void testGetAllPosts() {
        // Mock données avec deux publications
        List<Post> mockPosts = new ArrayList<>();
        Post post1 = new Post("Message 1", null);  // Définir les valeurs appropriées pour votre entité Post
        Post post2 = new Post("Message 2", null);  // Définir les valeurs appropriées pour votre entité Post
        mockPosts.add(post1);
        mockPosts.add(post2);

        // Simulation de la méthode du service
        when(postService.getAllPosts()).thenReturn(mockPosts);

        // Appeler la méthode du contrôleur
        ResponseEntity<List<Post>> responseEntity = postController.getAllPosts();

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPosts, responseEntity.getBody());

        // Vérifier que la méthode du service a été appelée
        verify(postService, times(1)).getAllPosts();
    }

    @Test
    void testGetPostById() {
        long postId = 1L;
        Post mockPost = new Post("Message 1", new Date());

        // Simulation de la méthode du service sans utiliser Optional
        when(postService.getPostById(postId)).thenReturn(mockPost);

        // Appeler la méthode du contrôleur
        ResponseEntity<Post> responseEntity = postController.getPost(postId);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPost, responseEntity.getBody());
    }

    @Test
    void testAddPost() {
        Post mockPostToAdd = new Post("Nouveau message", new Date());
        Post mockPostAdded = new Post("Nouveau message", new Date());

        // Simulation de la méthode du service
        when(postService.addPost(mockPostToAdd)).thenReturn(mockPostAdded);

        // Appeler la méthode du contrôleur
        ResponseEntity<?> responseEntity = postController.addPost(mockPostToAdd);

        // Assertions
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockPostAdded, responseEntity.getBody());
    }
}

