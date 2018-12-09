package com.maks.dwitter.controller;

import com.maks.dwitter.model.Message;
import com.maks.dwitter.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("main")
    public String main(Model model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("main")
    public String add(@RequestParam String text, @RequestParam String tag, Model model) {
        Message message = new Message(text, tag);

        messageRepository.save(message);

        return "redirect:main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String tag, Model model) {
        if (tag != null && !tag.isEmpty()) {
            Iterable<Message> messages = messageRepository.findByTag(tag);
            model.addAttribute("messages", messages);
            return "main";
        } else
            return "redirect:main";
    }
}
