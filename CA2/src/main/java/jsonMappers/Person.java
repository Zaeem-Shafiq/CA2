
package jsonMappers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joacim
 */
public class Person {
    String firstName,lastName;
    List<jsonMappers.Hobby> hobbies = new ArrayList();

    public Person(entity.Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        for (entity.Hobby hobby : person.getHobbies()) {
            hobbies.add(new jsonMappers.Hobby(hobby));
        }
    }
    
}
