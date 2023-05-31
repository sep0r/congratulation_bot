package ru.myproject.congratulation_bot.service.goodMorningRandom;

import ru.myproject.congratulation_bot.service.goodMorningRandom.util.ListPhrasesUtil;

import static ru.myproject.congratulation_bot.service.goodMorningRandom.util.Utils.*;

public class Block3 {

    static int fork2 = random(3);

    public void getBlock(ListPhrasesUtil phrase) {
        switch (fork2) {
            case 0:
                phrase.tab3_2 = phrase.table3_2_1.getTable((phrase.table3_2_1.getSize() - 1));
                int randomFork = random(2);                                     // сменить на переменную
                if (randomFork == 0) {
                    phrase.tab3_3 = phrase.table3_3_1.getTable(random(phrase.table3_3_1.getSize()));
                    phrase.tab3_4 = phrase.table3_4_1.getTable(random(phrase.table3_4_1.getSize()));
                    phrase.tab3_5 = phrase.table3_5.getTable(random(phrase.table3_5.getSize()));
                } else {
                    phrase.tab3_3 = phrase.table3_3_2.getTable(random(phrase.table3_3_2.getSize()));
                    phrase.tab3_4 = phrase.table3_4_2.getTable(random(phrase.table3_4_1.getSize()));
                    int randomNum = random(phrase.table3_3_3.getSize());
                    if (fiftyFifty()) {
                        phrase.tab3_5 = phrase.table3_3_3.getTable(randomNum) + " и " + phrase.table3_3_3.getTable(secondPhrase(randomNum, phrase.table3_3_3.getSize()));
                    } else {
                        phrase.tab3_5 = phrase.table3_3_3.getTable(randomNum);
                    }
                }
                break;
            case 1:
                phrase.tab3_2 = phrase.table3_2_2.getTable(phrase.table3_2_2.getSize() - 1);
                int randomNum2 = random(phrase.table3_3_3.getSize());
                if (fiftyFifty()) {
                    phrase.tab3_3 = phrase.table3_3_3.getTable(randomNum2) + " и "
                            + phrase.table3_3_3.getTable(secondPhrase(randomNum2, phrase.table3_3_3.getSize()));
                } else {
                    phrase.tab3_3 = phrase.table3_3_3.getTable(randomNum2);
                }
                phrase.tab3_4 = "";
                phrase.tab3_5 = "";
                break;
            case 2:
                int randomNum3 = random(phrase.table3_3_3.getSize());
                if (fiftyFifty()) {
                    phrase.tab3_3 = phrase.table3_3_3.getTable(randomNum3) + ", "
                            + phrase.table3_3_3.getTable(secondPhrase(randomNum3, phrase.table3_3_3.getSize())) + " и "
                            + phrase.table3_3_3.getTable(secondPhrase(randomNum3, phrase.table3_3_3.getSize()));
                } else {
                    phrase.tab3_3 = phrase.table3_3_3.getTable(randomNum3);
                }
                phrase.tab3_2 = "";
                phrase.tab3_4 = "";
                phrase.tab3_5 = "";
                break;
        }
    }
}
