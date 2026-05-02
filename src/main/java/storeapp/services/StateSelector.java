package storeapp.services;

import storeapp.domain.enums.ProductState;

import java.util.Scanner;

public class StateSelector {
    Scanner  sc = new Scanner(System.in);
    public  boolean ProductState(){

        System.out.println("Selecione el estado del producto: ");
        System.out.println("1. Disponible 2. Sin Stock 3. Descontinuado");
        int option = sc.nextInt();
        if (option == 1){
            System.out.println("Estado:" + ProductState.AVAILABLE);
            return true;
        } else if (option == 2 || option == 3) {
            System.out.println("Estado:" + (option == 2 ? ProductState.OUT_OF_STOCK : ProductState.DISCONTINUED));
            return false;
        } else {
            System.out.println("Opción no válida, se asignará el estado por defecto: Disponible");
            return false;
        }


    }


}
