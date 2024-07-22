package com.example.joker_parser.controller;

import com.example.joker_parser.service.JokeService;
import com.example.joker_parser.model.Joke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jokes")
public class JokeController {

    private static final Logger LOG = LoggerFactory.getLogger(JokeController.class);

    @Autowired
    private JokeService jokeService;

    @GetMapping("/fetch")
    public String fetchJoke() {
        jokeService.fetchAndSaveJoke();
        return "Запись добавлена!";
    }

    @GetMapping("/random")
    public Joke getRandomJoke() {
        LOG.info("Отправлен запрос");
        Joke joke = jokeService.getRandomJoke();
        if (joke == null){
            LOG.warn("Шуток не найдено");
        }
        return joke;
    }

    @GetMapping("/randomByCategory")
    public Joke getRandomJokeByCategory(@RequestParam int category) {
        LOG.info("Отправлен запрос на случайную шутку категории {}", category);
        Joke joke = jokeService.getRandomJokeByCategory(category);
        if (joke == null) {
            LOG.warn("Шуток не найдено для категории {}", category);
        }
        return joke;
    }

    @GetMapping("/all")
    public List<Joke> getAllJokes() {
        List<Joke> jokes = jokeService.getAllJokes();
        jokes.forEach(joke -> System.out.println(joke.getjoke_text()));
        return jokes;
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Сервис доступен");
    }
}
