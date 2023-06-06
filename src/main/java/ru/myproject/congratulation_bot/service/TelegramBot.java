package ru.myproject.congratulation_bot.service;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.myproject.congratulation_bot.config.BotConfig;
import ru.myproject.congratulation_bot.model.Anecdote;
import ru.myproject.congratulation_bot.model.Tost;
import ru.myproject.congratulation_bot.repository.AnecdoteRepository;
import ru.myproject.congratulation_bot.model.User;
import ru.myproject.congratulation_bot.repository.TostRepository;
import ru.myproject.congratulation_bot.repository.UserRepository;
import ru.myproject.congratulation_bot.repository.goodMorningRandom.*;
import ru.myproject.congratulation_bot.service.goodMorningRandom.Block2;
import ru.myproject.congratulation_bot.service.goodMorningRandom.Block3;
import ru.myproject.congratulation_bot.service.goodMorningRandom.TemplatesForRandom;
import ru.myproject.congratulation_bot.service.goodMorningRandom.util.ListPhrasesUtil;
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
    private TostRepository tostRepository;

    @Autowired
    GMTable1Repository gm1;
    @Autowired
    GMTable2_1Repository gm2_1;
    @Autowired
    GMTable2_2Repository gm2_2;
    @Autowired
    GMTable2_3_1Repository gm2_3_1;
    @Autowired
    GMTable2_3_2Repository gm2_3_2;
    @Autowired
    GMTable2_3_3Repository gm2_3_3;
    @Autowired
    GMTable2_3_4Repository gm2_3_4;
    @Autowired
    GMTable2_4_1Repository gm2_4_1;
    @Autowired
    GMTable2_4_2Repository gm2_4_2;
    @Autowired
    GMTable2_4_3Repository gm2_4_3;
    @Autowired
    GMTable2_4_4Repository gm2_4_4;
    @Autowired
    GMTable3_1Repository gm3_1;
    @Autowired
    GMTable3_2_1Repository gm3_2_1;
    @Autowired
    GMTable3_2_2Repository gm3_2_2;
    @Autowired
    GMTable3_3_1Repository gm3_3_1;
    @Autowired
    GMTable3_3_2Repository gm3_3_2;
    @Autowired
    GMTable3_3_3Repository gm3_3_3;
    @Autowired
    GMTable3_4_1Repository gm3_4_1;
    @Autowired
    GMTable3_4_2Repository gm3_4_2;
    @Autowired
    GMTable3_5Repository gm3_5;

    final BotConfig config;
    private ListPhrasesUtil listPhrasesUtil;

    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "Получить приветственное сообщение"));
        listOfCommands.add(new BotCommand("/anecdote", "Получить анекдот"));
        listOfCommands.add(new BotCommand("/tost", "Получить тост"));
        listOfCommands.add(new BotCommand("/goodmorning", "Получить пожелание доброго утра"));
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
                case "/tost":
                    getTostCommandReceived(chatId);
                    break;
                case "/goodmorning":
                    getRandomCongratulationCommandReceived(chatId);
                    break;
                case "/help":
                    helpCommandReceived(chatId);
                    break;
                default:
                    sendMessage(chatId, "Извини, но такой команды пока нет");
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            if (callbackData.equals("NEXT_BUTTON")) {
                getAnecdoteCommandReceived(chatId);
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
                "/tost - получить тост\n" +
                "/goodmorning - получить пожелание доброго утра\n" +
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

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Do you really want next anecdote?");

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var nextButton = new InlineKeyboardButton();

        nextButton.setText("Следующий");
        nextButton.setCallbackData("NEXT_BUTTON");

        rowInLine.add(nextButton);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    private void getTostCommandReceived(long chatId) {
        int sum = tostRepository.getCount();

        Tost tost = tostRepository.findById(Utils.random(sum)).get();
        String answer = tost.getText();
        log.info("Message /tost");

        sendMessage(chatId, answer);
    }

    private void getRandomCongratulationCommandReceived(long chatId) {
        Block2 block2 = new Block2();
        Block3 block3 = new Block3();

        block2.getBlock(listPhrasesUtil, gm2_3_1, gm2_3_2, gm2_3_3, gm2_3_4, gm2_4_1, gm2_4_2, gm2_4_3, gm2_4_4);
        block3.getBlock(listPhrasesUtil, gm2_3_1, gm3_2_1, gm3_2_2, gm3_3_1, gm3_3_2, gm3_3_3, gm3_4_1, gm3_4_2, gm3_5);

        String answer = TemplatesForRandom.randomTemplates(listPhrasesUtil, gm1, gm2_1, gm2_2, gm3_1);
        log.info("Message /randomGoodMorningCongratulation");

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
