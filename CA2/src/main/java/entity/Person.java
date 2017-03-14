package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Person extends InfoEntity {

    private String firstName;
    private String lastName;
    @ManyToMany
    private List<Hobby> hobbies;

    public Person() {
    }

    public Person(String firstName, String lastName, List<Hobby> hobbies, Integer id, String email, List<Phone> phones, List<Address> addresses) {
        super(id, email, phones, addresses);
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
    }

}
