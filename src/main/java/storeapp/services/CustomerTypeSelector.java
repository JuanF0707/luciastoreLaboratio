package storeapp.services;

import storeapp.domain.enums.CustomerTypeEnum;
import storeapp.utils.CustomerFormValidation;

public class CustomerTypeSelector {

    public static String selectTypeCustomer(){

        String value = "";

        System.out.println("Seleccione 1. Nuevo 2. Antiguo 3. En mora");

        int option = CustomerFormValidation.validateInt("Opcion");

        switch (option){
            case 1:
                value =CustomerTypeEnum.NEW_CUSTOMER.getDescription();
                break;
            case 2:
                value = CustomerTypeEnum.OLD_CUSTOMER.getDescription();
                break;
            case 3:
                value = CustomerTypeEnum.BLOCKED.getDescription();
                break;
            default:
                System.out.println("Seleccione una Opción valida");
        }

        return value;
    }

}
