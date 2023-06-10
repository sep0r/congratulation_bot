package ru.myproject.congratulation_bot;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.myproject.congratulation_bot.service.goodMorningRandom.util.Utils;


@SpringBootTest
class CongratulationBotApplicationTests {

    Utils utils;

    //От 1 и до num
    @Test
    void randomForTable() {
        int res = Utils.randomForTable(1);
        assertTrue(res != 0);
        assertEquals(res, 1);
    }

    //От 0 и до (num - 1)
    @Test
    void random() {
        int res = Utils.random(1);
        assertEquals(0, res);
    }
}
