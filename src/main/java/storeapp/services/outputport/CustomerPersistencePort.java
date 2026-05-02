package storeapp.services.outputport;

import storeapp.domain.Customer;

import java.util.List;

public interface CustomerPersistencePort {


    Customer saveCustomer(Customer customer);
    List<Customer> findAllCustomers();
    Customer findCustomerById(int id);
    Customer updateCustomer(int id);
    void deleteCustomer(int id);


}
