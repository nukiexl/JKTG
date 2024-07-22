package com.example.joker_bot.controller;

import com.example.joker_bot.service.JokeStatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jokesStat")
public class JokeStatController {

    private static final Logger LOG = LoggerFactory.getLogger(JokeStatController.class);

    @Autowired
    private JokeStatService jokeStatService;

    @GetMapping("/checkJoke")
    public String checkJoke(@RequestParam Long chatId, @RequestParam(required = false, defaultValue = "1") int category) {
        LOG.info("Received request to check joke for chatId: {}", chatId);
        return jokeStatService.checkJoke(chatId, category);
    }

    @GetMapping("/likeJoke")
    public void likeJoke(@RequestParam Long jokeId, @RequestParam Long chatId) {
        LOG.info("Received request to like joke for jokeId: {}", jokeId);
        jokeStatService.likeJoke(jokeId);
        jokeStatService.userLikeJoke(chatId);
    }

    @GetMapping("/dislikeJoke")
    public void dislikeJoke(@RequestParam Long jokeId, @RequestParam Long chatId) {
        LOG.info("Received request to dislike joke for jokeId: {}", jokeId);
        jokeStatService.dislikeJoke(jokeId);
        jokeStatService.userDislikeJoke(chatId);
    }


}
