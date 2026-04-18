# 🛡️ VALIDACIONES LUCIASTORE - GUÍA RÁPIDA

## ¿QUÉ SE IMPLEMENTÓ?

Un **sistema completo de validaciones** que previene crear datos duplicados y asegura que todos los datos sean válidos.

---

## 📂 NUEVA ESTRUCTURA DE CARPETAS

```
luciastoreLaboratio/
├── src/main/java/storeapp/
│   ├── exceptions/                 ← NUEVA CARPETA
│   │   ├── DuplicateIdException.java
│   │   ├── DuplicateEmailException.java
│   │   └── ValidationException.java
│   │
│   ├── utils/                      ← MODIFICADA
│   │   ├── EntityValidator.java           ← NUEVO ✨
│   │   ├── DuplicateChecker.java          ← NUEVO ✨
│   │   ├── ExceptionHandlerExample.java   ← NUEVO ✨
│   │   ├── ValidationTestDemo.java        ← NUEVO ✨
│   │   └── CustomerFormValidation.java    (existente)
│   │
│   ├── services/                   ← MODIFICADA
│   │   ├── CustumerServiceImpl.java        ✏️ MEJORADO
│   │   ├── CategoryServicesImpl.java       ✏️ MEJORADO
│   │   └── ProductServiceImpl.java         ✏️ MEJORADO
│   │
│   ├── repository/                 ← MODIFICADA
│   │   └── CustomerRepository.java        ✏️ MEJORADO
│   │
│   └── ... (resto de carpetas sin cambios)
```

---

## 🎯 VALIDACIONES POR ENTIDAD

### 👤 CLIENTES (Customers)
```
✅ ID           → Debe ser positivo y ÚNICO
✅ Nombre       → No vacío, mínimo 2 caracteres
✅ Apellido     → No vacío
✅ Email        → Formato válido y ÚNICO (NUEVA)
✅ Contraseña   → Mínimo 6 caracteres
✅ Cupo         → Mayor a 0
✅ Tipo         → No vacío
```

### 📂 CATEGORÍAS (Categories)
```
✅ ID           → Debe ser positivo y ÚNICO (NUEVA)
✅ Descripción  → No vacía
✅ Estado       → true/false
```

### 📦 PRODUCTOS (Products)
```
✅ ID           → Debe ser positivo y ÚNICO (NUEVA)
✅ Descripción  → No vacía
✅ Precio       → Mayor a 0
✅ Stock        → Mayor o igual a 0
✅ Categoría    → Opcional
```

---

## 🔧 CÓMO USAR - EJEMPLOS RÁPIDOS

### Crear un Cliente CON Validaciones

```java
// ANTES (sin validaciones):
customer = custumerServiceImpl.createCustomer(new Customer());

// AHORA (con validaciones y excepciones):
try {
    customer = custumerServiceImpl.createCustomer(new Customer());
    System.out.println("✓ Cliente creado exitosamente");
} catch (DuplicateIdException e) {
    System.out.println("✗ Error: El ID ya existe");
} catch (DuplicateEmailException e) {
    System.out.println("✗ Error: El email ya está registrado");
} catch (ValidationException e) {
    System.out.println("✗ Error: " + e.getMessage());
}
```

### Validar Manualmente un Email

```java
import storeapp.utils.EntityValidator;

// Verificar si es válido (retorna true/false)
if (EntityValidator.isValidEmail("usuario@ejemplo.com")) {
    System.out.println("Email válido");
} else {
    System.out.println("Email inválido");
}

// O lanzar excepción si es inválido
try {
    EntityValidator.validateEmailFormat("usuariosindominio");
} catch (ValidationException e) {
    System.out.println("Email formato inválido: " + e.getMessage());
}
```

### Verificar Duplicados

```java
import storeapp.utils.DuplicateChecker;
import storeapp.repository.CustomerRepository;

List<Customer> customers = customerRepository.findAllCustomers();

// Método 1: Verificar si existe (retorna true/false)
if (DuplicateChecker.customerExists(customers, 5)) {
    System.out.println("Ya existe cliente con ID 5");
}

// Método 2: Lanzar excepción si existe
try {
    DuplicateChecker.checkCustomerDuplicateById(customers, 5);
    System.out.println("ID disponible");
} catch (DuplicateIdException e) {
    System.out.println("Error: " + e.getMessage());
}
```

---

## 📊 TABLA DE VALIDACIONES

| Entidad | Campo | Regla | Excepción | Mensaje |
|---------|-------|-------|-----------|---------|
| Cliente | ID | > 0 | ValidationException | "ID debe ser positivo" |
| Cliente | ID | ≠ existe | DuplicateIdException | "Ya existe cliente con ID: X" |
| Cliente | Email | valid | ValidationException | "Email no válido" |
| Cliente | Email | ≠ existe | DuplicateEmailException | "Ya existe cliente con email: X" |
| Cliente | Nombre | not empty | ValidationException | "Campo no puede estar vacío" |
| Cliente | Nombre | ≥ 2 chars | ValidationException | "Mínimo 2 caracteres" |
| Cliente | Pass | ≥ 6 chars | ValidationException | "Mínimo 6 caracteres" |
| Cliente | Cupo | > 0 | ValidationException | "Debe ser positivo" |
| Categoría | ID | > 0 | ValidationException | "ID debe ser positivo" |
| Categoría | ID | ≠ existe | DuplicateIdException | "Ya existe categoría con ID: X" |
| Categoría | Desc | not empty | ValidationException | "No puede estar vacío" |
| Producto | ID | > 0 | ValidationException | "ID debe ser positivo" |
| Producto | ID | ≠ existe | DuplicateIdException | "Ya existe producto con ID: X" |
| Producto | Precio | > 0 | ValidationException | "Debe ser positivo" |
| Producto | Stock | ≥ 0 | ValidationException | "No puede ser negativo" |

