package facade;

import entity.CityInfo;
import entity.Person;
import java.util.List;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PersonFacadeTest {

    private final PersonFacade pf;

    public PersonFacadeTest() {
        pf = new PersonFacade("pu_test");
    }

    //@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql")
    
    @Before
    public void startUp() {
        Persistence.generateSchema("pu_test", null);
        TestDataGenerator tdg = new TestDataGenerator("pu_test");
        tdg.starter();
    }
//    @AfterClass
//    public static void tearDown(){
//        Properties prop = new Properties();
//        prop.put("javax.persistence.jdbc.url", "jdbc:derby:memory:myDB;shutdown=true");
//        Persistence.generateSchema("pu_test", prop);
//    }

//    @Test
//    public void testGetPersonById() {
//        assertEquals("Andy", pf.getPersonById(1).getFirstName());
//    }

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
        List<Person> p = pf.getPersonsByZip(800);
        assertEquals(1, p.size());
        assertEquals("Andy", p.get(0).getFirstName());
    }
    
    @Test
    public void testGetPersonByHobby() {
        List<Person> p = pf.getPersonsByHobby("football");
        assertEquals(1, p.size());
        assertEquals("Andy", p.get(0).getFirstName());
    }
    
    @Test
    public void testGetCountOfPersonsWithHobby() {
        int i = pf.getCountOfPersonsWithHobby("football");
        assertEquals(1, i);
    }
    
//    @Test
//    public void TestGetZipCodesInDk(){
//        List<CityInfo> ci = pf.getZipCodes();
//        assertEquals(1189, ci.size());
//    }
    
    @Test
    public void TestGetCityInfoByZip() {
       CityInfo ci = pf.getCityInfoByZip(2720);
        assertEquals("Vanløse", ci.getCity());
    }

    @Test
    public void TestCreatePerson() {
        
    }
}
