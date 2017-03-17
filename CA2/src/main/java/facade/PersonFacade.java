package facade;

import entity.CityInfo;
import entity.Person;
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
        System.out.println(pf.getPersonById(1).toString());
//        System.out.println(pf.getPersonByPhoneNumber("17136178").toString());
//       System.out.println(pf.getPersons().toString());
//        System.out.println(pf.getPersonsByZip(2600).toString());
//        System.out.println(pf.getPersonsByHobby("fishing").toString());
//        System.out.println(pf.getCountOfPersonsWithHobby("fishing"));
//        System.out.println(pf.getCityInfoByZip(3390));
//        List<Hobby> hobbies = new ArrayList();
//        List<Phone> phones = new ArrayList();
//        CityInfo cityInfo = pf.getCityInfoByZip(2600);
//        Address address = new Address("Hejvej 12", "nothing", cityInfo);
//        Person person = pf.getPersonById(1);
//        person.setFirstName("zam");
//        hobbies.add(new Hobby("Football", "Score goals"));
//        phones.add(new Phone(person, "99999999", "no des"));
//        person.setId(35);
//        System.out.println(pf.createPerson("Peter", "Klausen", hobbies, "hej@hotmail.com", phones, 2500, "En vej 212", "nothing"));
//        System.out.println(pf.updatePerson(1, "Lars", "Tomsen", hobbies, "Mail@gmail.com", phones, 2635, "Gedemarksvej 60, 2. th", "Hello"));
//        System.out.println(pf.updatePerson(person));
//        pf.deletePerson(97);
    }

    public Person getPersonById(int id) {
        EntityManager em = getEntityManager();
        return em.find(Person.class, id);
    }

    public Person getPersonByPhoneNumber(String phoneNumber) {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.phones ph WHERE ph.number = :phoneNumber", Person.class);
        query.setParameter("phoneNumber", phoneNumber);
        return query.getSingleResult();
    }

    public List<Person> getPersons() {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    public List<Person> getPersonsByZip(int zipCode) {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.address.cityInfo.zip = :zipCode", Person.class);
        query.setParameter("zipCode", zipCode);
        return query.getResultList();
    }

    public List<Person> getPersonsByHobby(String hobby) {
        EntityManager em = getEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :hobby", Person.class);
        query.setParameter("hobby", hobby);
        return query.getResultList();
    }

    public int getCountOfPersonsWithHobby(String hobby) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT COUNT(p) FROM Person p JOIN p.hobbies h WHERE h.name = :hobby");
        query.setParameter("hobby", hobby);
        return Integer.parseInt(query.getSingleResult().toString());
    }

    public CityInfo getCityInfoByZip(int zipCode) {
        EntityManager em = getEntityManager();
        TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo c WHERE c.zip = :zipCode", CityInfo.class);
        query.setParameter("zipCode", zipCode);
        return query.getSingleResult();
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
        return getPersonById(person.getId());
    }

    public Person updatePerson(Person person) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            person = em.merge(person);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return person;
    }

    public boolean deletePerson(int id) {
        EntityManager em = getEntityManager();
        Person person = getPersonById(id);
        try {
            em.getTransaction().begin();
            person = em.merge(person);
            em.remove(person);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
}
