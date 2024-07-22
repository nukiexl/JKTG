package com.example.joker_bot.service;

import com.example.joker_bot.exception.ServiceException;

public interface JokesParseService {

    String fetchJoke() throws ServiceException;

    void saveJoke(int category) throws ServiceException;
}
