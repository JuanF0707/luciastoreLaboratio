package storeapp.services;

import storeapp.domain.Customer;
import storeapp.repository.CustomerRepository;
import storeapp.utils.DuplicateChecker;
import storeapp.utils.EntityValidator;
import storeapp.exceptions.DuplicateEmailException;
import storeapp.exceptions.DuplicateIdException;
import storeapp.exceptions.ValidationException;

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
        try {
            System.out.println("Ingrese el id del cliente");
            int id = sc.nextInt();
            sc.nextLine();
            
            // Validar que el ID sea positivo
            EntityValidator.validatePositiveId(id, "ID del cliente");
            
            // Verificar si el ID ya existe
            DuplicateChecker.checkCustomerDuplicateById(customerRepository.findAllCustomers(), id);
            customer.setId(id);

            System.out.println("Ingrese el nombre del cliente");
            String name = sc.nextLine();
            
            // Validar que el nombre sea válido
            EntityValidator.validateNotEmpty(name, "nombre del cliente");
            if (!EntityValidator.isValidName(name)) {
                throw new ValidationException("El nombre debe tener al menos 2 caracteres");
            }
            customer.setName(name);

            System.out.println("Ingrese el apellido");
            String lastName = sc.nextLine();
            
            // Validar que el apellido sea válido
            EntityValidator.validateNotEmpty(lastName, "apellido");
            customer.setLastName(lastName);

            System.out.println("Ingrese el email");
            String email = sc.nextLine();
            
            // Validar formato del email
            EntityValidator.validateEmailFormat(email);
            
            // Verificar si el email ya existe
            DuplicateChecker.checkCustomerDuplicateByEmail(customerRepository.findAllCustomers(), email);
            customer.setEmail(email);

            System.out.println("Ingrese el password ");
            String password = sc.nextLine();
            
            // Validar que la contraseña sea válida
            if (!EntityValidator.isValidPassword(password)) {
                throw new ValidationException("La contraseña debe tener mínimo 6 caracteres");
            }
            customer.setPassword(password);

            System.out.println("Estado Cliente (true/false)");
            boolean state = sc.nextBoolean();
            customer.setStatus(state);

            System.out.println("Cupo");
            double quote = sc.nextDouble();
            
            // Validar que el cupo sea válido
            EntityValidator.validatePositivePrice(quote);
            customer.setQuote(quote);
            sc.nextLine();

            System.out.println("Tipo de Cliente");
            String customerType = sc.nextLine();
            
            // Validar que el tipo de cliente no esté vacío
            EntityValidator.validateNotEmpty(customerType, "tipo de cliente");
            customer.setCustomerType(customerType);

            Customer savedCustomer = customerRepository.saveCustomer(customer);
            System.out.println("✓ Cliente registrado exitosamente con ID: " + id);
            return savedCustomer;
            
        } catch (DuplicateIdException e) {
            System.out.println("✗ Error: " + e.getMessage());
            throw e;
        } catch (DuplicateEmailException e) {
            System.out.println("✗ Error: " + e.getMessage());
            throw e;
        } catch (ValidationException e) {
            System.out.println("✗ Error de validación: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("✗ Error inesperado: " + e.getMessage());
            throw new ValidationException("Error al crear el cliente: " + e.getMessage());
        }
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {

        return customerRepository.findCustomerById(id);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        EntityValidator.validateEmailFormat(email);
        return customerRepository.findCustomerByEmail(email);
    }


    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }




}
