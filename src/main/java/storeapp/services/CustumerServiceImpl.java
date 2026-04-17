package storeapp.services;

import storeapp.domain.Customer;
import storeapp.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CustumerServiceImpl implements CustumerService {

    Scanner sc = new Scanner(System.in);

    //Ahora vamos a comunicar las clases , para eso vamos a crear una instancia de la capa inmediatamente anterior
    private final CustomerRepository customerRepository;

    public CustumerServiceImpl(Customer customer, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    @Override
    public Customer createCustomer(Customer customer) {
        System.out.println("Ingrese el id del cliente");
        int id = sc.nextInt();
        sc.nextLine();
        customer.setId(id);

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
        boolean state = sc.nextBoolean();
        customer.setStatus(state);

        System.out.println("Cupo");
        double quote = sc.nextDouble();
        customer.setQuote(quote);
        sc.nextLine();

        System.out.println("Tipo de Cliente");
        String customerType = sc.nextLine();
        customer.setCustomerType(customerType);


        return customerRepository.saveCustomer(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {

        return customerRepository.findCustomerById(id);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return Optional.empty();
    }


    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }




}
