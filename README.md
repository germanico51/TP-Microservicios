# TP Integrador Final – Microservicios con Spring Cloud

## 📌 Objetivo

Este proyecto integrador tiene como fin **validar conocimientos prácticos y técnicos sobre el desarrollo de microservicios en Java** utilizando **Spring Cloud**, junto con otros componentes del ecosistema de microservicios. Se trabajó con distintos **patrones de diseño**, prácticas modernas y herramientas propias de arquitecturas distribuidas.

Este trabajo fue realizado en el marco del curso **"Microservicios con Spring Cloud"** dictado por **TodoCode Academy**.

---

## 🛍️ Escenario

El sistema simula el backend de una **tienda online de electrodomésticos**, compuesta por microservicios independientes que interactúan entre sí para brindar una experiencia de compra fluida:

### 🧩 Microservicios desarrollados

- ### 🧾 Productos Service
  - Gestiona los productos disponibles.
  - Permite listar, consultar y administrar electrodomésticos.
  - Atributos: código, nombre, marca y precio.

- ### 🛒 Carritos Service
  - Maneja los carritos de compra de los usuarios.
  - Permite agregar o quitar productos.
  - Calcula el precio total del carrito.

- ### 💳 Ventas Service
  - Registra ventas con ID y fecha.
  - Se vincula con un carrito existente.
  - Consulta el total y los productos a través de los otros microservicios.

- ### 🛠️ Config Server
  - Servidor de configuración centralizada para todos los microservicios.
  - Utiliza un repositorio Git como fuente remota (`config-data`).
  - Las credenciales se leen desde variables de entorno (`GIT_USER` y `GIT_PASSWORD`).

---

## ⚙️ Requerimientos Técnicos Implementados

1. **Arquitectura basada en microservicios**, con diseño modular y comunicación entre servicios.
2. CRUD completo en cada microservicio con su propia configuración externa.
3. **Servidor de Configuración (Spring Cloud Config Server)**:
   - Centraliza los archivos de configuración `.yml` de los microservicios.
   - Configuración disponible en este mismo repositorio.
4. **Eureka Server** para descubrimiento de servicios.
5. **Spring Cloud Load Balancer** para balanceo de carga (con pruebas mediante Postman).
6. **Resilience4J Circuit Breaker y Retry** para tolerancia a fallos.
7. **API Gateway** como punto único de entrada.
8. **Base de datos MySQL** utilizada en los servicios que requieren persistencia.

---

## 🚀 Cómo correr el proyecto

> Actualmente, el proyecto **no utiliza Docker**. Los servicios deben ejecutarse manualmente desde cada submódulo utilizando tu IDE o comandos de Maven.

Ejemplo para levantar un servicio:
```bash
cd config-server
./mvnw spring-boot:run
Asegurarse de tener una instancia de MySQL corriendo y configurada correctamente según los archivos application.yml de cada servicio.
