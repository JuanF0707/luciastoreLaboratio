package storeapp.domain;

import java.util.List;

public class Admin extends Person{

    String rol;
    String permissions;
    //Constuctor

    public Admin(int id, String name, String lastName, String email, String password, boolean status, String rol, String permissions) {
        super(id, name, lastName, email, password, status);
        this.rol = rol;
        this.permissions = permissions;
    }

    public Admin() {
        super();
    }

    public Admin(String email) {
        super(email);
    }

    //Get and Set

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }


    //metodos Propios


    @Override
    public <T> void createUser(T person) {
        super.createUser(person);
    }

    @Override
    public Person updatePerson(Person person) {
        return super.updatePerson(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return super.getAllPersons();
    }


    @Override
    public Person getPersonById(int id) {
        return super.getPersonById(id);
    }


    @Override
    public void deletePerson(int id) {
        super.deletePerson(id);
    }
}
