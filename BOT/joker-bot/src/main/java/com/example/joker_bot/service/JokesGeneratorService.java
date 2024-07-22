package com.example.joker_bot.service;

import com.example.joker_bot.exception.ServiceException;

public interface JokesGeneratorService {

    String getJOKE(Long chatId) throws ServiceException;

    void likeJoke(Long jokeId, Long chatId) throws ServiceException;

    void dislikeJoke(Long jokeId, Long chatId) throws ServiceException;

    String getJokeByCategory(Long chatId, int category) throws ServiceException;

}
