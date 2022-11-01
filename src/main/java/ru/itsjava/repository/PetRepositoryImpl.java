package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Pet;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class PetRepositoryImpl implements PetRepository {
    private final EntityManager entityManager;

    @Override
    public Pet getById(long id) {
        return entityManager.find(Pet.class, id);
    }

    @Override
    public void insert(Pet pet) {
        if (pet.getId() == 0L) {
            entityManager.persist(pet);
        } else {
            entityManager.merge(pet);
        }
    }

    @Override
    public void update(Pet pet) {
        entityManager.merge(pet);
    }

    @Override
    public void deleteById(long id) {
        Pet petById = entityManager.find(Pet.class, id);
        entityManager.remove(petById);
    }

    @Override
    public List<Pet> findAll() {
        return entityManager.createQuery("select p from pets p", Pet.class).getResultList();
    }
}
