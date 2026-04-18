# 🚀 INSTRUCCIONES DE USO - SISTEMA DE VALIDACIONES

## 📌 TABLA DE CONTENIDOS

1. [Instalación y Configuración](#instalación-y-configuración)
2. [Cómo Usar las Validaciones](#cómo-usar-las-validaciones)
3. [Ejemplos Prácticos](#ejemplos-prácticos)
4. [Manejo de Excepciones](#manejo-de-excepciones)
5. [Pruebas](#pruebas)
6. [Solución de Problemas](#solución-de-problemas)

---

## 🔧 Instalación y Configuración

### Requisitos Previos
- Java 21 o superior
- Maven 3.6 o superior (opcional, si quieres usar Maven)
- Un IDE como IntelliJ IDEA o Eclipse

### Pasos de Instalación

1. **Verificar que los archivos estén en su lugar**
   ```
   ✓ storeapp/exceptions/DuplicateIdException.java
   ✓ storeapp/exceptions/DuplicateEmailException.java
   ✓ storeapp/exceptions/ValidationException.java
   ✓ storeapp/utils/EntityValidator.java
   ✓ storeapp/utils/DuplicateChecker.java
   ```

2. **Compilar el proyecto** (si usas Maven)
   ```bash
   mvn clean compile
   ```

3. **Si no usas Maven**, asegúrate de que todos los `.java` estén en el classpath

### Verificar Instalación
- Si el proyecto compila sin errores ✅ → Instalación correcta
- Si hay errores de importación ❌ → Verifica que los paths sean correctos

---

## 💻 Cómo Usar las Validaciones

### Opción 1: Mediante los Servicios (Automático)

Los servicios ya tienen las validaciones integradas:

```java
// En tu Main o MenuApp
CustumerServiceImpl custumerService = new CustumerServiceImpl(new Customer(), customerRepository);

try {
    Customer customer = custumerService.createCustomer(new Customer());
    // Si llegó aquí, el cliente es válido ✅
} catch (DuplicateIdException e) {
    System.out.println("ID duplicado: " + e.getMessage());
} catch (DuplicateEmailException e) {
    System.out.println("Email duplicado: " + e.getMessage());
} catch (ValidationException e) {
    System.out.println("Validación fallida: " + e.getMessage());
}
```

### Opción 2: Validar Manualmente (Control Total)

Puedes usar los validadores directamente:

```java
import storeapp.utils.EntityValidator;
import storeapp.utils.DuplicateChecker;

// Validar un email
try {
    EntityValidator.validateEmailFormat("usuario@ejemplo.com");
    System.out.println("Email válido ✓");
} catch (ValidationException e) {
    System.out.println("Email inválido: " + e.getMessage());
}

// Verificar que no exista cliente con ese ID
try {
    List<Customer> customers = customerRepository.findAllCustomers();
    DuplicateChecker.checkCustomerDuplicateById(customers, 5);
    System.out.println("ID disponible ✓");
} catch (DuplicateIdException e) {
    System.out.println("ID duplicado: " + e.getMessage());
}
```

---

## 📋 Ejemplos Prácticos

### Ejemplo 1: Crear un Cliente Nuevo

```java
public void crearClienteNuevo() {
    CustumerServiceImpl custumerService = new CustumerServiceImpl(
        new Customer(), 
        customerRepository
    );
    
    try {
        // El servicio pedirá los datos y validará automáticamente
        Customer nuevoCliente = custumerService.createCustomer(new Customer());
        
        System.out.println("✓ Cliente creado: " + nuevoCliente.getName());
        System.out.println("✓ Email: " + nuevoCliente.getEmail());
        System.out.println("✓ ID: " + nuevoCliente.getId());
        
    } catch (DuplicateIdException e) {
        System.out.println("❌ El ID ya existe. " + e.getMessage());
        // Opción: Reintentar con otro ID
        crearClienteNuevo();
        
    } catch (DuplicateEmailException e) {
        System.out.println("❌ El email ya está registrado. " + e.getMessage());
        // Opción: Reintentar con otro email
        crearClienteNuevo();
        
    } catch (ValidationException e) {
        System.out.println("❌ Error de validación: " + e.getMessage());
        // Opción: Mostrar ayuda al usuario
        mostrarAyudaValidacion();
    }
}
```

### Ejemplo 2: Validar Email Antes de Crear Cliente

```java
public boolean verificarEmailDisponible(String email) {
    try {
        // Validar formato
        EntityValidator.validateEmailFormat(email);
        
        // Verificar que no esté duplicado
        List<Customer> customers = customerRepository.findAllCustomers();
        DuplicateChecker.checkCustomerDuplicateByEmail(customers, email);
        
        return true; // Email válido y disponible
        
    } catch (ValidationException e) {
        System.out.println("Email inválido: " + e.getMessage());
        return false;
    } catch (DuplicateEmailException e) {
        System.out.println("Email duplicado: " + e.getMessage());
        return false;
    }
}

// Uso:
if (verificarEmailDisponible("nuevo@ejemplo.com")) {
    System.out.println("✓ Email disponible");
} else {
    System.out.println("❌ Email no disponible");
}
```

### Ejemplo 3: Validar Precio de Producto

```java
public void crearProductoConPrecio(double precio) {
    try {
        // Validar que el precio sea válido
        EntityValidator.validatePositivePrice(precio);
        
        System.out.println("✓ Precio válido: $" + precio);
        // Continuar con creación del producto
        
    } catch (ValidationException e) {
        System.out.println("❌ Precio inválido: " + e.getMessage());
        System.out.println("   El precio debe ser mayor a $0");
    }
}

// Uso:
crearProductoConPrecio(100.50);  // ✓ Válido
crearProductoConPrecio(-50);     // ❌ Error
crearProductoConPrecio(0);       // ❌ Error
```

### Ejemplo 4: Buscar Cliente por Email

```java
public void buscarClientePorEmail(String email) {
    try {
        // Validar formato del email
        EntityValidator.validateEmailFormat(email);
        
        // Buscar el cliente
        Optional<Customer> cliente = custumerService.getCustomerByEmail(email);
        
        if (cliente.isPresent()) {
            System.out.println("✓ Cliente encontrado:");
            System.out.println("  Nombre: " + cliente.get().getName());
            System.out.println("  Email: " + cliente.get().getEmail());
            System.out.println("  ID: " + cliente.get().getId());
        } else {
            System.out.println("⚠️  No se encontró cliente con ese email");
        }
        
    } catch (ValidationException e) {
        System.out.println("❌ Email inválido: " + e.getMessage());
    }
}

// Uso:
buscarClientePorEmail("juan@ejemplo.com");
buscarClientePorEmail("emailsindominio");  // ❌ Será rechazado
```

---

## ⚠️ Manejo de Excepciones

### Los 3 Tipos de Excepciones

#### 1. DuplicateIdException
```java
try {
    // Intenta crear algo con un ID que ya existe
    DuplicateChecker.checkCustomerDuplicateById(customers, 5);
} catch (DuplicateIdException e) {
    // Acciones posibles:
    System.out.println("Error: " + e.getMessage());
    // - Mostrar formulario para ingresar otro ID
    // - Sugerir un ID disponible
    // - Permitir actualizar el existente
}
```

#### 2. DuplicateEmailException
```java
try {
    // Intenta crear un cliente con un email que ya existe
    DuplicateChecker.checkCustomerDuplicateByEmail(customers, email);
} catch (DuplicateEmailException e) {
    // Acciones posibles:
    System.out.println("Error: " + e.getMessage());
    // - Sugerir recuperar contraseña
    // - Mostrar formulario para ingresar otro email
    // - Ofrecer vincular cuentas
}
```

#### 3. ValidationException
```java
try {
    // Intenta validar datos que no cumplen reglas
    EntityValidator.validatePositivePrice(-100);
} catch (ValidationException e) {
    // Acciones posibles:
    System.out.println("Error: " + e.getMessage());
    // - Mostrar reglas de validación
    // - Permitir reintentar
    // - Guiar al usuario
}
```

### Patrón Recomendado

```java
try {
    // Tu código aquí
    customer = custumerServiceImpl.createCustomer(new Customer());
    System.out.println("✓ Operación exitosa");
    
} catch (DuplicateIdException e) {
    logger.error("ID duplicado", e);
    mensajeUsuario("El ID ya existe. Intente con otro.");
    
} catch (DuplicateEmailException e) {
    logger.error("Email duplicado", e);
    mensajeUsuario("El email ya está registrado. Intente recuperar su contraseña.");
    
} catch (ValidationException e) {
    logger.warn("Validación fallida", e);
    mensajeUsuario("Error: " + e.getMessage());
    
} catch (Exception e) {
    logger.error("Error inesperado", e);
    mensajeUsuario("Error inesperado. Intente más tarde.");
}
```

---

## 🧪 Pruebas

### Test 1: Crear Cliente Duplicado

**Pasos:**
1. Ejecuta la aplicación
2. Crea un cliente con ID=1
3. Intenta crear otro cliente con ID=1
4. Verás el error

**Resultado esperado:**
```
✗ Error: Ya existe un cliente registrado con el ID: 1
```

### Test 2: Email Inválido

**Pasos:**
1. Intenta crear un cliente
2. Ingresa email: "correosindominio"
3. Presiona Enter

**Resultado esperado:**
```
✗ Error de validación: El email ingresado no tiene un formato válido
```

### Test 3: Ejecutar Demostración

```bash
# Ejecuta la clase de prueba
java storeapp.utils.ValidationTestDemo

# Verás todos los validadores en acción
```

### Test 4: Validar Manualmente

```java
// En una clase de prueba:
public void testValidaciones() {
    // Test email válido
    assertTrue(EntityValidator.isValidEmail("test@ejemplo.com"));
    assertFalse(EntityValidator.isValidEmail("testsindominio"));
    
    // Test precio
    assertTrue(EntityValidator.isValidPrice(100));
    assertFalse(EntityValidator.isValidPrice(-50));
    
    // Test stock
    assertTrue(EntityValidator.isValidStock(0));
    assertTrue(EntityValidator.isValidStock(10));
    assertFalse(EntityValidator.isValidStock(-5));
    
    System.out.println("✓ Todas las pruebas pasaron");
}
```

---

## 🔍 Solución de Problemas

### Problema 1: "No se encuentra clase EntityValidator"
**Solución:**
- Verificar que el archivo esté en: `src/main/java/storeapp/utils/EntityValidator.java`
- Asegurarse de que el package es: `package storeapp.utils;`
- Hacer clean y rebuild del proyecto

### Problema 2: DuplicateIdException no se captura
**Solución:**
```java
// ❌ INCORRECTO
catch (Exception e) {
    // No captura específicamente DuplicateIdException
}

// ✅ CORRECTO - Importar la excepción
import storeapp.exceptions.DuplicateIdException;

try {
    // código
} catch (DuplicateIdException e) {
    // Ahora sí se captura
}
```

### Problema 3: Los validadores no funcionan
**Solución:**
- Verificar que todas las clases estén compiladas
- Asegurarse de que los imports están correctos
- Verificar que no hay conflictos de nombres

### Problema 4: El email no se valida correctamente
**Solución:**
```java
// El regex espera formato: usuario@dominio.com
// ✅ Válidos:
EntityValidator.isValidEmail("juan@ejemplo.com")    // true
EntityValidator.isValidEmail("user.name@test.co.uk") // true

// ❌ Inválidos:
EntityValidator.isValidEmail("juansindominio")      // false
EntityValidator.isValidEmail("@ejemplo.com")        // false
EntityValidator.isValidEmail("juan@")               // false
```

### Problema 5: "ArrayIndexOutOfBoundsException" al leer datos
**Solución:**
- Asegurarse de que los datos de entrada son correctos
- Verificar que el número ingresado es un entero válido
- Limpiar el buffer de entrada con `sc.nextLine()`

---

## 📚 Referencia Rápida de Métodos

### EntityValidator.java

```java
// Métodos que retornan boolean
EntityValidator.isValidEmail(email)          // Email formato válido
EntityValidator.isNotEmpty(value)            // No está vacío
EntityValidator.isValidPrice(price)          // Precio > 0
EntityValidator.isValidStock(stock)          // Stock >= 0
EntityValidator.isValidId(id)                // ID > 0
EntityValidator.isValidName(name)            // Nombre >= 2 caracteres
EntityValidator.isValidPassword(password)    // Contraseña >= 6 caracteres
EntityValidator.isValidPhone(phone)          // Teléfono 10 dígitos

// Métodos que lanzan excepción si falla
EntityValidator.validateNotEmpty()           // Lanza si está vacío
EntityValidator.validatePositiveId()         // Lanza si ID <= 0
EntityValidator.validateEmailFormat()        // Lanza si email inválido
EntityValidator.validatePositivePrice()      // Lanza si precio <= 0
EntityValidator.validateNonNegativeStock()  // Lanza si stock < 0
```

### DuplicateChecker.java

```java
// Métodos que lanzan excepción si hay duplicado
DuplicateChecker.checkCustomerDuplicateById()      // DuplicateIdException
DuplicateChecker.checkCustomerDuplicateByEmail()   // DuplicateEmailException
DuplicateChecker.checkCategoryDuplicateById()      // DuplicateIdException
DuplicateChecker.checkProductDuplicateById()       // DuplicateIdException

// Métodos que retornan boolean
DuplicateChecker.customerExists()           // ¿Existe cliente?
DuplicateChecker.customerEmailExists()      // ¿Existe email?
DuplicateChecker.categoryExists()           // ¿Existe categoría?
DuplicateChecker.productExists()            // ¿Existe producto?
```

---

## ✅ Checklist de Implementación

- [ ] ¿Compiló sin errores?
- [ ] ¿Las excepciones se importan correctamente?
- [ ] ¿Puedes crear un cliente sin duplicados?
- [ ] ¿Se rechaza email inválido?
- [ ] ¿Se rechaza email duplicado?
- [ ] ¿Se rechaza categoría duplicada?
- [ ] ¿Se rechaza producto duplicado?
- [ ] ¿Los mensajes de error son claros?
- [ ] ¿Funciona la búsqueda por email?
- [ ] ¿Se capturan las excepciones correctamente?

---

## 🎓 Conclusión

El sistema de validaciones está completamente funcional. Ahora puedes:
- ✅ Crear datos sin duplicados
- ✅ Validar formatos de entrada
- ✅ Manejar errores específicos
- ✅ Proporcionar mensajes claros al usuario
- ✅ Asegurar integridad de datos

**¡Tu aplicación LuciaStore está lista para producción!** 🚀

