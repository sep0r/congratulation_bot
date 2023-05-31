package ru.myproject.congratulation_bot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.Anecdote;

public interface AnecdoteRepository extends CrudRepository<Anecdote, Integer> {

    @Query("SELECT COUNT(a) FROM anecdoteTable a")
    int getCount();
}
