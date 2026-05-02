package storeapp.services;

import storeapp.domain.Customer;
import storeapp.services.input.CustumerService;
import storeapp.services.outputport.CustomerPersistencePort;
import storeapp.utils.CustomerFormValidation;

import java.util.Optional;
import java.util.Scanner;

public class CustumerServiceImpl implements CustumerService {

    Scanner sc = new Scanner(System.in);

    //Ahora vamos a comunicar las clases , para eso vamos a crear una instancia de la capa inmediatamente anterior
    private final CustomerPersistencePort customerRepository;

    public CustumerServiceImpl( CustomerPersistencePort customerRepository) {
        this.customerRepository = customerRepository;

    }

    @Override
    public Customer createCustomer() {

        Customer customer = new Customer();

        String prompt = "Ingrese el id del cliente";
        customer.setId(CustomerFormValidation.validateInt(prompt));


        System.out.println("Ingrese el nombre del cliente");
        String name = sc.nextLine();
        customer.setName(name);

        System.out.println("INgrese el apellido");
        String lastName = sc.nextLine();
        customer.setLastName(lastName);

        System.out.println("ingrese el email");
        String email = sc.nextLine();
        customer.setEmail(email);

        System.out.println("Ingrese el password ");
        String password = sc.nextLine();
        customer.setPassword(password);

        System.out.println("Estado Cliente ");

        customer.setStatus(CustomerStateSelector.selectCustomerState());

        System.out.println("Cupo");
        double quote = sc.nextDouble();
        customer.setQuote(quote);
        sc.nextLine();

        System.out.println("Tipo de Cliente");
        customer.setCustomerType(CustomerTypeSelector.selectTypeCustomer());

        return customerRepository.saveCustomer(customer);
    }

    @Override
    public Customer getCustomerById(int id) {

        return customerRepository.findCustomerById(id);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return Optional.empty();
    }


    @Override
    public Customer updateCustomer(int id) {

        System.out.println("Estoy en el service");
        Customer customer = customerRepository.findCustomerById(id);

        if(customer.getId() == id){

            System.out.println("Actualizar 1. id 2. Nombre 3 Apellido 4.Correo 5. Contraseña");
            int option = CustomerFormValidation.validateInt("Opcion");

            switch (option){
                case 1:
                    customer.setId(CustomerFormValidation.validateInt("Actualizar id"));
                    break;
                case 2:
                    customer.setName(CustomerFormValidation.validateString("Actualzar nombre"));
                    break;
                case 3:
                    customer.setLastName(CustomerFormValidation.validateString("Actualizar Apellido"));
                    break;
                case 4:
                    customer.setEmail(CustomerFormValidation.validateString("Actualizar Email"));
                    break;
                case 5:
                    customer.setPassword(CustomerFormValidation.validateString("Actualizar contraseña"));
                default:
                    System.out.println("Seleccione una opcion");
                    break;
            }

            customerRepository.updateCustomer(id);
        }else{
            System.out.println("Cliente  no encontrado");
        }



        return customer;
    }

    @Override
    public void deleteCustomer(int id) {

        customerRepository.deleteCustomer(id);

    }
}
