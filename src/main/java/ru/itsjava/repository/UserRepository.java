package ru.itsjava.repository;

import ru.itsjava.domain.User;

import java.util.List;

public interface UserRepository {
    User getById(long id);

    void insert(User user);

    void update(User user);

    void deleteById(long id);

    List<User> findAll();

}
