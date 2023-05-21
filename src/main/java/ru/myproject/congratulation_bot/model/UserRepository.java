package ru.myproject.congratulation_bot.model;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
