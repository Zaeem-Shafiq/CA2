package jsonMappers;

import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joacim
 */
public class PersonJson {

    public int id;
    public String firstName, lastName, email;
    public AddressJson address;
    public List<HobbyJson> hobbies = new ArrayList();
    public List<PhoneJson> phones = new ArrayList();
    public CityInfoJson cityInfo;

    public PersonJson(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        this.cityInfo = new jsonMappers.CityInfoJson(person.getAddress().getCityInfo());
        this.address = new jsonMappers.AddressJson(person.getAddress());
        
        for (Phone phone : person.getPhones()) {
            phones.add(new jsonMappers.PhoneJson(phone));

        }
        for (Hobby hobby : person.getHobbies()) {
            hobbies.add(new jsonMappers.HobbyJson(hobby));
        }
    }

}
