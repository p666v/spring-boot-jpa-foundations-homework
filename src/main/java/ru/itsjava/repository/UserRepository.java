package ru.itsjava.repository;

import ru.itsjava.domain.User;

public interface UserRepository {
    User getById(long id);

    void insert(User user);

    void update(User user);

    void deleteById(long id);

}
