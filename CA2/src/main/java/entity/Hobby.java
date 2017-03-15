package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @ManyToMany(mappedBy = "hobbies")
    private List<Person> persons;

    public Hobby() {
    }

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Hobby(String name, String description, List<Person> persons) {
        this.name = name;
        this.description = description;
        this.persons = persons;
    }

    
    
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Hobby{" + "id=" + id + ", name=" + name + ", description=" + description + ", persons=" + persons + '}';
    }

    
    
}
