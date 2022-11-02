package ru.itsjava.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(PetRepositoryImpl.class)
public class PetRepositoryImplTest {
    public static final long DEFAULT_PET_ID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PetRepository petRepository;

    @DisplayName("Корректно возвращает питомца по id")
    @Test
    public void shouldHaveCorrectGetById() {
        var expectedPet = entityManager.find(Pet.class, DEFAULT_PET_ID);
        var actualPet = petRepository.getById(DEFAULT_PET_ID);

        assertEquals(expectedPet, actualPet);
    }

    @DisplayName("Корректно добавляет питомца в БД")
    @Test
    public void shouldHaveCorrectInsert() {
        var expectedPet = new Pet(4L, "Snake");
        petRepository.insert(expectedPet);
        var actualPet = petRepository.getById(4L);

        assertEquals(expectedPet, actualPet);
    }

    @DisplayName("Корректно обновляет питомца в БД")
    @Test
    public void shouldHaveCorrectUpdate() {
        var pet = petRepository.getById(1L);
        pet.setBreed("Snake");
        petRepository.update(pet);
        var actualPet = petRepository.getById(1L);

        assertEquals("Snake", actualPet.getBreed());
    }

    @DisplayName("Корректно удаляет питомца по id")
    @Test
    public void shouldHaveCorrectDeleteById() {
        petRepository.deleteById(3L);
        var deletedPet = petRepository.getById(3L);

        assertNull(deletedPet);
    }

    @DisplayName("Корректно возвращает список всех питомцев")
    @Test
    public void shouldHaveCorrectFindAll() {
        var expectedPets = entityManager.createQuery("select p from pets p", Pet.class).getResultList();
        var actualPets = petRepository.findAll();

        assertEquals(expectedPets, actualPets);
    }
}
