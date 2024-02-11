package fr.moussalli.slackdej;

import fr.moussalli.slackdej.entity.Channel;
import fr.moussalli.slackdej.entity.Post;
import fr.moussalli.slackdej.entity.User;
import fr.moussalli.slackdej.service.ChannelService;
import fr.moussalli.slackdej.service.PostService;
import fr.moussalli.slackdej.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SlackDejApplication {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    ChannelService channelService;

    public static void main(String[] args) {
        SpringApplication.run(SlackDejApplication.class, args);
    }


    // @Postconstruct permet de s'assurer que la méthode initApplication va s'exécuter
    // un fois que la class SlackDejApplication a été instanciée et ses dépendances injectées
    @PostConstruct
    void initApplication() {
        // Placer ici l'initialisation de l'application
        // Canal général par défaut
        // Mais aussi l’utilisateur est créé côté Backend ou côté Frontend de manière statique dans le code

        // L'initialisation se déclenche si il n'y a ni channel ni user
        if ((userService.nombreDeUsers() < 1) && (channelService.nombreDeChannels() < 1)) {
            System.out.println("// initialisation application //////////////////////////");

            // Il n'y a pas encore de users, on créé le user "admin"
            User userAdmin = new User("admin", "admin@localhost.org");
            User savedUser = userService.addUser(userAdmin);
            System.out.println("// initialisation application : user 'admin' créé");

            // Création d'un post de bienvenue
            Post postDeBienvenue = new Post("Hello !", Date.valueOf(LocalDate.now()));
            postDeBienvenue.setUser(userAdmin);
            Post savedPostDeBienvenue = postService.addPost(postDeBienvenue);
            System.out.println("// initialisation application : post 'Bienvenue !' créé");


            // Il n'y a pas encore de channels, on créé le channel "général"
            Channel channelGeneral = new Channel("général");
            channelGeneral.setisDeletable(false);
            channelGeneral.setUser(userAdmin);
            Channel savedChannelGeneral = channelService.add(channelGeneral);
            System.out.println("// initialisation application : channel 'général' créé avec son user admin");

            // Ajout des relations
            System.out.println("// Ajout des relations //////////////////////////");

            // Rattachement du post de bienvenue au canal général
            List<Post> posts = new ArrayList<>();
            posts.add(postDeBienvenue);
            savedChannelGeneral.setPosts(posts);
            channelService.save(savedChannelGeneral);
            System.out.println("// Ajout des relations : Rattachement du post de bienvenue au canal général");

            // pour finir, rattachement du channel au post

            savedPostDeBienvenue.setChannel(channelGeneral);
            postService.updatePost(savedPostDeBienvenue, savedPostDeBienvenue.getId());
            System.out.println("// Ajout des relations : Rattachement du channel général au post de bienvenue");

            System.out.println("Fin initialisation");
        }

    }

}
