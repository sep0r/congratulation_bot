package ru.myproject.congratulation_bot.model.goodMorningRandom.part1;

import ru.myproject.congratulation_bot.model.goodMorningRandom.AbstractGMTable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "GMTable1")
public class GMTable1 extends AbstractGMTable {
    @Id
    private Integer id;

    private String text;

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "GMTable1{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
