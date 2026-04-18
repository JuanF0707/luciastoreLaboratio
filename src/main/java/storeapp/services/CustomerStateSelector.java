package storeapp.services;

import storeapp.domain.enums.CustomerState;
import storeapp.utils.CustomerFormValidation;

public class CustomerStateSelector {

    public static boolean selectCustomerState(){


        boolean value= false;

        System.out.println("Seleccione 1. Activo 2. Inactivo");

        int option = CustomerFormValidation.validateInt("Opcion");

        switch (option){
            case 1:
                value = CustomerState.ACTIVE.getDescription();
                break;
            case 2:
                value = CustomerState.INACTIVE.getDescription();
                break;
            default:
                System.out.println("Seleccione una opcion valida");
        }

        return value;

    }



}
