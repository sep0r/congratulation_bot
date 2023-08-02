package ru.myproject.congratulation_bot.repository.tost;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.tost.TostPrazdnik;
import ru.myproject.congratulation_bot.model.tost.TostPrikolnye;

public interface TostPrikolnyeRepository extends CrudRepository<TostPrikolnye, Integer> {

    @Query("SELECT COUNT(t) FROM tostPrikolnye t")
    int getCount();
}
