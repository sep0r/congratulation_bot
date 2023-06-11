package ru.myproject.congratulation_bot.service.goodMorningRandom;

import ru.myproject.congratulation_bot.repository.goodMorningRandom.*;
import ru.myproject.congratulation_bot.service.goodMorningRandom.util.ListPhrasesUtil;

import static ru.myproject.congratulation_bot.service.goodMorningRandom.util.Utils.*;

public class Block3 {

    static int fork2 = random(3);

    public void getBlock(ListPhrasesUtil phrase, GMTable3_2_1Repository gm3_2_1, GMTable3_2_2Repository gm3_2_2, GMTable3_3_1Repository gm3_3_1,
                         GMTable3_3_2Repository gm3_3_2, GMTable3_3_3Repository gm3_3_3, GMTable3_4_1Repository gm3_4_1, GMTable3_4_2Repository gm3_4_2, GMTable3_5Repository gm3_5) {
        switch (fork2) {
            case 0:
                phrase.tab3_2 = gm3_2_1.findById(randomForTable(gm3_2_1.getCount())).get().getText();
                int randomFork = random(2);
                if (randomFork == 0) {
                    phrase.tab3_3 = gm3_3_1.findById(randomForTable(gm3_3_1.getCount())).get().getText();
                    phrase.tab3_4 = gm3_4_1.findById(randomForTable(gm3_4_1.getCount())).get().getText();
                    phrase.tab3_5 = gm3_5.findById(randomForTable(gm3_5.getCount())).get().getText();
                } else {
                    phrase.tab3_3 = gm3_3_2.findById(randomForTable(gm3_3_2.getCount())).get().getText();
                    int rrr = randomForTable(gm3_4_2.getCount());
                    phrase.tab3_4 = gm3_4_2.findById(rrr).get().getText();
                    int randomNum = randomForTable(gm3_3_3.getCount());
                    if (fiftyFifty()) {
                        phrase.tab3_5 = gm3_3_3.findById(randomNum).get().getText() + " и " + gm3_3_3.findById(secondPhrase(randomNum, gm3_3_3.getCount())).get().getText();
                    } else {
                        phrase.tab3_5 = gm3_3_3.findById(randomNum).get().getText();
                    }
                }
                break;
            case 1:
                phrase.tab3_2 = gm3_2_2.findById(randomForTable(gm3_2_2.getCount())).get().getText();
                int randomNum2 = randomForTable(gm3_3_3.getCount());
                if (fiftyFifty()) {
                    phrase.tab3_3 = gm3_3_3.findById(randomNum2).get().getText() + " и "
                            + gm3_3_3.findById(secondPhrase(randomNum2, gm3_3_3.getCount())).get().getText();
                } else {
                    phrase.tab3_3 = gm3_3_3.findById(randomNum2).get().getText();
                }
                phrase.tab3_4 = "";
                phrase.tab3_5 = "";
                break;
            case 2:                                                                 //Нужно переделать, звучит глупо
                int randomNum3 = randomForTable(gm3_3_3.getCount());
                if (fiftyFifty()) {
                    int randomNumForSecondPhrase = secondPhrase(randomNum3, gm3_3_3.getCount());
                    phrase.tab3_3 = gm3_3_3.findById(randomNum3).get().getText() + ", "
                            + gm3_3_3.findById(randomNumForSecondPhrase).get().getText() + " и "
                            + gm3_3_3.findById(thirdPhrase(randomNum3, randomNumForSecondPhrase, gm3_3_3.getCount())).get().getText();
                } else {
                    phrase.tab3_3 = gm3_3_3.findById(randomNum3).get().getText();
                }
                phrase.tab3_2 = "";
                phrase.tab3_4 = "";
                phrase.tab3_5 = "";
                break;
        }
    }
}
