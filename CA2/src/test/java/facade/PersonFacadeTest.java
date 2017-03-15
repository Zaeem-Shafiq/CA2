/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import entity.Phone;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.ejb.Startup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.DataGenerator;
import org.junit.After;
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
        //assertEquals("Andy", pf.getPersonsByZip(1218));
    }
}
