package com.ma.SpringBootDemo;

import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messaging")
@RequiredArgsConstructor
public class MessagingController {

    private final MessageRepository messageRepository;

    @GetMapping
    public Iterable<Message> getMessages() {
        return messageRepository.findAll();
    }

    @PostMapping
    public Message addMessage(@RequestBody MessageDTO message) {
        return messageRepository.save(new Message(message));
    }

}
