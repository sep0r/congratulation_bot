package ru.myproject.congratulation_bot.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "anecdoteTable")
public class Anecdote {
    @Id
    private Integer id;

    private String text;

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Anecdote{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
