package fr.moussalli.slackdej.service;

import ch.qos.logback.core.net.server.Client;
import fr.moussalli.slackdej.entity.Channel;
import fr.moussalli.slackdej.entity.Post;
import fr.moussalli.slackdej.entity.User;
import fr.moussalli.slackdej.repository.ChannelRepository;
import fr.moussalli.slackdej.repository.PostRepository;
import fr.moussalli.slackdej.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;



    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getOneById(Integer id) {
        return userRepository.findById(id);
    }
// Ajouts
    public void addUser(User user) {
        userRepository.save(user);
    }


    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
    // Remplacer un utilisateur par un autre (tous les attributs)
    public void replaceUser(User userToUpdate, User newDataUser){
        if(newDataUser.getName()!=null && !newDataUser.getName().isBlank())
            userToUpdate.setName(newDataUser.getName());
        if(newDataUser.getEmail()!=null && !newDataUser.getEmail().isBlank())
            userToUpdate.setEmail(newDataUser.getEmail());
        if(newDataUser.getPosts()!=null && !newDataUser.getPosts().isEmpty())
            userToUpdate.setPosts(newDataUser.getPosts());
        if(newDataUser.getChannels()!=null && !newDataUser.getChannels().isEmpty())
            userToUpdate.setChannels(newDataUser.getChannels());
        userRepository.save(userToUpdate);
    }

    public Optional<User> updateUser (Integer id, User newDataUser) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User userToUpdate = optional.get();
            replaceUser(userToUpdate, newDataUser);
            userRepository.save(userToUpdate);
        }
        return optional;
    }

}


