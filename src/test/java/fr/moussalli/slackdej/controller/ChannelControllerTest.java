package fr.moussalli.slackdej.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.moussalli.slackdej.entity.Channel;
import fr.moussalli.slackdej.service.ChannelService;
import fr.moussalli.slackdej.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChannelController.class)
class ChannelControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private ChannelService channelService;

    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ChannelController channelController = new ChannelController(channelService);
        mockMvc = MockMvcBuilders.standaloneSetup(channelController).build();
    }

    @Test
    void createChannel() {
    }

    @Test
    void testGetAllChannels() throws Exception {
        // Given
        Channel channel1 = new Channel("News");
        Channel channel2 = new Channel("Sports");
        List<Channel> channels = Arrays.asList(channel1, channel2);

        when(channelService.getAll()).thenReturn(channels);

        // When & Then
        mockMvc.perform(get("/api/channels") // Assuming the endpoint is "/channels"
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'name':'News'},{'name':'Sports'}]")); // Simplified JSON string for illustration
    }

    @Test
    void getChannelById() throws Exception {
        // Given
        Long channelId = 1L;
        Channel mockChannel = new Channel("Test Channel");
        mockChannel.setId(channelId);
        when(channelService.findById(anyLong())).thenReturn(Optional.of(mockChannel));

        // When & Then
        mockMvc.perform(get("/api/channels/{id}", channelId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'Test Channel'}")); // Simplify your actual JSON structure as necessary
    }

    @Test
    void getChannelById_NotFound() throws Exception {
        // Given
        Long channelId = 1L;
        when(channelService.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/api/channels/{id}", channelId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateChannel() throws Exception {
        // Given
        Long channelId = 1L;
        Channel existingChannel = new Channel("Original Name");
        existingChannel.setId(channelId);

        Channel updatedChannelDetails = new Channel("Updated Name");

        when(channelService.findById(eq(channelId))).thenReturn(Optional.of(existingChannel));
        doNothing().when(channelService).update(any(Channel.class)); // Adjusted for void return type

        // When & Then
        mockMvc.perform(put("/api/channels/{id}", channelId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedChannelDetails)))
                .andExpect(status().isOk());

        verify(channelService, times(1)).update(any(Channel.class)); // Verify that update was called
    }


    @Test
    void updateChannel_NotFound() throws Exception {
        // Given
        Long channelId = 1L;
        Channel updatedChannelDetails = new Channel("Updated Name");

        when(channelService.findById(eq(channelId))).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(put("/api/channels/{id}", channelId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedChannelDetails)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteChannel() {
    }
}