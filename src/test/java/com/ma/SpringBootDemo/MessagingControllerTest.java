package com.ma.SpringBootDemo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MessagingControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Autowired
    private MessagingController messagingController;

    @Autowired
    private MessageRepository messageRepository;

    @Before
    public void setUp() throws Exception {
        MessageDTO messageDTO = new MessageDTO("test message", "test user");
        messageRepository.save(new Message(messageDTO));
    }

    @Test
    public void getMessages() {
        Iterable<Message> message = messagingController.getMessages();
        assertThat(message.iterator().hasNext(), is(true));
    }

    @Test
    public void getMessagesWebCall() throws Exception {
        String result = restTemplate.getForObject("http://localhost:" + port + "/messaging", String.class);
        assertThat(result, containsString("test message"));
    }

    @Test
    public void postMessagesWebCall() throws Exception {
        MessageDTO messageDTO = new MessageDTO("sdff", "qwe");
        String result = restTemplate.postForObject("http://localhost:" + port + "/messaging", messageDTO, String.class);
        assertThat(result, containsString("qwe"));
    }

}