---

## 🧪 PRUEBA RÁPIDA DEL SISTEMA

Para ver las validaciones en acción:

```bash
# 1. Edita ValidationTestDemo.java si quieres cambiar los valores de prueba
# 2. Ejecuta como aplicación principal (main)
# 3. Verás todos los validadores funcionando

Ubicación: storeapp/utils/ValidationTestDemo.java
```

---

## 🔑 CLASES CLAVE

### 1️⃣ EntityValidator.java
- **Qué hace**: Valida campos individuales
- **Métodos**: `isValidEmail()`, `isValidPrice()`, `isValidStock()`, etc.
- **Lanza**: `ValidationException`

### 2️⃣ DuplicateChecker.java
- **Qué hace**: Detecta duplicados en listas
- **Métodos**: `checkCustomerDuplicateById()`, `checkProductDuplicateById()`, etc.
- **Lanza**: `DuplicateIdException`, `DuplicateEmailException`

### 3️⃣ Excepciones Personalizadas
- **DuplicateIdException**: Para IDs duplicados
- **DuplicateEmailException**: Para emails duplicados
- **ValidationException**: Para datos inválidos

---

## 💾 FLUJO COMPLETO DE CREAR UN CLIENTE

```
1. Usuario ejecuta: custumerServiceImpl.createCustomer()
   │
2. Sistema pide: ID, Nombre, Email, Contraseña, Cupo, Tipo
   │
3. Para cada dato:
   ├─ EntityValidator valida formato
   ├─ DuplicateChecker valida unicidad (si aplica)
   └─ Si error → Lanza excepción → Captura → Mensaje de error
   │
4. Si TODOS válidos:
   ├─ Guarda en CustomerRepository
   ├─ Retorna cliente creado
   └─ Mensaje: "✓ Cliente registrado exitosamente"
   │
5. Si ALGUNO inválido:
   ├─ Lanza excepción
   ├─ Se captura en try-catch
   ├─ Se muestra mensaje específico
   └─ NO se guarda nada
```

---

## ✨ BENEFICIOS

✅ **Sin duplicados**: Imposible crear ID o email duplicados
✅ **Datos válidos**: Todo cumple reglas específicas
✅ **Mensajes claros**: Usuario sabe exactamente qué está mal
✅ **Fácil de mantener**: Validaciones en un lugar centralizado
✅ **Fácil de extender**: Agregar nuevas validaciones es simple
✅ **Profesional**: Manejo de excepciones robusto
✅ **Reutilizable**: Los mismos validadores en create y update
✅ **Listo para BD**: Sistema preparado para migrar a base de datos

---

## 🚀 PRÓXIMAS MEJORAS

- [ ] Agregar base de datos SQL (en lugar de ArrayList)
- [ ] Logging profesional con Log4j
- [ ] Configuración externa de mensajes
- [ ] Validaciones asincrónicas
- [ ] Caché de resultados
- [ ] Auditoría de cambios
- [ ] API REST con validaciones

---

## 📚 DOCUMENTACIÓN INCLUIDA

1. **EXPLICACION_VALIDACIONES.md** - Guía completa detallada
2. **RESUMEN_VALIDACIONES.txt** - Resumen visual en ASCII
3. **ExceptionHandlerExample.java** - Ejemplos de manejo de excepciones
4. **ValidationTestDemo.java** - Prueba interactiva del sistema
5. **Esta guía** - Referencia rápida

---

## ❓ PREGUNTAS FRECUENTES

**P: ¿Qué pasa si intento crear un cliente con ID que ya existe?**
R: Se lanza `DuplicateIdException` con mensaje: "Ya existe un cliente registrado con el ID: X"

**P: ¿Puedo tener dos clientes con el mismo email?**
R: No. Si lo intentas se lanza `DuplicateEmailException`

**P: ¿Qué validaciones se hacen al actualizar?**
R: Se validan los mismos campos que al crear (excepto duplicado de ID)

**P: ¿Dónde están centralizadas todas las validaciones?**
R: En `storeapp/utils/EntityValidator.java` y `storeapp/utils/DuplicateChecker.java`

**P: ¿Cómo manejo las excepciones en mi código?**
R: Usa try-catch para capturar `DuplicateIdException`, `DuplicateEmailException` o `ValidationException`

**P: ¿Puedo agregar más validaciones?**
R: Sí, agrega métodos a `EntityValidator.java` o `DuplicateChecker.java`

---

## 📞 RESUMEN FINAL

Has implementado **validaciones profesionales** que:
- ✅ Previenen duplicados completamente
- ✅ Validan formato de todos los datos
- ✅ Usan excepciones personalizadas
- ✅ Proporcionan mensajes claros
- ✅ Están centralizadas y reutilizables
- ✅ Son fáciles de mantener y extender

**Tu aplicación ahora está lista para producción con protección de datos.** 🎉


