package storeapp.utils;

import storeapp.domain.Customer;

import java.util.Scanner;

public class CustomerFormValidation {

    static Scanner sc = new Scanner(System.in);

    public static void validateId(Customer customer) {
        boolean validInput = false;
        while(!validInput) {
            try{

                System.out.println("Ingrese el id del cliente");
                int id = sc.nextInt();
                sc.nextLine();
                validInput = true;
                customer.setId(id);
                continue;
            } catch (Exception e){
                System.out.println("Error al ingresar el id del cliente, por favor ingrese un numero entero");
                validInput = false;
                sc.nextLine();
            }}
    }


    public static int validateInt(String prompt) {

        while(true){
            try{

                System.out.println(prompt);
                int value = sc.nextInt();
                sc.nextLine();
                return value;

            }catch (Exception e){
                System.out.println("Error al ingresar el valor, este debe ser un numero entero");
                sc.nextLine();
            }
        }
    }

    public static double validateDouble(String prompt) {

        while(true){
            try{

                System.out.println(prompt);
                double value = sc.nextDouble();
                sc.nextLine();
                return value;

            }catch (Exception e){
                System.out.println("Error al ingresar el valor, este debe ser un numero decimal");
                sc.nextLine();
            }
        }
    }

    public static boolean validateBoolean(String prompt) {

        while(true){
            try{

                System.out.println(prompt);
                boolean value = sc.nextBoolean();
                sc.nextLine();
                return value;

            }catch (Exception e){
                System.out.println("Error al ingresar el valor, este debe ser un booleano (true/false)");
                sc.nextLine();
            }
        }
    }

    public static String validateString(String prompt) {

        while(true){
            try{

                System.out.println(prompt);
                String value = sc.nextLine();
                sc.nextLine();
                return value;

            }catch (Exception e){
                System.out.println("Error al ingresar el valor, este debe ser carácteres");
                sc.nextLine();
            }
        }
    }



    public static boolean validateCustomerForm(String name, String email, String phone) {
        if (name == null || name.trim().isEmpty()) {
            return false; // Name is required
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return false; // Invalid email format
        }
        if (phone == null || !phone.matches("^\\d{10}$")) {
            return false; // Phone number must be 10 digits
        }
        return true; // All validations passed
    }




}
