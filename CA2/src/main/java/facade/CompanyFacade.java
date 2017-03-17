package facade;

import entity.Company;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

public class CompanyFacade {

    private EntityManagerFactory emf;

    public CompanyFacade(String persistence) {
        emf = Persistence.createEntityManagerFactory(persistence);
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
        CompanyFacade cf = new CompanyFacade("PU");
//        System.out.println(cf.getCompanyById(2));
//        System.out.println(cf.getCompanyByCvr(45313913).toString());
//        System.out.println(cf.getCompanyByPhoneNumber("45942644").toString());
//        System.out.println(cf.getCompaniesWithEmployeesMoreThan(200).toString());
//        System.out.println(cf.deleteCompany(2));
    }

    public Company getCompanyById(int id) {
        EntityManager em = getEntityManager();
        return em.find(Company.class, id);
    }

    public Company getCompanyByCvr(int cvr) {
        EntityManager em = getEntityManager();
        TypedQuery<Company> query = em.createQuery("SELECT c FROM Company c WHERE c.cvr = :cvr", Company.class);
        query.setParameter("cvr", cvr);
        return query.getSingleResult();
    }

    public Company getCompanyByPhoneNumber(String phoneNumber) {
        EntityManager em = getEntityManager();
        TypedQuery<Company> query = em.createQuery("SELECT c FROM Company c JOIN c.phones p WHERE p.number = :phoneNumber", Company.class);
        query.setParameter("phoneNumber", phoneNumber);
        return query.getSingleResult();
    }

    public List<Company> getCompaniesWithEmployeesMoreThan(int employees) {
        EntityManager em = getEntityManager();
        TypedQuery<Company> query = em.createQuery("SELECT c FROM Company c WHERE c.numEmployees > :employees", Company.class);
        query.setParameter("employees", employees);
        return query.getResultList();
    }

    public Company createCompany(Company company) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return getCompanyById(company.getId());
    }

    public Company updateCompany(Company company) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(company);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return getCompanyById(company.getId());
    }

    public boolean deleteCompany(int id) {
        EntityManager em = getEntityManager();
        Company company = getCompanyById(id);
        try {
            em.getTransaction().begin();
            company = em.merge(company);
            em.remove(company);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }
}
