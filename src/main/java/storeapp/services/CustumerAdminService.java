package storeapp.services;

import storeapp.domain.Customer;

import java.util.List;

public interface CustumerAdminService {

    public List<Customer> getAllCustomers();
    public void deleteCustomer(int id);
}
