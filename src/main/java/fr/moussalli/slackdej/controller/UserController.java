package fr.moussalli.slackdej.controller;

import fr.moussalli.slackdej.entity.Channel;
import fr.moussalli.slackdej.entity.Post;
import fr.moussalli.slackdej.entity.User;
import fr.moussalli.slackdej.service.ChannelService;
import fr.moussalli.slackdej.service.PostService;
import fr.moussalli.slackdej.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    ChannelService channelService;


 // GET ALL users
     @GetMapping("users")
     public ResponseEntity<List<User>> getAllUsers(){
         List<User> users = userService.getAll();
         return ResponseEntity.ok(users);
     }

     // GET user/1
     @GetMapping("users/{id}")
     public ResponseEntity<?> getOneById(@PathVariable("id") Integer id) {
        Optional<User> optional = userService.getOneById(id);
         return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.badRequest().body("id inexistant");
    }

    // POST users
    @PostMapping("users")
    public ResponseEntity<?> createUser(@RequestBody User newUser){
         if(newUser.getName()==null || newUser.getName().isBlank() || newUser.getEmail()==null||newUser.getEmail().isBlank())
             return ResponseEntity.badRequest().body("Saisie incomplète!");
         userService.addUser(newUser);
         return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
     }


     // PUT users
    @PutMapping("users/{id}")
    public ResponseEntity<String> putUser(@PathVariable("id") Integer id,@RequestBody User newDataUser){
         Optional<User> optional = userService.getOneById(id);
         if(optional.isEmpty())
             return ResponseEntity.notFound().build();

         User userToUpdate = optional.get();
         userService.replaceUser(userToUpdate, newDataUser);
         return ResponseEntity.ok("Update réalisée");
    }

    // DELETE users
    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteOneById(@PathVariable("id") Integer id){
        Optional<User> optional = userService.getOneById(id);

        if (optional.isEmpty())
            return ResponseEntity.notFound().build();

        userService.deleteUserById(id);
        return ResponseEntity.ok("Suppression réalisée!");

    }



}





