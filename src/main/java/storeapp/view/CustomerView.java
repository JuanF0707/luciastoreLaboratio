package storeapp.view;

import storeapp.domain.Customer;
import storeapp.services.CustumerServiceImpl;

public class CustomerView {

    private final CustumerServiceImpl customerService;
    private final Customer customer;

    public CustomerView(CustumerServiceImpl customerService, Customer customer){
        this.customerService = customerService;
        this.customer = customer;
    }

    public void createCustomer(){

        customerService.createCustomer(customer);


    }


}
