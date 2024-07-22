package com.example.joker_bot.controller;

import com.example.joker_bot.service.impl.JokesParseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/jokebot")
public class JokeController {

    private static final Logger LOG = LoggerFactory.getLogger(JokeController.class);

    @Autowired
    private JokesParseServiceImpl jokesParseServiceImpl;

    @GetMapping(value = "/fetchJoke", produces = MediaType.APPLICATION_XML_VALUE)
    public String fetchJoke() {
        return jokesParseServiceImpl.fetchJoke();
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Сервис доступен");
    }
}
