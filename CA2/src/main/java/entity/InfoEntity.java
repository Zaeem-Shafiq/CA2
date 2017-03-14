package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    @OneToMany(mappedBy = "infoEntity")
    private List<Phone> phones;
    @OneToMany(mappedBy = "infoEntity")
    private List<Address> addresses;

    public InfoEntity() {
    }

    public InfoEntity(Integer id, String email, List<Phone> phones, List<Address> addresses) {
        this.id = id;
        this.email = email;
        this.phones = phones;
        this.addresses = addresses;
    }
    
    public Integer getId() {
        return id;
    }
}
