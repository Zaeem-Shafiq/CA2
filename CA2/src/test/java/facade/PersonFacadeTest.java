/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import javax.ejb.Startup;
import javax.persistence.Persistence;
import model.DataGenerator;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Asger
 */
public class PersonFacadeTest {
    private PersonFacade pf;
    
    public PersonFacadeTest() {
        pf = new PersonFacade("pu_test");
    }

    @Before
    public void startUp(){
        Persistence.generateSchema("pu_test", null);
        TestDataGenerator tdg = new TestDataGenerator("pu_test");
        tdg.starter();
    }
    
    @Test
    public void testGetPersonById() {
        assertEquals("Dann", pf.getPersonById(1).getFirstName());
    }
    
}
