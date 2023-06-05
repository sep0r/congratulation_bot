package ru.myproject.congratulation_bot.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tostTable")
public class Tost {
    @Id
    private Integer id;

    private String text;

    public String getText() {
        return text;
    }
}