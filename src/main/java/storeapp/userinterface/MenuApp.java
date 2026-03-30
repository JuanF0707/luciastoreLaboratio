package storeapp.userinterface;

import storeapp.domain.Customer;
import storeapp.services.CustumerServiceImpl;
import storeapp.view.AdminView;
import storeapp.view.CustomerView;

import java.util.Scanner;

public class MenuApp {


    Scanner sc = new Scanner(System.in);
    private final CustomerView customerView;
    private final AdminView adminView;

    public MenuApp(CustomerView customerView, AdminView adminView) {
        this.customerView = customerView;
        this.adminView = adminView;
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
                    System.out.println("1. Cliente 2. Administrador");
                    int userType = sc.nextInt();
                    sc.nextLine();
                    if (userType == 1){
                        customerView.createCustomer();
                    }else if(userType == 2){
                        adminView.createAdmin();
                    }else{
                        System.out.println("Opcion no valida, por favor seleccione una opcion valida");
                    }

                    break;
                case 2:
                    System.out.println("Iniciar Sesion");
                    System.out.println("1. Administrador 2. Cliente");
                    int loginType = sc.nextInt();
                    sc.nextLine();
                    if (loginType == 1){
                        profileSelector("admin");
                    } else if (loginType == 2) {
                        profileSelector("customer");
                    } else {
                        System.out.println("Opción no valida");
                    }
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


    public void profileSelector(String profile){

        if(profile.equals("admin")){
            showMenuAdmin();
        }else if(profile.equals("customer")){
            showMenuCustomer();
        }
    }


    public void showMenuAdmin(){

        while (true){
            System.out.println("Menu Administrador");
            System.out.println("1. Gestionar Productos 2. Gestionar Categorias 3. Gestionar Clientes 4. Salir");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1:
                    System.out.println("Gestionar Productos");
                    break;
                case 2:
                    System.out.println("Gestionar Categorias");
                    break;
                case 3:
                    System.out.println("Gestionar Clientes");
                    customerMenuAdmin();
                    break;
                case 4:
                    System.out.println("Saliendo del menu de administrador...");
                    return;
                default:
                    System.out.println("Opcion no valida, por favor seleccione una opcion valida");
            }
        }

    }


    public void showMenuCustomer(){

        System.out.println("Menu Cliente");
        while (true) {

            System.out.println("1. Crear mi perfil 2. Ver mi perfil por id 3. Modificar mi perfil 4. Salir");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Crear mi perfil");
                    customerView.createCustomer();
                    break;
                case 2:
                    System.out.println("Ver mi  perfil");
                    System.out.println("Ingrese su id para ver su perfil");
                    int id = sc.nextInt();
                    customerView.getCustumerById(id);
                    break;
                case 3:
                    System.out.println("Modificar mi perfil");
                    break;
                case 4:
                    System.out.println("Saliendo del Menu Cliente...");
                default:
                    System.out.println("Opcion no valida, por favor seleccione una opcion valida");
            }
        }

    }


    public void customerMenuAdmin(){

        System.out.println("Menu Cliente");
        while (true) {

            System.out.println("1. Crear Perfil Cliente 2. Ver perfil por id 3. Modificar perfil 4. Ver perfiles 5. Eliminar Perfil 6. Salir");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Crear perfil");
                    customerView.createCustomer();
                    break;
                case 2:
                    System.out.println("Ver perfil por id");
                    System.out.println("Buscar perfil");
                    System.out.println("Ingrese el id del  perfil a buscar");
                    int id = sc.nextInt();
                    customerView.getCustumerById(id);
                    break;
                case 3:
                    System.out.println("Modificar perfil");
                    break;
                case 4:
                    System.out.println("Ver perfiles");
                    adminView.getAllCustomers();
                    break;
                case 5:
                    System.out.println("Eliminar Perfil");
                    adminView.deleteCustomer();
                    break;
                case 6:
                    System.out.println("Saliendo del menu de Clientes...");
                default:
                    System.out.println("Opcion no valida, por favor seleccione una opcion valida");
            }
        }
    }
}
