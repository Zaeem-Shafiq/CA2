package facade;

import entity.Company;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

public class CompanyFacade {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
        CompanyFacade cf = new CompanyFacade();
        System.out.println(cf.getCompanyByCvr(768484).toString());
    }
    
    public Company getCompanyByCvr(int cvr) {
        EntityManager em = getEntityManager();
        Company company = null;
        try {
            em.getTransaction().begin();
            TypedQuery<Company> query = em.createQuery("SELECT c FROM Company c WHERE c.cvr = :cvr", Company.class);
            query.setParameter("cvr", cvr);
            company = query.getSingleResult();
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return company;
    }
    
    public List<Person> getPersons() {
        EntityManager em = getEntityManager();
        List<Person> persons = null;
        try {
            em.getTransaction().begin();
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            persons = query.getResultList();
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return persons;
    }
    

}
