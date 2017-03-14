package entity;

import java.util.List;
import javax.persistence.Entity;

@Entity
public class Company extends InfoEntity {

    private String name;
    private String description;
    private Integer cvr;
    private Integer numEmployees;
    private Integer marketValue;

    public Company() {
    }

    public Company(String name, String description, Integer cvr, Integer numEmployees, Integer marketValue, Integer id, String email, List<Phone> phones, List<Address> addresses) {
        super(id, email, phones, addresses);
        this.name = name;
        this.description = description;
        this.cvr = cvr;
        this.numEmployees = numEmployees;
        this.marketValue = marketValue;
    }
    
}
