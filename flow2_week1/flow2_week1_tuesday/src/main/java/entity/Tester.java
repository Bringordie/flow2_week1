/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author frederikbraagaard
 */
public class Tester {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    public static void main(String[] args) {
        
        EntityManager em = emf.createEntityManager();
        
        
        Customer c = new Customer();
        em.getTransaction().begin();
//        Customer c1 = new Customer("Frederik", "Braagaard");
//        c1.addHobby("Tennis");
//        Customer c2 = new Customer("Jørgen", "Mikkelsen");
//        c2.addHobby("Fodbold");
//        Address a1 = new Address("Street 1", "City 1");
//        Address a2 = new Address("Street 2", "City 2");
//        em.persist(a1);
//        em.persist(a2);
        em.find(Customer.class, 1).toString();
        
        em.getTransaction().commit();

            Persistence.generateSchema("pu", null);
        
    }
    
}
