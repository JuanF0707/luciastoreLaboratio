package storeapp.view;

import storeapp.services.input.CustumerService;
import storeapp.utils.CustomerFormValidation;

public class CustomerView {

    private final CustumerService customerService;


    public CustomerView(CustumerService customerService){
        this.customerService = customerService;
    }

    public void createCustomer(){

        customerService.createCustomer();


    }


    public void getCustumerById(int id){

        customerService.getCustomerById(id);
    }


    public void updateCustumer(){
        System.out.println("Estoy en la Vista");
        customerService.updateCustomer(CustomerFormValidation.validateInt("Ingrese el ID"));

    }

    public void deleteCustomer(){
        customerService.deleteCustomer(CustomerFormValidation.validateInt("Ingrese el id del CLiente a eliminar"));
    }


}
