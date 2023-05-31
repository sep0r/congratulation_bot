package ru.myproject.congratulation_bot.model.goodMorningRandom.part2;

import ru.myproject.congratulation_bot.model.goodMorningRandom.AbstractGMTable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "GMTable2_3_1")
public class GMTable2_3_1 extends AbstractGMTable {
    @Id
    private Integer id;

    private String text;

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "GMTable2_3_1{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
