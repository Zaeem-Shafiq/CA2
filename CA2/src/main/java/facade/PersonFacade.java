package facade;

import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

public class PersonFacade {

    private EntityManagerFactory emf;

    public PersonFacade(String persistence) {
        emf = Persistence.createEntityManagerFactory(persistence);
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

//    public static void main(String[] args) {
        //PersonFacade pf = new PersonFacade("PU");

//        PersonFacade pf = new PersonFacade("PU");
//        System.out.println(pf.getPersonById(1).toString());
//        System.out.println(pf.getPersonById(1).toString());

//        System.out.println(pf.getPersonByPhoneNumber("89851654").toString());
//        System.out.println(pf.getPersons().toString());
//        System.out.println(pf.getPersonsByZip(800).toString());
//        System.out.println(pf.getPersonsByHobby("football").toString());
//        System.out.println(pf.getCountOfPersonsWithHobby("football"));
//        System.out.println(pf.getZipCodesInDk());
//    }
//        System.out.println(pf.getCityInfoByZip(3390));
//    }

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

    public Person getPersonByPhoneNumber(String phoneNumber) {
        EntityManager em = getEntityManager();
        Person person = null;
        try {
            em.getTransaction().begin();
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.phones ph WHERE ph.number = :phoneNumber", Person.class);
            query.setParameter("phoneNumber", phoneNumber);
            person = query.getSingleResult();
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
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :hobby", Person.class);
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

    public int getCountOfPersonsWithHobby(String hobby) {
        EntityManager em = getEntityManager();
        int count = 0;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT COUNT(p) FROM Person p JOIN p.hobbies h WHERE h.name = :hobby");
            query.setParameter("hobby", hobby);
            count = Integer.parseInt(query.getSingleResult().toString());
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return count;
    }

    public List<CityInfo> getZipCodesInDk() {
        EntityManager em = getEntityManager();
        List<CityInfo> zipCodes = null;
        try {
            em.getTransaction().begin();
            TypedQuery<CityInfo> query = em.createQuery("SELECT c.zip FROM CityInfo c WHERE c.id < 1190", CityInfo.class);
            zipCodes = query.getResultList();
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return zipCodes;
    }

    public CityInfo getCityInfoByZip(int zipCode) {
        EntityManager em = getEntityManager();
        CityInfo cityInfo = null;
        try {
            em.getTransaction().begin();
            TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c WHERE c.zip = :zipCode", CityInfo.class);
            query.setParameter("zipCode", zipCode);
            cityInfo = query.getSingleResult();
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return cityInfo;
    }

    public Person createPerson(String firstName, String lastName, String hobbyName, String hobbyDescription,
            String email, String phoneNumber, String phoneDescription, int zipCode, String street, String additionalAddressInfo) {
        EntityManager em = getEntityManager();
        List<Hobby> hobbies = new ArrayList();
        List<Phone> phones = new ArrayList();
        CityInfo cityInfo = getCityInfoByZip(zipCode);
        Address address = new Address(street, additionalAddressInfo, cityInfo);
 
        hobbies.add(new Hobby(hobbyName, hobbyDescription));
        phones.add(new Phone(phoneNumber, phoneDescription));
        
        Person person = new Person(firstName, lastName, hobbies, email, phones, address);
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return person;
    }
}
