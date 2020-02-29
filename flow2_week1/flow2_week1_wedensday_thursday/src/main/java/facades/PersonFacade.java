package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Person;
import entities.Address;
import exceptions.PersonNotFoundException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements PersonInterface {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public PersonDTO addPerson(String fName, String lName, String phone) {
        EntityManager em = emf.createEntityManager();
        Person p1 = new Person(fName, lName, phone);
        try {
            em.getTransaction().begin();
            em.persist(p1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(p1);
    }
    
//    public PersonDTO addPersonAndAddress(String fName, String lName, String phone) {
//        EntityManager em = emf.createEntityManager();
//        Person p1 = new Person(fName, lName, phone);
//        try {
//            em.getTransaction().begin();
//            em.persist(p1);
//            p1.setAddress(new Address("Street", 2800, "zip"));
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//        return new PersonDTO(p1);
//    }
    
    public PersonDTO deletePerson(int id) {
        EntityManager em = emf.createEntityManager();
        Person returnval = new Person();
        try {
            em.getTransaction().begin();
            returnval = em.find(Person.class, Long.valueOf(id));
            if (returnval == null) {
                return new PersonDTO(returnval);
            } else {
                em.remove(returnval);
                //Query query = em.createQuery("DELETE FROM Person p WHERE p.id = :id");
                //query.setParameter("id", returnval.getId());
                //query.executeUpdate();
                em.getTransaction().commit();
                em.close();
                return new PersonDTO(returnval);
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public PersonDTO getPerson(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            Person returnval = em.find(Person.class, Long.valueOf(id));
            return new PersonDTO(returnval);
        } catch (Exception e) {
            return null;
        }
        
    }

    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = emf.createEntityManager();
        TypedQuery query = em.createQuery("Select p from Person p", PersonsDTO.class);
        PersonsDTO allPersons = new PersonsDTO(query.getResultList());
        return allPersons;
        //return query.getResultList();
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person find = em.find(Person.class, p.getId());
        find.setFirstName(p.getfName());
        find.setLastName(p.getlName());
        find.setPhone(p.getPhone());
        find.setLastEdited(new Date());
        em.getTransaction().commit();
        return p;
    }

    public static void main(String[] args) throws PersonNotFoundException {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        PersonFacade pf = new PersonFacade();
       // pf.addPerson("fName", "lName", "phone");
       //pf.addPersonAndAddress("fName", "lName", "phone");
        PersonDTO pdto = new PersonDTO();
        //System.out.println(pf.getPerson(2));
        //System.out.println(pf.getAllPersons().toString());
//        Person person = new Person();
//        pdto.setId(Long.valueOf("1"));
//        pdto.setfName("renamefirstname");
//        pdto.setlName("renamelastname");
//        pdto.setPhone("renamephone");
//        pf.editPerson(pdto);
        //pf.deletePerson(3);
    }

}
