package com.example.joker_bot.service;

import com.example.joker_bot.mapper.JokeStatsMapper;
import com.example.joker_bot.mapper.JokeUsedMapper;
import com.example.joker_bot.mapper.UserStatsMapper;
import com.example.joker_bot.model.JokeStats;
import com.example.joker_bot.model.JokeUsed;
import com.example.joker_bot.model.UserStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;

@Service
public class JokeStatService {
    @Autowired
    private JokeUsedMapper jokeUsedMapper;

    @Autowired
    private JokeStatsMapper jokeStatsMapper;

    @Autowired
    private UserStatsMapper userStatsMapper;


    RestClient restClient = RestClient.create();

    public String checkJoke(Long chatId, int category) {
        String url = "http://localhost:8081/jokes/randomByCategory?category=" + category;

        JokeResponse jokeResponse;
        List<JokeUsed> jokeUsedList;
        List<JokeStats> jokeStatsUsedList;
        List<UserStats> userStatsList;


        int attempts = 0;
        do  {
            jokeResponse = restClient
                    .get()
                    .uri(url)
                    .retrieve()
                    .toEntity(JokeResponse.class)
                    .getBody();

            if (jokeResponse == null) {
                return "Шутки закончились";
            }

            int jokeId = Math.toIntExact(jokeResponse.getId_joke());
            jokeUsedList = jokeUsedMapper.findByJokeIdAndChatId(jokeId, chatId);

            attempts++;
        }
        while (!jokeUsedList.isEmpty() && attempts < 15);

        if (attempts == 15) {
            return "Шутки закончились";
        }
        JokeUsed jokeUsed = new JokeUsed();
        jokeUsed.setId_joke(jokeResponse.getId_joke());
        jokeUsed.setId_chat(chatId);
        jokeUsed.setDate_used(LocalDate.now());
        jokeUsedMapper.insertJokeUsed(jokeUsed);

        jokeStatsUsedList = jokeStatsMapper.findByStatsJokeId(jokeResponse.getId_joke());
        if (jokeStatsUsedList.isEmpty()) {
            JokeStats jokeStats = new JokeStats();
            jokeStats.setJoke_id(jokeResponse.getId_joke());
            jokeStats.setLikes(0);
            jokeStats.setDislikes(0);
            jokeStatsMapper.insertJokeStats(jokeStats);
        }

        userStatsList = userStatsMapper.findByStatsChatId(chatId);
        if (userStatsList.isEmpty()) {
            UserStats userStats = new UserStats();
            userStats.setChat_id(chatId);
            userStats.setCountLikes(0);
            userStats.setCountDislikes(0);
            userStats.setCountJokes(0);
            userStatsMapper.insertUserStats(userStats);
        }

        viewJoke(chatId);


        return jokeResponse.getJoke_text() + " " + jokeResponse.getId_joke();
    }

    public void viewJoke(Long chatId) {
        userStatsMapper.incrementJokes(chatId);
    }

    public void userLikeJoke(Long chatId) {
        userStatsMapper.incrementLikes(chatId);
    }

    public void userDislikeJoke(Long chatId) {
        userStatsMapper.incrementDislikes(chatId);
    }


    public void likeJoke(Long jokeId) {
        jokeStatsMapper.incrementLikes(jokeId);
    }

    public void dislikeJoke(Long jokeId) {
        jokeStatsMapper.incrementDislikes(jokeId);
    }


    private static class JokeResponse {
        private Long id_joke;
        private String joke_text;

        public Long getId_joke() {
            return id_joke;
        }

        public String getJoke_text() {
            return joke_text;
        }
    }

}
