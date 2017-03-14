package model;

import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

/**
 *
 * @author Joacim
 */
public class DataGenerator {

    Random ran = new Random();
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public static void main(String[] args) {
        new DataGenerator().starter();

    }

    public void starter() {
//        for (int i = 0; i < 50; i++) {
//            createRandomAddress();
//        }
//        createHobbies();
//        
//        for (int i = 0; i < 50; i++) {
//        createPhoneNumbers();            
//        }

//        for (int i = 0; i < 50; i++) {
//            createRandomPerson();
//
//        }
//        for (int i = 1; i <= 50; i++) {
//            updatePhone(i);
//        }

    }

    public InfoEntity getInfoEntity(int id) {
        EntityManager em = getManager();
        InfoEntity infoEntity = null;
        try {
            em.getTransaction().begin();
            infoEntity = em.find(InfoEntity.class, id);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return infoEntity;
    }

    public void updatePhone(int id) {
        EntityManager em = getManager();
        try {
            em.getTransaction().begin();
            Phone p = em.find(Phone.class, id);
            p.setInfoEntity(getInfoEntity(id));
            em.merge(p);
            em.getTransaction().commit();
        } catch (RollbackException r) {

        } finally {
            em.close();
        }
    }

    public void createRandomPerson() {
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(getHobby(ran.nextInt(2) + 1));
        List<Phone> phones = new ArrayList();
        phones.add(getPhone(ran.nextInt(50) + 1));
        Person person = new Person("Asger", "Lundblad", hobbies, "asger@gmail.com", phones, getAddress(ran.nextInt(50) + 1));
        EntityManager em = getManager();
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
    }

    public Address getAddress(int id) {
        EntityManager em = getManager();
        Address address = null;
        try {
            em.getTransaction().begin();
            address = em.find(Address.class, id);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return address;
    }

    public Phone getPhone(int id) {
        EntityManager em = getManager();
        Phone phone = null;
        try {
            em.getTransaction().begin();
            phone = em.find(Phone.class, id);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return phone;
    }

    public Hobby getHobby(int id) {
        EntityManager em = getManager();
        Hobby hobby = null;
        try {
            em.getTransaction().begin();
            hobby = em.find(Hobby.class, id);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return hobby;
    }

    public void createPhoneNumbers() {
        int n = 100000 + ran.nextInt(90000000);
        Phone phone = new Phone(n + "", "desc");
        EntityManager em = getManager();
        try {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void createHobbies() {
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(new Hobby("football", "run around"));
        hobbies.add(new Hobby("fishing", "relax"));
        hobbies.add(new Hobby("sowing", "sow things"));
        EntityManager em = getManager();
        try {
            em.getTransaction().begin();
            em.persist(hobbies.get(0));
            em.persist(hobbies.get(1));
            em.persist(hobbies.get(2));
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public CityInfo getCityInfo(int id) {
        EntityManager em = getManager();
        CityInfo c = new CityInfo();
        try {
            em.getTransaction().begin();
            c = em.find(CityInfo.class, id);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return c;
    }

    public void createRandomAddress() {
        Address a = new Address("street" + ran.nextInt(250), "desc", getCityInfo(ran.nextInt(1200)));
        EntityManager em = getManager();
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    private EntityManager getManager() {
        return emf.createEntityManager();
    }
}
