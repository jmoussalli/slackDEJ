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
@RequestMapping("/api/channels")
public class ChannelController {

    private final ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    // Create a new Channel
    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody Channel channel) {
        Channel newChannel = channelService.add(channel);
        return new ResponseEntity<>(newChannel, HttpStatus.CREATED);
    }

    // Get all Channels
    @GetMapping
    public ResponseEntity<List<Channel>> getAllChannels() {
        List<Channel> channels = channelService.getAll();
        return ResponseEntity.ok(channels);
    }

    // Get a single Channel by ID
    @GetMapping("/{id}")
    public ResponseEntity<Channel> getChannelById(@PathVariable Long id) {
        Optional<Channel> channel = channelService.findById(id);
        return channel.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing Channel
    @PutMapping("/{id}")
    public ResponseEntity<Channel> updateChannel(@PathVariable Long id, @RequestBody Channel channelDetails) {
        Optional<Channel> channelData = channelService.findById(id);

        if (channelData.isPresent()) {
            Channel updatedChannel = channelData.get();
            updatedChannel.setName(channelDetails.getName());
            updatedChannel.setUser(channelDetails.getUser());
            updatedChannel.setPosts(channelDetails.getPosts());
            channelService.update(updatedChannel);
            return ResponseEntity.ok(updatedChannel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Channel
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteChannel(@PathVariable Long id) {
        try {
            channelService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}