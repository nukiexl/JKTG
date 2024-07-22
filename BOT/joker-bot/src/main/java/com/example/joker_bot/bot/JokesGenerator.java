package com.example.joker_bot.bot;

import com.example.joker_bot.service.JokesParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.joker_bot.exception.ServiceException;
import com.example.joker_bot.service.JokesGeneratorService;

import java.util.ArrayList;
import java.util.List;

@Component
public class JokesGenerator extends TelegramLongPollingBot {

    private static final Logger LOG = LoggerFactory.getLogger(JokesGenerator.class);

    private static final String START = "/start";
    private static final String JOKE = "/joke";
    private static final String HELP = "/help";
    private static final String CATEGORY_1 = "category_1";
    private static final String CATEGORY_2 = "category_2";
    private static final String CATEGORY_3 = "category_3";
    private static final String CATEGORY_5 = "category_5";
    private static final String CATEGORY_6 = "category_6";
    private static final String CATEGORY_11 = "category_11";


    @Autowired
    private JokesGeneratorService jokesGeneratorService;

    @Autowired
    private JokesParseService jokesParseService;

    public JokesGenerator(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            switch (message) {
                case START -> {
                    String userName = update.getMessage().getChat().getUserName();
                    startCommand(chatId, userName);
                }
                case JOKE -> {
                    try {
                        jokeCommand(chatId, null);
                    } catch (ServiceException e) {
                        throw new RuntimeException(e);
                    }
                }
                case HELP -> helpCommand(chatId, null);
                default -> unknownCommand(chatId);
            }

        } else if (update.hasCallbackQuery()) {
            try {
                handleCallbackQuery(update.getCallbackQuery());
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }


    }

    @Override
    public String getBotUsername() {
        return "JKTG_42_BOT";
    }

    private void startCommand(Long chatId, String userName) {
        var text = """
                Добро пожаловать в бот, %s!
                
                Здесь Вы сможете посмеяться.
                """;
        var formattedText = String.format(text, userName);
        sendInlineKeyboardHelpMessage(chatId, formattedText);
    }

    String jokeId = "";
    String jokeText = "";
    private void viewJokeId(String joke) throws ServiceException {
        int lastSpaceIndex = joke.lastIndexOf(" ");
        if (lastSpaceIndex != -1) {
            jokeId = joke.substring(lastSpaceIndex + 1);
            jokeText = joke.substring(0, lastSpaceIndex);
        }
    }

    private void sendJokeByCategory(Long chatId, int category, CallbackQuery callbackQuery) {
        String formattedText;
        try {
            var joke = jokesGeneratorService.getJokeByCategory(chatId, category);
            var text = "Приходит как-то улитка в бар и говорит: \n %s";
            System.out.println(joke);
            if (String.format(joke).equals("Шутки закончились")) {
                jokesParseService.saveJoke(category);
                joke = jokesGeneratorService.getJokeByCategory(chatId, category);
            }
            viewJokeId(String.format(joke));
            formattedText = String.format(text, jokeText);
        } catch (ServiceException e) {
            LOG.error("Ошибка получения шутки категории " + category, e);
            formattedText = "Улитка не нашла шуток в этой категории. Загляните позже.";
        }
        if (callbackQuery != null) {
            clearPreviousMessageMarkup(callbackQuery);
        }
        sendInlineKeyboardMessage(chatId, formattedText);
    }

    private void jokeCommand(Long chatId, CallbackQuery callbackQuery) throws ServiceException {
        if (callbackQuery != null) {
            clearPreviousMessageMarkup(callbackQuery);
        }
        var text = "Выберите категорию шутки:";
        sendCategoryKeyboardMessage(chatId, text);
    }


    private void helpCommand(Long chatId, CallbackQuery callbackQuery) {
        if (callbackQuery != null) {
            clearPreviousMessageMarkup(callbackQuery);
        }
        var text = """
                Справочная информация по боту
                
                /joke - получить шутку
                
                А больше ничего улитка не может
                """;
        sendInlineKeyboardHelpMessage(chatId, text);
    }

    private void unknownCommand(Long chatId) {
        var text = "Улитка не бармен, она только шутит.";
        sendInlineKeyboardMessage(chatId, text);
    }

    private void sendInlineKeyboardMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();

        InlineKeyboardButton likeButton = new InlineKeyboardButton();
        likeButton.setText("👍");
        likeButton.setCallbackData("LIKE");

        InlineKeyboardButton dislikeButton = new InlineKeyboardButton();
        dislikeButton.setText("👎");
        dislikeButton.setCallbackData("DISLIKE");

        InlineKeyboardButton jokeButton = new InlineKeyboardButton();
        jokeButton.setText("Шутка");
        jokeButton.setCallbackData(JOKE);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText("Справка");
        helpButton.setCallbackData(HELP);

