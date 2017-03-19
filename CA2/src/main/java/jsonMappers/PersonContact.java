package jsonMappers;

import entity.Phone;
import java.util.ArrayList;
import java.util.List;

public class PersonContact {

    public int id;
    public String firstName, lastName, email;
    public List<PhoneJson> phones = new ArrayList();

    public PersonContact(entity.Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        for (Phone phone : person.getPhones()) {
            phones.add(new jsonMappers.PhoneJson(phone));

        }
    }
}
