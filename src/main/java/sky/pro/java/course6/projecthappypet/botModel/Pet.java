package sky.pro.java.course6.projecthappypet.botModel;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AnimalType typeOfAnimal;

    private int age;

    @OneToOne
    private Users users;

    public Pet() {
    }

    public Pet(Long id, String name, AnimalType typeOfAnimal, int age, Users users) {
        this.id = id;
        this.name = name;
        this.typeOfAnimal = typeOfAnimal;
        this.age = age;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalType getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(AnimalType animalTypeOfAnimal) {
        this.typeOfAnimal = animalTypeOfAnimal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return age == pet.age && Objects.equals(id, pet.id)
                && Objects.equals(name, pet.name)
                && typeOfAnimal == pet.typeOfAnimal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, typeOfAnimal, age);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeOfAnimal=" + typeOfAnimal +
                ", age=" + age +
                '}';
    }
}
