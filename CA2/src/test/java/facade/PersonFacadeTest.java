/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

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
    
    public PersonFacadeTest() {
    }

    @Before
    public void startUp(){
        Persistence.generateSchema("pu_test", null);
        DataGenerator dg = new DataGenerator();
        dg.starter();
    }
    
    @Test
    public void testSomeMethod() {
    }
    
}
