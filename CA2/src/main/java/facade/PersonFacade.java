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

    public static void main(String[] args) {
        PersonFacade pf = new PersonFacade("PU");

//        System.out.println(pf.getPersonById(1).toString());
//        System.out.println(pf.getPersonByPhoneNumber("89851654").toString());
//        System.out.println(pf.getPersons().toString());
//        System.out.println(pf.getPersonsByZip(800).toString());
//        System.out.println(pf.getPersonsByHobby("football").toString());
//        System.out.println(pf.getCountOfPersonsWithHobby("football"));
//        System.out.println(pf.getZipCodesInDk());
//        System.out.println(pf.getCityInfoByZip(3390));
        List<Hobby> hobbies = new ArrayList();
        List<Phone> phones = new ArrayList();
        hobbies.add(new Hobby("Football", "Score goals"));
        phones.add(new Phone("99999999", "no des"));
//        System.out.println(pf.createPerson("Peter", "Klausen", hobbies, "hej@hotmail.com", phones, 2500, "En vej 212", "nothing"));
        System.out.println(pf.updatePerson(30, "Lars", "Tomsen", hobbies, "Mail@gmail.com", phones, 2635, "Gedemarksvej 60, 2. th", "Hello"));
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

    public List<Phone> getPhones() {
        EntityManager em = getEntityManager();
        List<Phone> phones = null;
        try {
            em.getTransaction().begin();
            TypedQuery<Phone> query = em.createQuery("SELECT p FROM Phone p", Phone.class);
            phones = query.getResultList();
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return phones;
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

        public Person createPerson(Person person) {
        EntityManager em = getEntityManager();
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
    
//    public Person createPerson(String firstName, String lastName, List<Hobby> hobbies, String email, List<Phone> phones, int zipCode, String street, String additionalAddressInfo) {
//        EntityManager em = getEntityManager();
//        CityInfo cityInfo = getCityInfoByZip(zipCode);
//        Address address = new Address(street, additionalAddressInfo, cityInfo);
//
//        Person person = new Person(firstName, lastName, hobbies, email, phones, address);
//        for (Phone phone : phones) {
//            phone.setInfoEntity(person);
//        }
//        
//        try {
//            em.getTransaction().begin();
//            em.persist(person);
//            em.getTransaction().commit();
//        } catch (RollbackException r) {
//            r.printStackTrace();
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
//        return person;
//    }
    
    public Person updatePerson(int id, String firstName, String lastName, List<Hobby> hobbies, String email, List<Phone> phones, int zipCode, String street, String additionalAddressInfo) {
        EntityManager em = getEntityManager();
        CityInfo cityInfo = getCityInfoByZip(zipCode);
        
        Person person = null;

        try {
            em.getTransaction().begin();
            person = em.find(Person.class, id);
            
            for (Phone phone : phones) {
                phone.setInfoEntity(person);
            }
            
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setHobbies(hobbies);
            person.setEmail(email);
            person.setPhones(phones);
            person.setAddress(new Address(street, additionalAddressInfo, cityInfo));
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
