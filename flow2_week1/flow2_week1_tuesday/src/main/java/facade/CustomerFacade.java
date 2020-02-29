package facade;

import entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author frederikbraagaard
 */
public class CustomerFacade {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static CustomerFacade instance;
    
    public static CustomerFacade getCarsFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Customer getCustomer(int id) {
        EntityManager em = emf.createEntityManager();
        Customer c1 = em.find(Customer.class, id);
        return c1;
        
    }
    
    public List<Customer> getCustomers() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select c from Customer c");
        List<Customer> result = query.getResultList();
        return result;
    }
    
    public Customer addCustomer(Customer cust) {
        EntityManager em = emf.createEntityManager();
        Customer c1 = cust;
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
        } finally {
            em.close();
            return c1;
        }
    }
    
    public Customer deleteCustomer(int id) {
        EntityManager em = emf.createEntityManager();
        Query queryReturn = em.createQuery("Select c from Customer c where c.id = :id");
        TypedQuery<Customer> query = em.createQuery("DELETE FROM Customer c where c.id = :id", Customer.class);
        query.setParameter("id", id);
        queryReturn.setParameter("id", id);
        Customer returnvalue = query.getSingleResult();
        return returnvalue;
    }
    
//    public Customer editCustomer(Customer cust) {
//        EntityManager em = emf.createEntityManager();
//        Query query = em.createQuery("UPDATE Customer c where c.id = :id", Customer.class);
//        query.setParameter("id", cust.getId());
//    }
    
}
