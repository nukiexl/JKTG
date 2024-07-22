package com.example.joker_bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.example.joker_bot.bot.JokesGenerator;

@Configuration
public class JokesGeneratorConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi (JokesGenerator jokesGenerator) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(jokesGenerator);
        return api;
    }
}
