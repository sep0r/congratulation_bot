package ru.myproject.congratulation_bot.model.goodMorningRandom.part3;

import ru.myproject.congratulation_bot.model.goodMorningRandom.AbstractGMTable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "GMTable3_4_2")
public class GMTable3_4_2 extends AbstractGMTable {
    @Id
    private Integer id;

    private String text;

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "GMTable3_4_2{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
