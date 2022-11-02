package ru.itsjava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    @ManyToOne(targetEntity = Pet.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;





    @Override
    public String toString() {
        return "id: " + id + ", имя: " + name + ", возраст: " + age + ", питомец: " + pet;
    }
}
