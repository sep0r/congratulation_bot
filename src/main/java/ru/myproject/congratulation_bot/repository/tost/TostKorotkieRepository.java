package ru.myproject.congratulation_bot.repository.tost;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.tost.TostKorotkie;

public interface TostKorotkieRepository extends CrudRepository<TostKorotkie, Integer> {

    @Query("SELECT COUNT(t) FROM tostKorotkie t")
    int getCount();
}
