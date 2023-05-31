package ru.myproject.congratulation_bot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.myproject.congratulation_bot.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
