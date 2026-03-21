package storeapp;

import storeapp.config.Config;
import storeapp.domain.Admin;
import storeapp.domain.Customer;
import storeapp.repository.CustomerRepository;
import storeapp.services.AdminServiceImpl;
import storeapp.services.CustumerService;
import storeapp.services.CustumerServiceImpl;
import storeapp.userinterface.MenuApp;
import storeapp.view.AdminView;
import storeapp.view.CustomerView;

public class Main {

    public static void main(String[] args) {


        MenuApp menuApp = Config.createMenuApp();

            menuApp.showMainMenu();
    }
}
