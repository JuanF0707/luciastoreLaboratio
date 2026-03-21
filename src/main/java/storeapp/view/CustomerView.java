package storeapp.view;

import storeapp.domain.Customer;
import storeapp.services.CustumerService;
import storeapp.services.CustumerServiceImpl;

public class CustomerView {

    private final CustumerService customerService;
    private final Customer customer;

    public CustomerView(CustumerService customerService, Customer customer){
        this.customerService = customerService;
        this.customer = customer;
    }

    public void createCustomer(){

        customerService.createCustomer(customer);


    }


}
