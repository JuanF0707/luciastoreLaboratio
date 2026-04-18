package storeapp.services;

import storeapp.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustumerService {

    // Estos metodos abstratos se configuran en el contrato
    public Customer createCustomer();
    public Customer getCustomerById(int id);
    public Optional<Customer> getCustomerByEmail(String email);
    public Customer updateCustomer(int id);
    public void deleteCustomer(int id);




}
