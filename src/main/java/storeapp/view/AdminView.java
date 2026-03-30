package storeapp.view;

import storeapp.domain.Admin;
import storeapp.services.AdminServiceImpl;

import java.util.Scanner;

public class AdminView {

    Scanner sc = new Scanner(System.in);
    private final AdminServiceImpl adminService;
    private final Admin admin;


    public AdminView(AdminServiceImpl adminService , Admin admin){
        this.adminService = adminService;
        this.admin = admin;
    }

    public void createAdmin(){
        adminService.createAdmin(admin);
    }


    public void getAllCustomers(){
        adminService.getAllCustomers().forEach(System.out::println);
    }

    public  void deleteCustomer(){
        System.out.println("Ingrese el ID del cliente a eliminar");
        int id = sc.nextInt();
        sc.nextLine();
        adminService.deleteCustomer(id);
        System.out.println("Cliente eliminado");
    }

}
