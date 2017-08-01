package io.joel.controllers;

import io.joel.interfaces.CommentRepository;
import io.joel.interfaces.TopicRepository;
import io.joel.models.Comment;
import io.joel.models.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TopicController {

    @Autowired
    TopicRepository repo;

    @Autowired
    CommentRepository commentRepo;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Topic> topics = repo.findAll();
        model.addAttribute("topics", topics);
        return "index";
    }

    @RequestMapping(value = "/createTopic", method = RequestMethod.POST)
    public String createTopic(@RequestParam("title") String title,
                              @RequestParam("description") String description) {
        Topic topic = new Topic(title, description);
        repo.save(topic);
        return "redirect:/";
    }

    @RequestMapping("/topic/{topicId}")
    public String topicDetail(@PathVariable("topicId") long topicId,
                              Model model) {
        Topic topic = repo.findOne(topicId);
        model.addAttribute("topic", topic);
        return "topicDetail";
    }

    @RequestMapping(value = "/topic/{topicId}/createComment", method = RequestMethod.POST)
    public String createComment(@PathVariable("topicId") long topicId,
                                @RequestParam("commentername") String commentername,
                                @RequestParam("message") String message) {
        Topic topic = repo.findOne(topicId);
        Comment newComment = new Comment(commentername, message, topic);
        commentRepo.save(newComment);
        return "redirect:/topic/" + topicId;
    }
}
