package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Phone implements Serializable {

    @ManyToOne
    private InfoEntity infoEntity;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private String description;

    public Phone(String number, String description) {
        this.number = number;
        this.description = description;
    }

    public Phone(InfoEntity infoEntity, String number, String description) {
        this.infoEntity = infoEntity;
        this.number = number;
        this.description = description;
    }

    public Phone() {
    }

    public void setInfoEntity(InfoEntity infoEntity) {
        this.infoEntity = infoEntity;
    }
    
   

    
    
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Phone{" + "infoEntity=" + infoEntity + ", id=" + id + ", number=" + number + ", description=" + description + '}';
    }
    
    
}
