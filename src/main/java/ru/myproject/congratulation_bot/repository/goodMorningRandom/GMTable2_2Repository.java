package ru.myproject.congratulation_bot.repository.goodMorningRandom;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.goodMorningRandom.part2.GMTable2_2;

public interface GMTable2_2Repository extends CrudRepository<GMTable2_2, Integer> {

    @Query("SELECT COUNT(g) FROM GMTable2_2 g")
    int getCount();
}
