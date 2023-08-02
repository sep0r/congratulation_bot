package ru.myproject.congratulation_bot.model.tost;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tostKorotkie")
public class TostKorotkie {
    @Id
    private Integer id;

    private String text;

    public String getText() {
        return text;
    }
}