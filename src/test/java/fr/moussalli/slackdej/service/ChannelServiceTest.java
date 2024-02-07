package fr.moussalli.slackdej.service;

import fr.moussalli.slackdej.entity.Channel;
import fr.moussalli.slackdej.repository.ChannelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ChannelServiceTest {

    @Mock
    private ChannelRepository channelRepository;

    @InjectMocks
    private ChannelService channelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddChannel() {
        Channel channel = new Channel("Test Channel");
        when(channelRepository.save(any(Channel.class))).thenReturn(channel);

        Channel savedChannel = channelService.add(channel);

        assertNotNull(savedChannel);
        assertEquals("Test Channel", savedChannel.getName());
    }

    @Test
    void testGetAllChannels() {
        // Given
        Channel channel1 = new Channel("News");
        Channel channel2 = new Channel("Sports");
        List<Channel> mockChannels = Arrays.asList(channel1, channel2);

        when(channelRepository.findAll()).thenReturn(mockChannels);

        // When
        List<Channel> channels = channelService.getAll();

        // Then
        assertNotNull(channels);
        assertEquals(2, channels.size());
        assertTrue(channels.containsAll(mockChannels));
    }

    @Test
    void testFindById_WhenChannelExists() {
        // Given
        Long channelId = 1L;
        Channel mockChannel = new Channel("Test Channel");
        mockChannel.setId(channelId);
        when(channelRepository.findById(channelId)).thenReturn(Optional.of(mockChannel));

        // When
        Optional<Channel> foundChannel = channelService.findById(channelId);

        // Then
        assertTrue(foundChannel.isPresent(), "Channel should be found");
        assertEquals(channelId, foundChannel.get().getId(), "Channel ID should match");
        assertEquals("Test Channel", foundChannel.get().getName(), "Channel name should match");
    }

    @Test
    void testFindById_WhenChannelDoesNotExist() {
        // Given
        Long channelId = 2L;
        when(channelRepository.findById(channelId)).thenReturn(Optional.empty());

        // When
        Optional<Channel> foundChannel = channelService.findById(channelId);

        // Then
        assertFalse(foundChannel.isPresent(), "Channel should not be found");
    }

    @Test
    void testDeleteChannel() {
        // Given
        Long channelId = 1L;

        // Do nothing when channelRepository.deleteById is called.
        doNothing().when(channelRepository).deleteById(channelId);

        // When
        channelService.delete(channelId);

        // Then
        verify(channelRepository, times(1)).deleteById(channelId);
    }

    @Test
    void testUpdateChannel() {
        // Given
        Long channelId = 1L;
        Channel existingChannel = new Channel("Original Name");
        existingChannel.setId(channelId); // Simulate an existing channel

        Channel updatedChannel = new Channel("Updated Name");
        updatedChannel.setId(channelId); // This should match the existing channel's ID to simulate an update

        when(channelRepository.save(any(Channel.class))).thenReturn(updatedChannel);

        // When
        channelService.update(updatedChannel);

        // Then
        verify(channelRepository, times(1)).save(updatedChannel);
        assertNotEquals("Original Name", updatedChannel.getName());
        assertEquals("Updated Name", updatedChannel.getName());
    }
}