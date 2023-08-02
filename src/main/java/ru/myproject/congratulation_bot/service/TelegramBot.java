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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.myproject.congratulation_bot.config.BotConfig;
import ru.myproject.congratulation_bot.model.Anecdote;
import ru.myproject.congratulation_bot.model.tost.*;
import ru.myproject.congratulation_bot.repository.AnecdoteRepository;
import ru.myproject.congratulation_bot.model.User;
import ru.myproject.congratulation_bot.repository.tost.*;
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
    private TostLybovRepository tostLybovRepository;
    @Autowired
    private TostDenRozhdeniyaRepository tostDenRozhdeniyaRepository;
    @Autowired
    private TostKorotkieRepository tostKorotkieRepository;
    @Autowired
    private TostKrasivyeRepository tostKrasivyeRepository;
    @Autowired
    private TostPrazdnikRepository tostPrazdnikRepository;
    @Autowired
    private TostPrikolnyeRepository tostPrikolnyeRepository;
    @Autowired
    private TostRodnymRepository tostRodnymRepository;
    @Autowired
    private TostSvadbaRepository tostSvadbaRepository;
    @Autowired
    private TostYubileyRepository tostYubileyRepository;

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
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            if (callbackData.equals("anecdote_button")) {
                getAnecdoteCommandReceived(chatId);
            } else if (callbackData.equals("tost_button")) {
                getTostCommandReceived(chatId);
            } else if (callbackData.equals("lybov_button")) {
                getTost(chatId, callbackData);
            } else if (callbackData.equals("yubiley_button")) {
                getTost(chatId, callbackData);
            } else if (callbackData.equals("svadba_button")) {
                getTost(chatId, callbackData);
            } else if (callbackData.equals("prikolnye_button")) {
                getTost(chatId, callbackData);
            } else if (callbackData.equals("prazdnik_button")) {
                getTost(chatId, callbackData);
            } else if (callbackData.equals("krasivye_button")) {
                getTost(chatId, callbackData);
            } else if (callbackData.equals("korotkie_button")) {
                getTost(chatId, callbackData);
            } else if (callbackData.equals("denRozhdeniya_button")) {
                getTost(chatId, callbackData);
            } else if (callbackData.equals("rodnym_button")) {
                getTost(chatId, callbackData);
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

        getMessageButton(chatId, "anecdote");
    }

    private void getTostCommandReceived(long chatId) {
        getTostButtons(chatId); // Должен возвращать название темы

//        int sum = tostRepository.getCount();
//
//        Tost tost = tostRepository.findById(Utils.random(sum)).get();
//        String answer = tost.getText();
//        log.info("Message /tost");
//
//        sendMessage(chatId, answer);

//        getMessageButton(chatId, "tost");
    }

    private void getTost(long chatId, String callbackData) {
        switch (callbackData) {
            case "rodnym_button":
                TostRodnym tostRodnym = tostRodnymRepository.findById(Utils.random(tostRodnymRepository.getCount())).get();
                sendMessage(chatId, tostRodnym.getText());
                break;
            case "lybov_button":
                TostLybov tostLybov = tostLybovRepository.findById(Utils.random(tostLybovRepository.getCount())).get();
                sendMessage(chatId, tostLybov.getText());
                break;
            case "yubiley_button":
                TostYubiley tostYubiley = tostYubileyRepository.findById(Utils.random(tostYubileyRepository.getCount())).get();
                sendMessage(chatId, tostYubiley.getText());
                break;
            case "svadba_button":
                TostSvadba tostSvadba = tostSvadbaRepository.findById(Utils.random(tostSvadbaRepository.getCount())).get();
                sendMessage(chatId, tostSvadba.getText());
                break;
            case "prikolnye_button":
                TostPrikolnye tostPrikolnye = tostPrikolnyeRepository.findById(Utils.random(tostPrikolnyeRepository.getCount())).get();
                sendMessage(chatId, tostPrikolnye.getText());
                break;
            case "prazdnik_button":
                TostPrazdnik tostPrazdnik = tostPrazdnikRepository.findById(Utils.random(tostPrazdnikRepository.getCount())).get();
                sendMessage(chatId, tostPrazdnik.getText());
                break;
            case "krasivye_button":
                TostKrasivye tostKrasivye = tostKrasivyeRepository.findById(Utils.random(tostKrasivyeRepository.getCount())).get();
                sendMessage(chatId, tostKrasivye.getText());
                break;
            case "korotkie_button":
                TostKorotkie tostKorotkie = tostKorotkieRepository.findById(Utils.random(tostKorotkieRepository.getCount())).get();
                sendMessage(chatId, tostKorotkie.getText());
                break;
            case "denRozhdeniya_button":
                TostDenRozhdeniya tostDenRozhdeniya = tostDenRozhdeniyaRepository.findById(Utils.random(tostDenRozhdeniyaRepository.getCount())).get();
                sendMessage(chatId, tostDenRozhdeniya.getText());
                break;
        }
    }


    private void getRandomCongratulationCommandReceived(long chatId) {
        Block2 block2 = new Block2();
        Block3 block3 = new Block3();

        block2.getBlock(listPhrasesUtil, gm2_3_1, gm2_3_2, gm2_3_3, gm2_3_4, gm2_4_1, gm2_4_2, gm2_4_3, gm2_4_4);
        block3.getBlock(listPhrasesUtil, gm3_2_1, gm3_2_2, gm3_3_1, gm3_3_2, gm3_3_3, gm3_4_1, gm3_4_2, gm3_5);

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


    private void getMessageButton(long chatId, String title) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        String request = (title.equals("anecdote")) ? "анекдот" : "тост";
        message.setText("Хотите получить ещё " + request + "?");

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        var button = new InlineKeyboardButton();

        button.setText("Следующий");
        button.setCallbackData(title + "_button");

        rowInLine.add(button);
        rowsInLine.add(rowInLine);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }


    private void getTostButtons(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите тему тоста:");

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineA = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineB = new ArrayList<>();
        List<InlineKeyboardButton> rowInLineC = new ArrayList<>();

        var buttonRodnym = new InlineKeyboardButton();
        var buttonLybov = new InlineKeyboardButton();
        var buttonYubiley = new InlineKeyboardButton();
        var buttonSvadba = new InlineKeyboardButton();
        var buttonPrikolnye = new InlineKeyboardButton();
        var buttonPrazdnik = new InlineKeyboardButton();
        var buttonKrasivye = new InlineKeyboardButton();
        var buttonKorotkie = new InlineKeyboardButton();
        var buttonDenRozhdeniya = new InlineKeyboardButton();

        buttonRodnym.setText("Родным");
        buttonRodnym.setCallbackData("rodnym_button");
        buttonLybov.setText("Любовь");
        buttonLybov.setCallbackData("lybov_button");
        buttonYubiley.setText("На юбилей");
        buttonYubiley.setCallbackData("yubiley_button");
        buttonSvadba.setText("На свадьбу");
        buttonSvadba.setCallbackData("svadba_button");
        buttonPrikolnye.setText("Прикольные");
        buttonPrikolnye.setCallbackData("prikolnye_button");
        buttonPrazdnik.setText("На праздник");
        buttonPrazdnik.setCallbackData("prazdnik_button");
        buttonKrasivye.setText("Красивые");
        buttonKrasivye.setCallbackData("krasivye_button");
        buttonKorotkie.setText("Короткие");
        buttonKorotkie.setCallbackData("korotkie_button");
        buttonDenRozhdeniya.setText("Ко дню рождения");
        buttonDenRozhdeniya.setCallbackData("denRozhdeniya_button");

        rowInLineA.add(buttonRodnym);
        rowInLineA.add(buttonLybov);
        rowInLineA.add(buttonYubiley);
        rowInLineB.add(buttonSvadba);
        rowInLineB.add(buttonPrikolnye);
        rowInLineB.add(buttonPrazdnik);
        rowInLineC.add(buttonKrasivye);
        rowInLineC.add(buttonKorotkie);
        rowInLineC.add(buttonDenRozhdeniya);

        rowsInLine.add(rowInLineA);
        rowsInLine.add(rowInLineB);
        rowsInLine.add(rowInLineC);

        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }
}
