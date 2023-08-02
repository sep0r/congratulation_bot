package ru.myproject.congratulation_bot.repository.tost;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.tost.TostLybov;

public interface TostLybovRepository extends CrudRepository<TostLybov, Integer> {

    @Query("SELECT COUNT(t) FROM tostLybov t")
    int getCount();
}
