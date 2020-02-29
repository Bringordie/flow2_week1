package dto;

import entities.Address;
import entities.Person;

public class PersonDTO {

    private transient long id;
    private String fName;
    private String lName;
    private String phone;
    private Address address;

    public PersonDTO(Person p) {
        this.fName = p.getFirstName();
        this.lName = p.getLastName();
        this.phone = p.getPhone();
        this.id = p.getId();
        this.address = p.getAddress();
        
    }

//    public PersonDTO(String fn, String ln, String phone) {
//        this.fName = fn;
//        this.lName = ln;
//        this.phone = phone;
//    }

    public PersonDTO() {

    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PersonDTO{" + "id=" + id + ", fName=" + fName + ", lName=" + lName + ", phone=" + phone + '}';
    }

}
