package storeapp.userinterface;

import storeapp.domain.Customer;
import storeapp.services.CustumerServiceImpl;
import storeapp.view.CustomerView;

import java.util.Scanner;

public class MenuApp {


    Scanner sc = new Scanner(System.in);
    private final CustomerView customerView;

    public MenuApp(CustomerView customerView) {
        this.customerView = customerView;
    }

    public void showMainMenu(){

        System.out.println("Bienvenido a la tienda online");
        System.out.println("Presione 1 para iniciar la aplicacion");

        int init = sc.nextInt();
        sc.nextLine();

        while(init != 0){

            System.out.println("Selecione 1. Registrar Usuario 2. Iniciar Sesion 3. Salir");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1:
                    System.out.println("Registrar Usuario");
                    customerView.createCustomer();
                    break;
                case 2:
                    System.out.println("Iniciar Sesion");
                    break;
                case 3:
                    System.out.println("Saliendo de la aplicacion");
                    init = 0;
                    break;
                default:
                    System.out.println("Opcion no valida, por favor seleccione una opcion valida");
            }
        }

    }

}
