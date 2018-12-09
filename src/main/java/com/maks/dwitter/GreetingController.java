package com.maks.dwitter;

import com.maks.dwitter.model.Message;
import com.maks.dwitter.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Model model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Model model) {
        Message message = new Message(text, tag);

        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String tag, Model model) {
        Iterable<Message> messages;
        if (tag != null && !tag.isEmpty())
            messages = messageRepository.findByTag(tag);
        else
            messages = messageRepository.findAll();

        model.addAttribute("messages", messages);
        return "main";
    }
}
