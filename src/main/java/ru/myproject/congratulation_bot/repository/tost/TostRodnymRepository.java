package ru.myproject.congratulation_bot.repository.tost;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.tost.TostPrikolnye;
import ru.myproject.congratulation_bot.model.tost.TostRodnym;

public interface TostRodnymRepository extends CrudRepository<TostRodnym, Integer> {

    @Query("SELECT COUNT(t) FROM tostRodnym t")
    int getCount();
}
