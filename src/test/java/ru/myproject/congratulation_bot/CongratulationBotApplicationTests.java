package ru.myproject.congratulation_bot;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.myproject.congratulation_bot.service.goodMorningRandom.util.Utils;


@SpringBootTest
class CongratulationBotApplicationTests {

    Utils utils;

    @Test
    void randomForTable() {
        int res1 = Utils.randomForTable(1);
        assertTrue(res1 != 0);
        assertEquals(res1, 1);
    }

    @Test
    void random() {
        int res = Utils.random(1);
        assertEquals(0, res);
    }
}
