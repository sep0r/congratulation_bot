package ru.myproject.congratulation_bot.repository.tost;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.tost.TostKrasivye;

public interface TostKrasivyeRepository extends CrudRepository<TostKrasivye, Integer> {

    @Query("SELECT COUNT(t) FROM tostKrasivye t")
    int getCount();
}
