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
        return channelService.findById(id)
                .map(channel -> {
                    if (channelDetails.getName() != null) {
                        channel.setName(channelDetails.getName());
                    }
                    if (channelDetails.getUser() != null) {
                        channel.setUser(channelDetails.getUser());
                    }
                    if (channelDetails.getPosts() != null) {
                        channel.getPosts().clear(); // Clear the existing collection
                        channel.getPosts().addAll(channelDetails.getPosts()); // Add all from the new collection
                    }
                    channelService.update(channel);
                    return ResponseEntity.ok(channel);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
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