package storeapp.domain;

import java.util.List;
import java.util.Scanner;

public class Person {

    Scanner sc = new Scanner(System.in);

    // Atributos de la clase Person

    protected int id;
    protected String name;
    protected String lastName;
    protected String email;
    protected String password;
    protected boolean status;


    //Constructores de la clase Person

    public Person(int id, String name, String lastName, String email, String password, boolean status) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public Person() {


    }

    public Person(String email){
        this.email = email;
    }

    public Person(boolean status){
        this.status = status;
    }

    // Getter y Setters de la clase Person

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    //Metodos propios

    public <T> void createUser( T person){

    }

    public Person createPerson(Person person){
        return person;
    }

    public Person updatePerson(Person person){
        return person;
    }

    public List<Person> getAllPersons(){
        return null;
    }

    public Person getPersonById(int id){
        return null;
    }

    public void deletePerson(int id){

    }



}
