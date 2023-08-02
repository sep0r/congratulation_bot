package ru.myproject.congratulation_bot.repository.tost;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.tost.TostRodnym;
import ru.myproject.congratulation_bot.model.tost.TostSvadba;

public interface TostSvadbaRepository extends CrudRepository<TostSvadba, Integer> {

    @Query("SELECT COUNT(t) FROM tostSvadba t")
    int getCount();
}
