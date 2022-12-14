package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Override
    public User getById(long id) {
        TypedQuery<User> query = entityManager.createQuery("select u from users u join fetch u.pet where u.id = :id", User.class);
        query.setParameter("id", id);

        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void insert(User user) {
        if (user.getId() == 0L) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(long id) {

//        User userById = entityManager.createQuery("select u from users u join fetch u.pet where u.id = :id", User.class)
//                .setParameter("id", id).getResultList().stream().findAny().orElse(null);
//
//        entityManager.remove(userById);

        User userById = entityManager.find(User.class, id);
        entityManager.remove(userById);

    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select distinct u from users u join fetch u.pet", User.class).getResultList();

    }
}
