package ru.myproject.congratulation_bot.repository.goodMorningRandom;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.goodMorningRandom.part1.GMTable1;

public interface GMTable1Repository extends CrudRepository<GMTable1, Integer>, AbstractGMTableRepository {

    @Override
    @Query("SELECT COUNT(g) FROM GMTable1 g")
    int getCount();
}