package facade;

import entity.Company;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

public class CompanyFacade {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
        CompanyFacade cf = new CompanyFacade();
//        System.out.println(cf.getCompanyByCvr(768484).toString());
//        System.out.println(cf.getCompanyByPhoneNumber("78314171").toString());
//        System.out.println(cf.getCompaniesWithEmployees(154).toString());
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

    public Company getCompanyByPhoneNumber(String phoneNumber) {
        EntityManager em = getEntityManager();
        Company company = null;
        try {
            em.getTransaction().begin();
            TypedQuery<Company> query = em.createQuery("SELECT c FROM Company c JOIN c.phones p WHERE p.number = :phoneNumber", Company.class);
            query.setParameter("phoneNumber", phoneNumber);
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

    public List<Company> getCompaniesWithEmployees(int employees) {
        EntityManager em = getEntityManager();
        List<Company> companies = null;
        try {
            em.getTransaction().begin();
            TypedQuery<Company> query = em.createQuery("SELECT c FROM Company c WHERE c.numEmployees > :employees", Company.class);
            query.setParameter("employees", employees);
            companies = query.getResultList();
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return companies;
    }
}
