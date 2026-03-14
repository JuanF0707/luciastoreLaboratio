package storeapp;

import storeapp.domain.Customer;
import storeapp.services.CustumerServiceImpl;
import storeapp.userinterface.MenuApp;
import storeapp.view.CustomerView;

public class Main {

    public static void main(String[] args) {

        Customer customer = new Customer();
        CustumerServiceImpl customerService = new CustumerServiceImpl(customer);
        CustomerView customerView = new CustomerView( customerService, customer);
        MenuApp menuApp = new MenuApp(customerView);

            menuApp.showMainMenu();
    }
}
