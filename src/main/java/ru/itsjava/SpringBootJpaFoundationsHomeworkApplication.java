package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.domain.Pet;
import ru.itsjava.repository.PetRepository;

import java.sql.SQLException;

@SpringBootApplication
public class SpringBootJpaFoundationsHomeworkApplication {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(SpringBootJpaFoundationsHomeworkApplication.class, args);
        PetRepository petRepository = context.getBean(PetRepository.class);
        System.out.println("petRepository.getById(1L) = " + petRepository.getById(1L));

        Pet pet = new Pet(3L,"hamster");
        petRepository.insert(pet);
        System.out.println("petRepository.getById(3L) = " + petRepository.getById(3L));

        Pet pet3 = petRepository.getById(3L);
        pet3.setBreed("rabbit");
        petRepository.update(pet3);
        System.out.println("petRepository.getById(3L) = " + petRepository.getById(3L));

        petRepository.deleteById(3L);
        System.out.println("petRepository.getById(3L) = " + petRepository.getById(3L));

        System.out.println(petRepository.findAll());


//        Console.main(args);
    }

}
