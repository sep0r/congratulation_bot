package ru.myproject.congratulation_bot.repository.tost;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.tost.TostSvadba;
import ru.myproject.congratulation_bot.model.tost.TostYubiley;

public interface TostYubileyRepository extends CrudRepository<TostYubiley, Integer> {

    @Query("SELECT COUNT(t) FROM tostYubiley t")
    int getCount();
}
