package com.ma.SpringBootDemo;

import java.security.Principal;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/greeting")
@RequiredArgsConstructor
public class GreetingController {

    private final MessageRepository messageRepository;

    @GetMapping
    public String sayGreeting(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String name = Optional.ofNullable(principal)
                .map(Principal::getName)
                .orElse(System.getProperty("user.name"));
        model.addAttribute("name", name);
        return "greeting";

    }

    // /greeting/dima
    @GetMapping("/{name}/{surname}")
    public String sayPersonalGreeting(@PathVariable String name,
                                      @PathVariable String surname,
                                      @RequestParam(name = "polite", required = false, defaultValue = "false") boolean isPolite,
                                      Model model) {
        if (isPolite) {
            name = "Mr. / Mrs. " + name;
        }
        model.addAttribute("name", name + " " + surname);
        return "greeting";
    }

    @GetMapping("/messages")
    public String getMessages(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "messages";
    }

    @PostMapping("/addmessage")
    public String addMessage(String user, String message) {
        MessageDTO messageDTO = new MessageDTO(message, user);
        messageRepository.save(new Message(messageDTO));
        return "redirect:/greeting/messages";
    }

}