        rowInline1.add(likeButton);
        rowInline1.add(dislikeButton);
        rowInline2.add(jokeButton);
        rowInline2.add(helpButton);

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);

        inlineKeyboardMarkup.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка отправки сообщения", e);
        }
    }

    private void sendCategoryKeyboardMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline4 = new ArrayList<>();

        InlineKeyboardButton category1Button = new InlineKeyboardButton();
        category1Button.setText("Анекдоты");
        category1Button.setCallbackData(CATEGORY_1);

        InlineKeyboardButton category2Button = new InlineKeyboardButton();
        category2Button.setText("Рассказы");
        category2Button.setCallbackData(CATEGORY_2);

        InlineKeyboardButton category3Button = new InlineKeyboardButton();
        category3Button.setText("Стишки");
        category3Button.setCallbackData(CATEGORY_3);

        InlineKeyboardButton category5Button = new InlineKeyboardButton();
        category5Button.setText("Афоризмы");
        category5Button.setCallbackData(CATEGORY_5);

        InlineKeyboardButton category6Button = new InlineKeyboardButton();
        category6Button.setText("Тосты");
        category6Button.setCallbackData(CATEGORY_6);

        InlineKeyboardButton category11Button = new InlineKeyboardButton();
        category11Button.setText("Анекдоты 18+");
        category11Button.setCallbackData(CATEGORY_11);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText("Справка");
        helpButton.setCallbackData(HELP);

        rowInline1.add(category1Button);
        rowInline1.add(category2Button);
        rowInline2.add(category3Button);
        rowInline2.add(category5Button);
        rowInline3.add(category6Button);
        rowInline3.add(category11Button);
        rowInline4.add(helpButton);

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        rowsInline.add(rowInline4);

        inlineKeyboardMarkup.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка отправки сообщения", e);
        }
    }

    private void sendInlineKeyboardHelpMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton jokeButton = new InlineKeyboardButton();
        jokeButton.setText("Шутка");
        jokeButton.setCallbackData(JOKE);

        rowInline.add(jokeButton);

        rowsInline.add(rowInline);

        inlineKeyboardMarkup.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка отправки сообщения", e);
        }
    }

    private void sendMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка отправки сообщения", e);
        }
    }

    private void handleCallbackQuery (CallbackQuery callbackQuery) throws ServiceException {
        String callBackData = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();

        switch (callBackData) {
            case JOKE -> jokeCommand(chatId, callbackQuery);
            case HELP -> helpCommand(chatId, callbackQuery);
            case "LIKE" -> likeCommand(Long.valueOf(jokeId), chatId, callbackQuery);
            case "DISLIKE" -> dislikeCommand(Long.valueOf(jokeId), chatId, callbackQuery);
            case CATEGORY_1 -> sendJokeByCategory(chatId, 1, callbackQuery);
            case CATEGORY_2 -> sendJokeByCategory(chatId, 2, callbackQuery);
            case CATEGORY_3 -> sendJokeByCategory(chatId, 3, callbackQuery);
            case CATEGORY_5 -> sendJokeByCategory(chatId, 5, callbackQuery);
            case CATEGORY_6 -> sendJokeByCategory(chatId, 6, callbackQuery);
            case CATEGORY_11 -> sendJokeByCategory(chatId, 11, callbackQuery);
            default -> unknownCommand(chatId);
        }
    }

    private void likeCommand(Long jokeId, Long chatId, CallbackQuery callbackQuery) throws ServiceException {
        jokesGeneratorService.likeJoke(jokeId, chatId);
        removeLikeDislikeButtons(callbackQuery);
    }

    private void dislikeCommand(Long jokeId, Long chatId, CallbackQuery callbackQuery) throws ServiceException {
        jokesGeneratorService.dislikeJoke(jokeId, chatId);
        removeLikeDislikeButtons(callbackQuery);
    }

    private void removeLikeDislikeButtons(CallbackQuery callbackQuery) {
        Integer messageId = callbackQuery.getMessage().getMessageId();
        Long chatId = callbackQuery.getMessage().getChatId();

        InlineKeyboardMarkup currentMarkup = (InlineKeyboardMarkup) callbackQuery.getMessage().getReplyMarkup();

        InlineKeyboardMarkup newMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton jokeButton = new InlineKeyboardButton();
        jokeButton.setText("Шутка");
        jokeButton.setCallbackData(JOKE);

        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        helpButton.setText("Справка");
        helpButton.setCallbackData(HELP);

        rowInline.add(jokeButton);
        rowInline.add(helpButton);

        rowsInline.add(rowInline);

        newMarkup.setKeyboard(rowsInline);

        if (currentMarkup.equals(newMarkup)) {
            LOG.info("Markup not changed, query didn't send");
            return;
        }

        EditMessageReplyMarkup editMarkup = new EditMessageReplyMarkup();
        editMarkup.setChatId(String.valueOf(chatId));
        editMarkup.setMessageId(messageId);
        editMarkup.setReplyMarkup(newMarkup);

        try {
            execute(editMarkup);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка обновления сообщения", e);
        }
    }

    private void clearPreviousMessageMarkup(CallbackQuery callbackQuery) {
        Integer messageId = callbackQuery.getMessage().getMessageId();
        Long chatId = callbackQuery.getMessage().getChatId();

            EditMessageReplyMarkup editMarkup = new EditMessageReplyMarkup();
            editMarkup.setChatId(String.valueOf(chatId));
            editMarkup.setMessageId(messageId);
            editMarkup.setReplyMarkup(null); // Устанавливаем разметку в null, чтобы удалить её

            try {
                execute(editMarkup);
            } catch (TelegramApiException e) {
                LOG.error("Ошибка очистки разметки предыдущего сообщения", e);
            }
    }
}
