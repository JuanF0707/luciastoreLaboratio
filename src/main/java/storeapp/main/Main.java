package storeapp.main;

import storeapp.domain.Customer;
import storeapp.domain.Person;

public class Main {

    public static void main(String[] args) {

        String mail = "clara@mail.com";

        Customer customer = new Customer();

        customer.createUser(customer);
        customer.getPersonById(1,customer);





    }


}
