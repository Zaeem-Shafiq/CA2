package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer zip;
    private String city;

    public CityInfo() {
    }

    public CityInfo(Integer zip, String city) {
        this.zip = zip;
        this.city = city;
    }

    public Integer getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "CityInfo{" + "zip=" + zip + ", city=" + city + '}';
    }

}
