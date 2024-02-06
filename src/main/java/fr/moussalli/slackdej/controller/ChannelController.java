package fr.moussalli.slackdej.controller;

import fr.moussalli.slackdej.entity.Channel;
import fr.moussalli.slackdej.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @GetMapping("channels")
    public List<Channel> getChannels() {
        return channelService.getAll();
    }

    // POST /channels
    @PostMapping("channels")
    public ResponseEntity<?> addChannel(@RequestBody Channel channel) {
        if (channel.getName() != null && channel.getName().isBlank())
            return ResponseEntity
                    .badRequest()
                    .body("le nom du channel est obligatoire");
        else {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(channelService.add(channel));
        }
    }

    // GET /channels/4
    @GetMapping("channels/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Channel> optional = channelService.findById(id);
        if (optional.isEmpty())
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok(optional.get());
        }
    }

    // DELETE /channels/4
    @DeleteMapping("channels/{id}")
    public void delete(@PathVariable("id") Long id) {
        channelService.delete(id);
    }

    // PUT /channels/
    @PutMapping("channels")
    public void update(@RequestBody Channel channel) {
        channelService.update(channel);
    }

}
