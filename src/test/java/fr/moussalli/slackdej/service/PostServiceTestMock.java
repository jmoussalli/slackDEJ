package fr.moussalli.slackdej.service;

import fr.moussalli.slackdej.entity.Channel;
import fr.moussalli.slackdej.entity.Post;
import fr.moussalli.slackdej.entity.User;
import fr.moussalli.slackdej.repository.ChannelRepository;
import fr.moussalli.slackdej.repository.PostRepository;
import fr.moussalli.slackdej.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PostServiceTestMock {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ChannelRepository channelRepository;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPost() {
        Post post1 = new Post("Bonjour, premier post !", new Date());
        when(postRepository.save(any(Post.class))).thenReturn(post1);

        Post addedPost = postService.addPost(post1);

        assertNotNull(addedPost);
        assertEquals(post1.getMessage(), addedPost.getMessage());
        assertEquals(post1.getPostDateTime(), addedPost.getPostDateTime());
    }

    @Test
    void testAddPostToUser() {
        User user1 = new User("jeff", "jef@jef.com");
        when(userRepository.save(any(User.class))).thenReturn(user1);

        Post post1 = new Post("Bonjour, premier post !", new Date());
        post1.setUser(user1);
        when(postRepository.save(any(Post.class))).thenReturn(post1);

        Post addedPost = postService.addPostToUser(post1, user1);

        assertNotNull(addedPost);
        assertEquals(post1.getMessage(), addedPost.getMessage());
        assertEquals(post1.getPostDateTime(), addedPost.getPostDateTime());
        assertEquals(user1, addedPost.getUser());
    }


    @Test
    void testAddPostToChannel() {
        Channel channel1 = new Channel();
        channel1.setName("channel1");
        when(channelRepository.save(any(Channel.class))).thenReturn(channel1);


        Post post1 = new Post("Bonjour, premier post dans le canal !", new Date());
        post1.setChannel(channel1);

        when(postRepository.save(any(Post.class))).thenReturn(post1);

        Post addedPost = postService.addPostToChannel(post1, channel1);

        assertNotNull(addedPost);
        assertEquals(post1.getMessage(), addedPost.getMessage());
        assertEquals(post1.getPostDateTime(), addedPost.getPostDateTime());
        assertEquals(channel1, addedPost.getChannel());
    }


    @Test
    void testAddPostToUserAndChannel() {
        // Création d'un utilisateur
        User user1 = new User("jeff", "jef@jef.com");
        when(userRepository.save(any(User.class))).thenReturn(user1);

        // Création d'un canal
        Channel channel1 = new Channel();
        channel1.setName("channel1");
        when(channelRepository.save(any(Channel.class))).thenReturn(channel1);

        // Création d'un post
        Post post1 = new Post("Bonjour, premier post dans le canal !", new Date());
        post1.setUser(user1);
        post1.setChannel(channel1);

        when(postRepository.save(any(Post.class))).thenReturn(post1);

        Post addedPost = postService.addPostToUserAndChannel(post1, user1, channel1);

        // Assertions
        assertNotNull(addedPost);
        assertEquals(post1.getMessage(), addedPost.getMessage());
        assertEquals(post1.getPostDateTime(), addedPost.getPostDateTime());
        assertEquals(user1, addedPost.getUser());
        assertEquals(channel1, addedPost.getChannel());
    }

    @Test
    void testGetAllPosts() {
        // Création de quelques posts fictifs pour simuler la base de données
        Post post1 = new Post("Premier post", new Date());
        Post post2 = new Post("Deuxième post", new Date());
        List<Post> mockPosts = Arrays.asList(post1, post2);

        // Simulation de la méthode findAll() du repository
        when(postRepository.findAll()).thenReturn(mockPosts);

        // Appel de la méthode réelle du service pour récupérer tous les posts
        List<Post> allPosts = postService.getAllPosts();

        // Assertions
        assertNotNull(allPosts, "La liste des posts ne devrait pas être null");
        assertEquals(mockPosts.size(), allPosts.size(), "Le nombre de posts devrait être le même que celui simulé");
        assertTrue(allPosts.contains(post1), "La liste des posts devrait contenir le premier post simulé");
        assertTrue(allPosts.contains(post2), "La liste des posts devrait contenir le deuxième post simulé");
    }


    @Test
    void testGetPostById() {
        // Création d'un post fictif avec un ID spécifique
        Long postId = 1L;
        Post mockPost = new Post("Post par ID", new Date());
        mockPost.setId(postId);

        // Simulation de la méthode findById() du repository
        when(postRepository.findById(postId)).thenReturn(Optional.of(mockPost));

        // Appel de la méthode réelle du service pour récupérer le post par ID
        Post retrievedPost = postService.getPostById(postId);

        // Assertions
        assertNotNull(retrievedPost, "Le post devrait être trouvé");
        assertEquals(mockPost.getId(), retrievedPost.getId(), "Les ID des posts devraient correspondre");
        assertEquals(mockPost.getMessage(), retrievedPost.getMessage(), "Les messages des posts devraient correspondre");
        assertEquals(mockPost.getPostDateTime(), retrievedPost.getPostDateTime(), "Les dates des posts devraient correspondre");
    }

    @Test
    void testUpdatePostById() {
        // Création d'un post fictif avec un ID spécifique
        Long postId = 1L;
        Post mockPost = new Post("Post avant mise à jour", new Date());
        mockPost.setId(postId);

        // Simulation de la méthode findById() du repository
        when(postRepository.findById(postId)).thenReturn(Optional.of(mockPost));

        // Mise à jour du contenu du post
        String newMessage = "Nouveau message après mise à jour";
        mockPost.setMessage(newMessage);

        // Appel de la méthode réelle du service pour mettre à jour le post par ID
        Post updatedPost = postService.updatePost(mockPost, postId);

        // Assertions
        assertNotNull(updatedPost, "Le post mis à jour ne devrait pas être null");
        assertEquals(postId, updatedPost.getId(), "Les ID des posts devraient correspondre");
        assertEquals(newMessage, updatedPost.getMessage(), "Le message du post devrait être mis à jour");
        assertEquals(mockPost.getPostDateTime(), updatedPost.getPostDateTime(), "Les dates des posts devraient correspondre");

        // Vérification que la méthode save a été appelée
        verify(postRepository, times(1)).save(mockPost);
    }

    @Test
    void testDeletePostById() {
        // Création d'un post fictif avec un ID spécifique
        Long postId = 1L;
        Post mockPost = new Post("Post à supprimer", new Date());
        mockPost.setId(postId);

        // Simulation de la méthode findById() du repository
        when(postRepository.findById(postId)).thenReturn(Optional.of(mockPost));

        // Appel de la méthode réelle du service pour supprimer le post par ID
        postService.deletePost(postId);

        // Vérification que la méthode deleteById a été appelée avec le bon ID
        verify(postRepository, times(1)).deleteById(postId);
    }



}
