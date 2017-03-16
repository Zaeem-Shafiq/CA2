package jsonMappers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joacim
 */
public class PersonJson {

    public String firstName, lastName;
    public List<jsonMappers.HobbyJson> hobbies = new ArrayList();

    public PersonJson(entity.Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        for (entity.Hobby hobby : person.getHobbies()) {
            hobbies.add(new jsonMappers.HobbyJson(hobby));
        }
        for (int i = 0; i < 10; i++) {
            
        }
    }

}
