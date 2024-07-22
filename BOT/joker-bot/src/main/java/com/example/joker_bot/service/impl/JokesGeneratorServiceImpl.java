package com.example.joker_bot.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.example.joker_bot.exception.ServiceException;
import com.example.joker_bot.service.JokesGeneratorService;


@Service
public class JokesGeneratorServiceImpl implements JokesGeneratorService {
    // Подгрузка шутки из БД
    @Value("${joke.service.url}")
    private String jokeServiceUrl;

    RestClient restClient = RestClient.create();

    @Override
    public String getJOKE(Long chatId) throws ServiceException {
        return getJokeFromService(chatId);
    }

    @Override
    public String getJokeByCategory(Long chatId, int category) throws ServiceException {
        return getJokeFromServiceByCategory(chatId, category);
    }

    private String getJokeFromService(Long chatId) throws ServiceException {
        try {
            String jokeResponse = restClient
                    .get()
                    .uri(jokeServiceUrl + "/jokesStat/checkJoke?chatId=" + chatId)
                    .retrieve()
                    .body(String.class);

            return jokeResponse;
        } catch (Exception e) {
            throw new ServiceException("Ошибка получения шутки", e);
        }
    }

    public void likeJoke(Long jokeId, Long chatId) throws ServiceException {
        try {
            restClient
                    .get()
                    .uri(jokeServiceUrl + "/jokesStat/likeJoke?jokeId=" + jokeId + "&chatId=" + chatId)
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            throw new ServiceException("Ошибка лайка шутки", e);
        }
    }

    public void dislikeJoke(Long jokeId, Long chatId) throws ServiceException {
       try {
            restClient
                    .get()
                    .uri(jokeServiceUrl + "/jokesStat/dislikeJoke?jokeId=" + jokeId + "&chatId=" + chatId)
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            throw new ServiceException("Ошибка дизлайка шутки", e);
       }
    }

    private String getJokeFromServiceByCategory(Long chatId, int category) throws ServiceException {
        try {
            String jokeResponse = restClient
                    .get()
                    .uri(jokeServiceUrl + "/jokesStat/checkJoke?chatId=" + chatId + "&category=" + category)
                    .retrieve()
                    .body(String.class);

            return jokeResponse;
        } catch (Exception e) {
            throw new ServiceException("Ошибка получения шутки категории " + category, e);
        }
    }
}
