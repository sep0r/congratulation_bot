package ru.myproject.congratulation_bot.repository.goodMorningRandom;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.goodMorningRandom.part3.GMTable3_2_1;

public interface GMTable3_2_1Repository extends CrudRepository<GMTable3_2_1, Integer> {

    @Query("SELECT COUNT(g) FROM GMTable3_2_1 g")
    int getCount();
}
