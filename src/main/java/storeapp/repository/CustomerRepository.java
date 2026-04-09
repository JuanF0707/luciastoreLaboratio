package storeapp.repository;

import storeapp.domain.Customer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(  1, "John", "Doe", "jd@mail.com" , "1234567890", true , 1000000.00 , "NUEVO" ),
            new Customer(  2, "Jane", "Smith","js@mail.com" , "1234567890", true , 2000000.00 , "ANTIGUO")
    ));

    public Customer saveCustomer(Customer customer){

        customers.add(customer);

        return customer;
    }


    public List<Customer> findAllCustomers(){

        for (Customer customer: customers) {
            System.out.println(customer.getId() + " " + customer.getName() + " " + customer.getLastName() + " " + customer.getEmail() + " " + customer.getPassword() + " " + customer.isStatus() + " " + customer.getQuote() + " " + customer.getCustomerType());
        }

        return customers;

    }


    public Optional<Customer> findCustomerById(int id){
        System.out.println("repositorio" + id);
        try{
        for(Customer customer: customers){
            if(customer.getId() == id){
                System.out.println(customer.getId() + " " + customer.getName() + " " + customer.getLastName() + " " + customer.getEmail() + " " + customer.getPassword() + " " + customer.isStatus() + " " + customer.getQuote() + " " + customer.getCustomerType());
            }

            return Optional.of(customer);
        }

        return Optional.ofNullable(null);
        }catch (Exception e){
            System.out.println("Customer not found");
            return Optional.ofNullable(null);
        }
    }

    public void findCustomerByEmail(){

    }

    public void updateCustomer(){

    }



    public void deleteCustomer(){

    }


}
