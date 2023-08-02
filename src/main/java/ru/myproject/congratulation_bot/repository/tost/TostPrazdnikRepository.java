package ru.myproject.congratulation_bot.repository.tost;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.tost.TostLybov;
import ru.myproject.congratulation_bot.model.tost.TostPrazdnik;

public interface TostPrazdnikRepository extends CrudRepository<TostPrazdnik, Integer> {

    @Query("SELECT COUNT(t) FROM tostPrazdnik t")
    int getCount();
}
