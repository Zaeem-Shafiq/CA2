package facade;

import entity.Person;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PersonFacadeTest {

    private PersonFacade pf;

    public PersonFacadeTest() {
        pf = new PersonFacade("pu_test");
    }

    @Before
    public void startUp() {
        TestDataGenerator tdg = new TestDataGenerator("pu_test");
        tdg.starter();
    }

    @Test
    public void testGetPersonById() {
        assertEquals("Andy", pf.getPersonById(1).getFirstName());
    }

    @Test
    public void testGetPersonByPhone() {
        assertEquals("Andy", pf.getPersonByPhoneNumber("12345671").getFirstName());
    }

    @Test
    public void testGetPersons() {
        List<Person> p = pf.getPersons();
        assertEquals(3, p.size());
        assertEquals("Andy", p.get(0).getFirstName());
        assertEquals("Taunya", p.get(1).getFirstName());
        assertEquals("Pearle", p.get(2).getFirstName());
    }

    @Test
    public void testGetPersonByZip() {
        List<Person> p = pf.getPersonsByZip(2720);
        assertEquals(3, p.size());
        assertEquals("Andy", p.get(0).getFirstName());
        assertEquals("Taunya", p.get(1).getFirstName());
        assertEquals("Pearle", p.get(2).getFirstName());
    }
    
    @Test
    public void testGetPersonByHobby() {
        List<Person> p = pf.getPersonsByHobby("football");
        assertEquals(1, p.size());
        assertEquals("Andy", p.get(0).getFirstName());
    }
}
