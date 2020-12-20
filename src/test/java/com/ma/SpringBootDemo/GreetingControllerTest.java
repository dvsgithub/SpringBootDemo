package com.ma.SpringBootDemo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

    @MockBean
    private MessageRepository messageRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", authorities = "USER")
    public void webGetMessageTest() throws Exception {
        MessageDTO messageDTO = new MessageDTO("test message", "test user");
        List<Message> messages = List.of(new Message(messageDTO));

        when(messageRepository.findAll()).thenReturn(messages);

        mockMvc.perform(MockMvcRequestBuilders.get("/greeting/messages"))
                .andExpect(status().isOk())
                .andExpect(view().name("messages"))
                .andExpect(model().attributeExists("messages"))
                .andExpect(model().attribute("messages", equalTo(messages)));
    }

    @Test
    @WithMockUser(username = "user", authorities = "GUEST")
    public void checkUserAuthority() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/greeting/messages"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void checkUserWithoutAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/greeting/messages"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }


}