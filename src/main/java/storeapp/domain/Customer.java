package storeapp.domain;

import java.util.List;

public class Customer extends Person{

    private double quote;
    private String customerType;

    public Customer(int id, String name, String lastName, String email, String password, boolean status, double quote, String customerType) {
        super(id, name, lastName, email, password, status);
        this.quote = quote;
        this.customerType = customerType;
    }

    public Customer(){
        super();
    }

    public Customer(double quote, String customerType) {
        this.quote = quote;
        this.customerType = customerType;
    }

    // Get Set

    public double getQuote() {
        return quote;
    }

    public void setQuote(double quote) {
        this.quote = quote;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }


    // Métodos Propios

    public Customer createUser( Customer customer) {


        System.out.println("Ingrese el id del cliente");
        int id = sc.nextInt();
        sc.nextLine();
        customer.id = id;

        System.out.println("Ingrese el nombre del cliente");
        String name = sc.nextLine();
        customer.name = name;

        System.out.println("INgrese el apellido");
        String lastName = sc.nextLine();
        customer.lastName = lastName;

        System.out.println("ingrese el email");
        String email = sc.nextLine();
        customer.email = email;

        System.out.println("Ingrese el password ");
        String password = sc.nextLine();
        customer.password = password;

        System.out.println("Estado Cliente ");
        boolean state = sc.nextBoolean();
        customer.status = state;

        System.out.println("Cupo");
        double quote = sc.nextDouble();
        customer.quote = quote;

        System.out.println("Tipo de Cliente");
        String customerType = sc.nextLine();
        customer.customerType = customerType;

        return customer;
    }

    @Override
    public Person updatePerson(Person person) {
        return super.updatePerson(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return super.getAllPersons();
    }



    public void  getPersonById(int id,Customer customer) {
        if(this.id == id){
            System.out.println("Id:" + customer.id + "\n" +
                    "name: " + customer.name + "\n");

        }
    }

    @Override
    public void deletePerson(int id) {
        super.deletePerson(id);
    }
}
