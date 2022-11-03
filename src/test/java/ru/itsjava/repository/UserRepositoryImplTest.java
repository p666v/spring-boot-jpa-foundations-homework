package ru.itsjava.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(UserRepositoryImpl.class)
public class UserRepositoryImplTest {
    public static final long DEFAULT_USER_ID = 1L;
    public static final Pet pet = new Pet(1L, "Rabbit");

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("Корректно возвращает пользователя по id")
    @Test
    public void shouldHaveCorrectGetById() {
        var expectedUser = entityManager.find(User.class, DEFAULT_USER_ID);
        var actualUser = userRepository.getById(DEFAULT_USER_ID);

        assertEquals(expectedUser, actualUser);
    }

    @DisplayName("Корректно добавляет пользователя в БД")
    @Test
    public void shouldHaveCorrectInsert() {
        var expectedUser = new User(4L, "TANOS", 1000000, pet);
        userRepository.insert(expectedUser);
        var actualUser = userRepository.getById(4L);

        assertEquals(expectedUser, actualUser);
    }

    @DisplayName("Корректно обновляет пользователя в БД")
    @Test
    public void shouldHaveCorrectUpdate() {
        var user = userRepository.getById(1L);
        user.setName("TANOS");
        userRepository.update(user);
        var actualUser = userRepository.getById(1L);

        assertEquals("TANOS", actualUser.getName());
    }

    @DisplayName("Корректно удаляет пользователя по id")
    @Test
    public void shouldHaveCorrectDeleteById() {
        userRepository.deleteById(3L);
        var deletedUser = userRepository.getById(3L);

        assertNull(deletedUser);
    }

    @DisplayName("Корректно возвращает список всех пользователей")
    @Test
    public void shouldHaveCorrectFindAll() {
        var expectedUsers = entityManager
                .createQuery("select distinct u from users u join fetch u.pet", User.class).getResultList();
        var actualUsers = userRepository.findAll();

        assertEquals(expectedUsers, actualUsers);
    }

}
