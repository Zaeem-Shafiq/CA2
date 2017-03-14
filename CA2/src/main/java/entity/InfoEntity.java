package entity;

import com.sun.javafx.css.CascadingStyle;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    
    @OneToMany(mappedBy = "infoEntity",cascade = CascadeType.PERSIST)
    private List<Phone> phones;
    
   
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;

    public InfoEntity() {
    }

    public InfoEntity(String email, List<Phone> phones, Address address) {
        this.email = email;
        this.phones = phones;
        this.address = address;
    }
    
    public Integer getId() {
        return id;
    }
}
