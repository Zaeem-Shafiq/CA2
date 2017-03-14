package entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Person extends InfoEntity {

    private String firstName;
    private String lastName;
    @ManyToMany
    private List<Hobby> hobbies;

    public Person() {
    }

    public Person(String firstName, String lastName, List<Hobby> hobbies, String email, List<Phone> phones, Address address) {
        super(email, phones, address);
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
    }

}
