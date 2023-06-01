package ru.myproject.congratulation_bot.repository.goodMorningRandom;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.goodMorningRandom.part2.GMTable2_1;

public interface GMTable2_1Repository extends CrudRepository<GMTable2_1, Integer> {

    @Query("SELECT COUNT(g) FROM GMTable2_1 g")
    int getCount();
}
