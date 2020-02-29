package facade;

import entity.Customer;
import entity.ItemType;
import entity.Orders;
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
public class Facade {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static Facade instance;

    public static Facade getCarsFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Facade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Customer addCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        Customer c1 = customer;
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } finally {
            em.close();
            return c1;
        }
    }

    public Customer getCustomer(int id) {
        EntityManager em = emf.createEntityManager();
        Customer c1 = em.find(Customer.class, Long.valueOf(id));
        return c1;

    }

    public List<Customer> getCustomers() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select c from Customer c");
        List<Customer> result = query.getResultList();
        return result;
    }
    
    public void createItemType(ItemType it) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(it);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public ItemType fineItemType(int id) {
        EntityManager em = emf.createEntityManager();
        ItemType itemFind = em.find(ItemType.class, Long.valueOf(id));
        return itemFind;
    }
    
    public void createOrderAddCustomer(Orders order, int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(order);
            order.setId(Long.valueOf(id));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}