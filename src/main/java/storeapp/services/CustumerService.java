package storeapp.services;

import storeapp.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustumerService {

    // Estos metodos abstratos se configuran en el contrato
    public Customer createCustomer(Customer customer);
    public Optional<Customer> getCustomerById(int id);
    public Optional<Customer> getCustomerByEmail(String email);
    public List<Customer> getAllCustomers();
    public Customer updateCustomer(Customer customer);
    public void deleteCustomer(int id);



}
