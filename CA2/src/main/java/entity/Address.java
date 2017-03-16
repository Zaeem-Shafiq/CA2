package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String additionalInfo;
    @ManyToOne
    private CityInfo cityInfo;
    @OneToMany(mappedBy = "address", cascade = {CascadeType.ALL})
    private List<InfoEntity> infoEntities;

    public Address() {
    }

    public Address(String street, String additionalInfo, CityInfo cityInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfo = cityInfo;
    }

    public Address(String street, String additionalInfo, CityInfo cityInfo, List<InfoEntity> infoEntities) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfo = cityInfo;
        this.infoEntities = infoEntities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    public List<InfoEntity> getInfoEntities() {
        return infoEntities;
    }

    public void setInfoEntities(List<InfoEntity> infoEntities) {
        this.infoEntities = infoEntities;
    }

}
