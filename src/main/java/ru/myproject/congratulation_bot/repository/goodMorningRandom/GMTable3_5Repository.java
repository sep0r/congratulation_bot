package ru.myproject.congratulation_bot.repository.goodMorningRandom;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.goodMorningRandom.part3.GMTable3_5;

public interface GMTable3_5Repository extends CrudRepository<GMTable3_5, Integer> {

    @Query("SELECT COUNT(g) FROM GMTable3_5 g")
    int getCount();
}
