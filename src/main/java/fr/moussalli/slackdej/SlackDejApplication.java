package fr.moussalli.slackdej;

import fr.moussalli.slackdej.entity.Channel;
import fr.moussalli.slackdej.entity.User;
import fr.moussalli.slackdej.service.ChannelService;
import fr.moussalli.slackdej.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SlackDejApplication {

    @Autowired
    UserService userService;

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

        if (userService.nombreDeUsers() < 1) {
            // Il n'y a pas encore de users, on créé le user "admin"
            User userAdmin = new User("admin", "admin@localhost.org");
            userService.addUser(userAdmin);
            System.out.println("// initialisation application : user 'admin'");
        }

        if (channelService.nombreDeChannels() < 1) {
            // Il n'y a pas encore de channels, on créé le channel "général"
            Channel channelGeneral = new Channel("général");
            channelGeneral.setisDeletable(false);
            channelService.add(channelGeneral);
            System.out.println("// initialisation application : channel 'général'");
        }

    }

}
