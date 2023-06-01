package ru.myproject.congratulation_bot.service.goodMorningRandom;

import ru.myproject.congratulation_bot.repository.goodMorningRandom.*;
import ru.myproject.congratulation_bot.service.goodMorningRandom.util.ListPhrasesUtil;

import static ru.myproject.congratulation_bot.service.goodMorningRandom.util.Utils.random;

public class TemplatesForRandom {
    static private String tail = "";
    static private String preTail = "";
    static private String res;

    public static String threeBlocks(ListPhrasesUtil phrase, GMTable1Repository gm1, GMTable2_1Repository gm2_1, GMTable2_2Repository gm2_2, GMTable3_1Repository gm3_1) {

        res = gm1.findById(random(gm1.getCount())).get().getText() + " "
                + gm2_1.findById(random(gm2_1.getCount())).get().getText() + " " + gm2_2.findById(random(gm2_2.getCount())).get().getText() + " " + phrase.tab2_3 + " " + phrase.tab2_4 + ". "
                + gm3_1.findById((gm3_1.getCount() - 1)).get().getText();

        if (!phrase.tab3_2.equals("")) {
            preTail = " " + phrase.tab3_2;
        }

        if (!phrase.tab3_4.equals("")) {
            tail = " " + phrase.tab3_4 + " " + phrase.tab3_5;
        }

        return (res + preTail + " " + phrase.tab3_3 + tail + ".");
    }

    public static String twoBlocks(ListPhrasesUtil phrase, GMTable2_1Repository gm2_1, GMTable2_2Repository gm2_2, GMTable3_1Repository gm3_1) {

        res = gm2_1.findById(random(gm2_1.getCount())).get().getText() + " " + gm2_2.findById(random(gm2_2.getCount())).get().getText() + " " + phrase.tab2_3 + " " + phrase.tab2_4 + ". "
                + gm3_1.findById((gm3_1.getCount() - 1)).get().getText() + " " + phrase.tab3_2 + " " + phrase.tab3_3;

        if (!phrase.tab3_4.equals("")) {
            tail = " " + phrase.tab3_4 + " " + phrase.tab3_5;
        }

        return (res + tail + ".");
    }

    public static String randomTemplates(ListPhrasesUtil phrase, GMTable1Repository gm1, GMTable2_1Repository gm2_1, GMTable2_2Repository gm2_2, GMTable3_1Repository gm3_1) {
        int r = (int) (Math.random() * 2);
        if (r == 0) {
            return threeBlocks(phrase, gm1, gm2_1, gm2_2, gm3_1);
        }
        return twoBlocks(phrase, gm2_1, gm2_2, gm3_1);
    }
}
