package facade;

import entity.Company;
import java.util.List;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class CompanyFacadeTest {

    private final CompanyFacade cf;

    public CompanyFacadeTest() {
        cf = new CompanyFacade("pu_test");
    }
    
    @Before
    public void startUp() {
        Persistence.generateSchema("pu_test", null);
        TestDataGenerator tdg = new TestDataGenerator("pu_test");
        tdg.starter();
    }
    
    @Test
    public void testGetCompanyByCvr() {
        Company c = cf.getCompanyByCvr(12345671);
        assertEquals(" Carson Dermatology Assoc", c.getName());
    }

    @Test
    public void testGetCompanyByPhone() {
        Company c = cf.getCompanyByPhoneNumber("12345681");
        assertEquals(" Carson Dermatology Assoc", c.getName());
    }
    
    @Test
    public void testGetCompaniesWithEmployees(){
        List<Company> c = cf.getCompaniesWithEmployeesMoreThan(11);
        assertEquals(2, c.size());
        assertEquals(12, Integer.parseInt(c.get(0).getNumEmployees().toString()));
        assertEquals(13, Integer.parseInt(c.get(1).getNumEmployees().toString()));
    }
}
