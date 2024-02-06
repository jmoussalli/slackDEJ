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

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ChannelRepository channelRepository;


    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getOneById(Integer id){
        return userRepository.findById(id);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(User user){

    }
      List<Post> posts = postRepository.findAll();
      List<Channel> channels = channelRepository.findAll();

        clientRepository.save(user);
