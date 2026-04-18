package storeapp.utils;

import storeapp.domain.Category;
import storeapp.domain.Customer;
import storeapp.domain.Product;
import storeapp.exceptions.DuplicateEmailException;
import storeapp.exceptions.DuplicateIdException;

import java.util.List;

/**
 * Clase utilitaria para verificar duplicados en las listas de entidades
 * Proporciona métodos estáticos para detectar IDs y emails duplicados
 */
public class DuplicateChecker {

    /**
     * Verifica si un cliente con el mismo ID ya existe en la lista
     * @param customers Lista de clientes
     * @param id ID a verificar
     * @throws DuplicateIdException si el ID ya existe
     */
    public static void checkCustomerDuplicateById(List<Customer> customers, int id) {
        if (customers.stream().anyMatch(c -> c.getId() == id)) {
            throw new DuplicateIdException("Ya existe un cliente registrado con el ID: " + id);
        }
    }

    /**
     * Verifica si un cliente con el mismo email ya existe en la lista
     * @param customers Lista de clientes
     * @param email Email a verificar
     * @throws DuplicateEmailException si el email ya existe
     */
    public static void checkCustomerDuplicateByEmail(List<Customer> customers, String email) {
        if (customers.stream().anyMatch(c -> c.getEmail().equalsIgnoreCase(email))) {
            throw new DuplicateEmailException("Ya existe un cliente registrado con el email: " + email);
        }
    }

    /**
     * Verifica si una categoría con el mismo ID ya existe en la lista
     * @param categories Lista de categorías
     * @param id ID a verificar
     * @throws DuplicateIdException si el ID ya existe
     */
    public static void checkCategoryDuplicateById(List<Category> categories, int id) {
        if (categories.stream().anyMatch(c -> c.getIdCategory() == id)) {
            throw new DuplicateIdException("Ya existe una categoría registrada con el ID: " + id);
        }
    }

    /**
     * Verifica si un producto con el mismo ID ya existe en la lista
     * @param products Lista de productos
     * @param id ID a verificar
     * @throws DuplicateIdException si el ID ya existe
     */
    public static void checkProductDuplicateById(List<Product> products, int id) {
        if (products.stream().anyMatch(p -> p.getIdProduct() == id)) {
            throw new DuplicateIdException("Ya existe un producto registrado con el ID: " + id);
        }
    }

    /**
     * Verifica si existe un cliente con el ID especificado
     * @param customers Lista de clientes
     * @param id ID a buscar
     * @return true si existe, false en caso contrario
     */
    public static boolean customerExists(List<Customer> customers, int id) {
        return customers.stream().anyMatch(c -> c.getId() == id);
    }

    /**
     * Verifica si existe un cliente con el email especificado
     * @param customers Lista de clientes
     * @param email Email a buscar
     * @return true si existe, false en caso contrario
     */
    public static boolean customerEmailExists(List<Customer> customers, String email) {
        return customers.stream().anyMatch(c -> c.getEmail().equalsIgnoreCase(email));
    }

    /**
     * Verifica si existe una categoría con el ID especificado
     * @param categories Lista de categorías
     * @param id ID a buscar
     * @return true si existe, false en caso contrario
     */
    public static boolean categoryExists(List<Category> categories, int id) {
        return categories.stream().anyMatch(c -> c.getIdCategory() == id);
    }

    /**
     * Verifica si existe un producto con el ID especificado
     * @param products Lista de productos
     * @param id ID a buscar
     * @return true si existe, false en caso contrario
     */
    public static boolean productExists(List<Product> products, int id) {
        return products.stream().anyMatch(p -> p.getIdProduct() == id);
    }
}

