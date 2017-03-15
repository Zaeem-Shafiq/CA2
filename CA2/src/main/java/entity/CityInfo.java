package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer zip;
    private String city;

    public Integer getId() {
        return id;
    }

    public CityInfo() {
    }

    public CityInfo(Integer id, Integer zip, String city) {
        this.id = id;
        this.zip = zip;
        this.city = city;
    }

    @Override
    public String toString() {
        return "CityInfo{" + "id=" + id + ", zip=" + zip + ", city=" + city + '}';
    }

}
