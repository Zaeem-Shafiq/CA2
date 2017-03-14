package model;

import entity.Address;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joacim
 */
public class DataGenerator {

    public static void main(String[] args) {

        List<Person> persons = new ArrayList();
        List<Phone> phoneNumbers = new ArrayList();
        
        Address address = new Address();
//    public Address(InfoEntity infoEntity, String street, String additionalInfo, CityInfo cityInfo, List<InfoEntity> infoEntities) {

        List<Hobby> hobbies = new ArrayList();

        hobbies.add(new Hobby("Magic Cards", "Magic card playing", persons));

   
        Person p = new Person("asger", "lundblad",hobbies,"mail",phoneNumbers,address);
}
}
