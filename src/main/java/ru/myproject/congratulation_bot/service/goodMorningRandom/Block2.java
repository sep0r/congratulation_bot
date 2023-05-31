package ru.myproject.congratulation_bot.service.goodMorningRandom;

import ru.myproject.congratulation_bot.service.goodMorningRandom.util.ListPhrasesUtil;
import static ru.myproject.congratulation_bot.service.goodMorningRandom.util.Utils.*;

public class Block2 {

    static int fork1 = random(4);

    public void getBlock(ListPhrasesUtil phrase) {
        switch (fork1) {
            case 0:
                phrase.tab2_3 = phrase.table2_3_1.getTable(random(phrase.table2_3_1.getSize()));
                int randomNum = random(phrase.table2_4_1.getSize());
                if (fiftyFifty()) {
                    phrase.tab2_4 = phrase.table2_4_1.getTable(randomNum) + " и " + phrase.table2_4_1.getTable(secondPhrase(randomNum, phrase.table2_4_1.getSize()));
                } else {
                    phrase.tab2_4 = phrase.table2_4_1.getTable(randomNum);
                }
                break;
            case 1:
                phrase.tab2_3 = phrase.table2_3_2.getTable(random(phrase.table2_3_2.getSize()));
                int randomNum2 = random(phrase.table2_4_2.getSize());
                if (fiftyFifty()) {
                    phrase.tab2_4 = phrase.table2_4_2.getTable(randomNum2) + " и " + phrase.table2_4_2.getTable(secondPhrase(randomNum2, phrase.table2_4_2.getSize()));
                } else {
                    phrase.tab2_4 = phrase.table2_4_2.getTable(randomNum2);
                }
                break;
            case 2:
                phrase.tab2_3 = phrase.table2_3_3.getTable(random(phrase.table2_3_3.getSize()));
                phrase.tab2_4 = phrase.table2_4_3.getTable(random(phrase.table2_4_3.getSize()));
                break;
            case 3:
                phrase.tab2_3 = phrase.table2_3_4.getTable(random(phrase.table2_3_4.getSize()));
                int randomNum4 = random(phrase.table2_4_4.getSize());
                if (fiftyFifty()) {
                    phrase.tab2_4 = phrase.table2_4_4.getTable(randomNum4) + " и " + phrase.table2_4_4.getTable(secondPhrase(randomNum4, phrase.table2_4_4.getSize()));
                } else {
                    phrase.tab2_4 = phrase.table2_4_4.getTable(randomNum4);
                }
                break;
        }
    }
}
