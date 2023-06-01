package ru.myproject.congratulation_bot.repository.goodMorningRandom;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.goodMorningRandom.part3.GMTable3_3_2;

public interface GMTable3_3_2Repository extends CrudRepository<GMTable3_3_2, Integer> {

    @Query("SELECT COUNT(g) FROM GMTable3_3_2 g")
    int getCount();
}
