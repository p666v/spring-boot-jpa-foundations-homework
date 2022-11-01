package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;
import ru.itsjava.repository.PetRepository;
import ru.itsjava.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class SpringBootJpaFoundationsHomeworkApplication {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(SpringBootJpaFoundationsHomeworkApplication.class, args);
        PetRepository petRepository = context.getBean(PetRepository.class);
        System.out.println("petRepository.getById(1L) = " + petRepository.getById(1L));

        Pet pet = new Pet(3L, "hamster");
        petRepository.insert(pet);
        System.out.println("petRepository.getById(3L) = " + petRepository.getById(3L));

        Pet pet3 = petRepository.getById(3L);
        pet3.setBreed("rabbit");
        petRepository.update(pet3);
        System.out.println("petRepository.getById(3L) = " + petRepository.getById(3L));

        petRepository.deleteById(3L);
        System.out.println("petRepository.getById(3L) = " + petRepository.getById(3L));

        List<Pet> petList = petRepository.findAll();

        for (Pet pets : petList) {
            System.out.println(pets);
        }


        System.out.println("=================================");

        UserRepository userRepository = context.getBean(UserRepository.class);
        System.out.println("userRepository.getById(1L) = " + userRepository.getById(1L));

        List<User> userList = userRepository.findAll();
        for (User users : userList) {
            System.out.println(users);
        }
        System.out.println();

        User user = new User(4L, "Ira", 17, petRepository.getById(2L));
        userRepository.insert(user);
        List<User> userList2 = userRepository.findAll();
        for (User users : userList2) {
            System.out.println(users);
        }
        System.out.println();



//        Console.main(args);
    }

}
