package ru.myproject.congratulation_bot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.Anecdote;
import ru.myproject.congratulation_bot.model.Tost;

public interface TostRepository extends CrudRepository<Tost, Integer> {

    @Query("SELECT COUNT(t) FROM tostTable t")
    int getCount();
}
