package facade;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

public class PersonFacade {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
        PersonFacade pf = new PersonFacade();
        System.out.println(pf.getPerson(1).toString());
        System.out.println(pf.getPersons().toString());
    }
    
    public Person getPerson(int id) {
        EntityManager em = getEntityManager();
        Person person = null;
        try {
            em.getTransaction().begin();
            person = em.find(Person.class, id);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return person;
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
