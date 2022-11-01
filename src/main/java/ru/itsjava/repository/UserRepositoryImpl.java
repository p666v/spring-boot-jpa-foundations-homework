package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(long id) {

    }
}
