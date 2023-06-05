package ru.myproject.congratulation_bot.service.goodMorningRandom;

import ru.myproject.congratulation_bot.model.goodMorningRandom.part2.GMTable2_3_1;
import ru.myproject.congratulation_bot.repository.goodMorningRandom.*;
import ru.myproject.congratulation_bot.service.goodMorningRandom.util.ListPhrasesUtil;

import static ru.myproject.congratulation_bot.service.goodMorningRandom.util.Utils.*;

public class Block2 {

    static int fork1 = random(4);

    public void getBlock(ListPhrasesUtil phrase, GMTable2_3_1Repository gm2_3_1, GMTable2_3_2Repository gm2_3_2, GMTable2_3_3Repository gm2_3_3, GMTable2_3_4Repository gm2_3_4,
                         GMTable2_4_1Repository gm2_4_1, GMTable2_4_2Repository gm2_4_2, GMTable2_4_3Repository gm2_4_3, GMTable2_4_4Repository gm2_4_4) {
        switch (2) { //вернуть fork1
            case 0:
                phrase.tab2_3 = gm2_3_1.findById(randomForTable(gm2_3_1.getCount())).get().getText();
                int randomNum = randomForTable(gm2_4_1.getCount());
                if (fiftyFifty()) {
                    phrase.tab2_4 = gm2_4_1.findById(randomNum).get().getText() + " и " + gm2_4_1.findById(secondPhrase(randomNum, gm2_4_1.getCount())).get().getText();
                } else {
                    phrase.tab2_4 = gm2_4_1.findById(randomNum).get().getText();
                }
                break;
            case 1:
                phrase.tab2_3 = gm2_3_2.findById(randomForTable(gm2_3_2.getCount())).get().getText();
                int randomNum2 = randomForTable(gm2_4_2.getCount());
                if (fiftyFifty()) {
                    phrase.tab2_4 = gm2_4_2.findById(randomNum2).get().getText() + " и " + gm2_4_2.findById(secondPhrase(randomNum2, gm2_4_2.getCount())).get().getText();
                } else {
                    phrase.tab2_4 = gm2_4_2.findById(randomNum2).get().getText();
                }
                break;
            case 2:
                phrase.tab2_3 = gm2_3_3.findById(randomForTable(gm2_3_3.getCount())).get().getText();
                phrase.tab2_4 = gm2_4_3.findById(randomForTable(gm2_4_3.getCount())).get().getText();
                break;
            case 3:
                phrase.tab2_3 = gm2_3_4.findById(randomForTable(gm2_3_4.getCount())).get().getText();
                int randomNum4 = randomForTable(gm2_4_4.getCount());
                if (fiftyFifty()) {
                    phrase.tab2_4 = gm2_4_4.findById(randomNum4).get().getText() + " и " + gm2_4_4.findById(secondPhrase(randomNum4, gm2_4_4.getCount())).get().getText();
                } else {
                    phrase.tab2_4 = gm2_4_4.findById(randomNum4).get().getText();
                }
                break;
        }
    }
}
