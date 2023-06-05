package ru.myproject.congratulation_bot.service.goodMorningRandom.util;

public class Utils {

    public static int random(int num) {
        return (int) (Math.random() * (num));
    }

    public static int randomForTable(int tableSize) {
        return 1 + (int) (Math.random() * (tableSize));
    }

    public static boolean fiftyFifty() {
        int i = (int) (Math.random() * 2);
        return i > 0;
    }

    public static int secondPhrase(int randomNumber, int tableSize) { //Не работает для 3 фраз, нужно исправить
        int random;
        do {
            random = (int) (Math.random() * (tableSize));
        } while (randomNumber == random);
        return random;
    }
}
