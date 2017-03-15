package facade;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

public class PersonFacade {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
        PersonFacade pf = new PersonFacade();
//        System.out.println(pf.getPersonById(1).toString());
//        System.out.println(pf.getPersons().toString());
//        System.out.println(pf.getPersonsByZip(800).toString());
//        System.out.println(pf.getPersonByPhoneNumber("9158773").toString());
        System.out.println(pf.getPersonsByHobby("relax").toString());
    }
    
    public Person getPersonById(int id) {
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
    
//    public Person getPersonByPhoneNumber(String phoneNumber) {
//        EntityManager em = getEntityManager();
//        Person person = null;
//        try {
//            em.getTransaction().begin();
//            Query query = em.createQuery("SELECT p FROM Person p WHERE p.phones = :phoneNumber", Person.class);
//            query.setParameter("phoneNumber", phoneNumber);
//            em.getTransaction().commit();
//        } catch (RollbackException r) {
//            r.printStackTrace();
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
//        return person;
//    }
    
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
    
    public List<Person> getPersonsByZip(int zipCode) {
        EntityManager em = getEntityManager();
        List<Person> persons = null;
        try {
            em.getTransaction().begin();
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.address.cityInfo.zip = :zipCode", Person.class);
            query.setParameter("zipCode", zipCode);
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
    
    public List<Person> getPersonsByHobby(String hobby) {
        EntityManager em = getEntityManager();
        List<Person> persons = null;
        try {
            em.getTransaction().begin();
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.hobbies.name = :hobby", Person.class);
            query.setParameter("hobby", hobby);
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
