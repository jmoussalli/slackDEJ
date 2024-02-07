package fr.moussalli.slackdej.controller;

import fr.moussalli.slackdej.service.ChannelService;
import fr.moussalli.slackdej.service.PostService;
import fr.moussalli.slackdej.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    ChannelService channelService;

//    //GET ALL users
//    @GetMapping("users")
//    public



}
