package ru.myproject.congratulation_bot.service.goodMorningRandom;

import ru.myproject.congratulation_bot.repository.goodMorningRandom.GMTable1Repository;
import ru.myproject.congratulation_bot.repository.goodMorningRandom.GMTable2_1Repository;
import ru.myproject.congratulation_bot.service.goodMorningRandom.util.ListPhrasesUtil;

import static ru.myproject.congratulation_bot.service.goodMorningRandom.util.Utils.random;

public class TemplatesForRandom {
    static private String tail = "";
    static private String preTail = "";
    static private String res;

    public static String threeBlocks(GMTable1Repository gm1, GMTable2_1Repository gm2_1, GMTable1Repository gm1, GMTable1Repository gm1,
                                     GMTable1Repository gm1, GMTable1Repository gm1, GMTable1Repository gm1, GMTable1Repository gm1,
                                     GMTable1Repository gm1, GMTable1Repository gm1, GMTable1Repository gm1, GMTable1Repository gm1,
                                     GMTable1Repository gm1, GMTable1Repository gm1, GMTable1Repository gm1, GMTable1Repository gm1,) {

        GMTable1Repository.

        res = phrase.table1.getTable(random(phrase.table1.getSize())) + " "
                + phrase.table2_1.getTable(random(phrase.table2_1.getSize())) + " " + phrase.table2_2.getTable(random(phrase.table2_2.getSize())) + " " + phrase.tab2_3 + " " + phrase.tab2_4 + ". "
                + phrase.table3_1.getTable((phrase.table3_1.getSize() - 1));

        if (!phrase.tab3_2.equals("")) {
            preTail = " " + phrase.tab3_2;
        }

        if (!phrase.tab3_4.equals("")) {
            tail = " " + phrase.tab3_4 + " " + phrase.tab3_5;
        }

        return (res + preTail + " " + phrase.tab3_3 + tail + ".");
    }

    public static String twoBlocks(ListPhrasesUtil phrase) {
        res = phrase.table2_1.getTable(random(phrase.table2_1.getSize())) + " " + phrase.table2_2.getTable(random(phrase.table2_2.getSize())) + " " + phrase.tab2_3 + " " + phrase.tab2_4 + ". "
                + phrase.table3_1.getTable((phrase.table3_1.getSize() - 1)) + " " + phrase.tab3_2 + " " + phrase.tab3_3;

        if (!phrase.tab3_4.equals("")) {
            tail = " " + phrase.tab3_4 + " " + phrase.tab3_5;
        }

        return (res + tail + ".");
    }

    public String randomTemplates(ListPhrasesUtil phrase) {
        int r = (int) (Math.random() * 2);
        if (r == 0) {
            return threeBlocks(phrase);
        }
        return twoBlocks(phrase);
    }
}
