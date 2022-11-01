package ru.itsjava.repository;

import ru.itsjava.domain.Pet;

import java.util.List;

public interface PetRepository {
    Pet getById(long id);

    void insert(Pet pet);

    void update(Pet pet);

    void deleteById(long id);

    List<Pet> findAll();
}
