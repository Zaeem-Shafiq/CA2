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

    public Company(String name, String description, Integer cvr, Integer numEmployees, Integer marketValue, String email, List<Phone> phones, Address address) {
        super(email, phones, address);
        this.name = name;
        this.description = description;
        this.cvr = cvr;
        this.numEmployees = numEmployees;
        this.marketValue = marketValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCvr() {
        return cvr;
    }

    public void setCvr(Integer cvr) {
        this.cvr = cvr;
    }

    public Integer getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(Integer numEmployees) {
        this.numEmployees = numEmployees;
    }

    public Integer getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Integer marketValue) {
        this.marketValue = marketValue;
    }

    @Override
    public String toString() {
        return "Company{" + "name=" + name + ", description=" + description + ", cvr=" + cvr + ", numEmployees=" + numEmployees + ", marketValue=" + marketValue + '}';
    }
}
