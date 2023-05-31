package ru.myproject.congratulation_bot.service;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.myproject.congratulation_bot.config.BotConfig;
import ru.myproject.congratulation_bot.model.Anecdote;
import ru.myproject.congratulation_bot.repository.AnecdoteRepository;
import ru.myproject.congratulation_bot.model.User;
import ru.myproject.congratulation_bot.repository.UserRepository;
import ru.myproject.congratulation_bot.repository.goodMorningRandom.GMTable1Repository;
import ru.myproject.congratulation_bot.service.goodMorningRandom.util.Utils;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnecdoteRepository anecdoteRepository;

    @Autowired
    private GMTable1Repository gmTableRepository;

    private TemplatesForRandom templatesForRandom;
    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "Получить приветственное сообщение"));
        listOfCommands.add(new BotCommand("/anecdote", "Получить анекдот"));
        listOfCommands.add(new BotCommand("/help", "Получить подробное описание команд"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    registerUser(update.getMessage());
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/anecdote":
                    getAnecdoteCommandReceived(chatId);
                    break;
                case "/help":
                    helpCommandReceived(chatId);
                    break;
                default:
                    sendMessage(chatId, "Извини, но такой команды пока нет");
            }
        }
    }

    private void registerUser(Message msg) {

        if (userRepository.findById(msg.getChatId()).isEmpty()) {

            var chatId = msg.getChatId();
            var chat = msg.getChat();

            User user = new User();

            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));

            userRepository.save(user);
            log.info("user saved: " + user);
        }
    }

    private void startCommandReceived(long chatId, String name) {
        String answer = EmojiParser.parseToUnicode("Привет " + name + ", рад познакомиться с тобой!" + " :wave:\n\n" +
                "Я тот самый Мюнхгаузен или если быть точнее:\n" +
                "Карл Фридрих Иероним фон Мюнхга́узен" + " :slight_smile:");
        log.info("Replied to user " + name);

        sendMessage(chatId, answer);
    }

    private void helpCommandReceived(long chatId) {
        String answer = "Я – Мюнхгаузен – барон.\n" + "Весел, ловок и умён.\n" +
                "Рассказываю анекдоты. А ещё ловко придумываю поздравления или пожелания.\n" +
                "\n" +
                "❗️Список команд\n" +
                "/start - получить приветственное сообщение\n" +
                "/anecdote - получить анекдот\n" +
                "/help - получить подробное описание команд\n";
        log.info("Message /help");

        sendMessage(chatId, answer);
    }

    private void getAnecdoteCommandReceived(long chatId) {
        int sum = anecdoteRepository.getCount();

        Anecdote anecdote = anecdoteRepository.findById(Utils.random(sum)).get();
        String answer = anecdote.getText();
        log.info("Message /anecdote");

        sendMessage(chatId, answer);
    }

    private void randomCongratulationCommandReceived(long chatId, TemplatesForRandom templatesForRandom) {


        String answer = "";
        log.info("Message /randomCongratulation");

        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }
}
