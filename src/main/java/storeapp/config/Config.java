package storeapp.config;

import storeapp.domain.Admin;
import storeapp.persistence.repository.CustomerRepository;
import storeapp.services.AdminServiceImpl;
import storeapp.services.input.CustumerService;
import storeapp.services.CustumerServiceImpl;
import storeapp.services.outputport.CustomerPersistencePort;
import storeapp.userinterface.MenuApp;
import storeapp.view.AdminView;
import storeapp.view.CustomerView;
import storeapp.view.ProductView;

public class Config {

    public static MenuApp createMenuApp() {

        // esto es un patron Simple Factory, se encarga de crear los objetos necesarios para la aplicacion, y devolver un objeto MenuApp con todos los objetos necesarios para la aplicacion,
        // esto es para evitar tener que crear los objetos en el main, y tener
        // todo el codigo de creacion de objetos en un solo lugar, y asi poder cambiar la implementacion de los objetos sin tener que cambiar el codigo del main,
        //  por ejemplo si queremos cambiar la implementacion de CustomerServiceImpl por otra implementacion, solo tenemos que cambiar el codigo de esta clase,
        //  y no tenemos que cambiar el codigo del main, esto es una buena practica de programacion, ya que nos permite tener un codigo mas limpio y mantenible.

        Admin admin = new Admin();
        CustomerPersistencePort customerRepository = new CustomerRepository();
        CustumerService customerService = new CustumerServiceImpl(customerRepository);
        CustomerView customerView = new CustomerView(customerService);
        AdminServiceImpl adminService = new AdminServiceImpl(admin, customerRepository);
        AdminView adminView = new AdminView(adminService, admin);


        return new MenuApp(customerView, adminView);

    }









}
