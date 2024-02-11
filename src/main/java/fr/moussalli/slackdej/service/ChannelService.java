package fr.moussalli.slackdej.service;

import fr.moussalli.slackdej.entity.Channel;
import fr.moussalli.slackdej.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }
    public Channel add(Channel a) {
        return channelRepository.save(a);
    }

    public List<Channel> getAll() {
        return channelRepository.findAll();
    }

    public Optional<Channel> findById(Long id) {
        return channelRepository.findById(id);
    }

    public void delete(Long id) {
        channelRepository.deleteById(id);
    }

    public void update(Channel channel) {
        channelRepository.save(channel);
    }

    public int nombreDeChannels() {
        return channelRepository.findAll().size();
    }

}
