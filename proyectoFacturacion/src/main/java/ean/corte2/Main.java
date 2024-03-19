package ean.corte2;

import java.util.Scanner;
import ean.corte2.*;

// workin' in
public class Main {
    Cliente[] clientes;
    Factura[] facturas;

    // Menu clientes
    /*
     * 1. Ver inventario de la tienda
     * 2. añadir productos a la factura
     * 3. Eliminar productos de la factura
     * 4. Imprimir factura
     * 5. Finalizar factura y salir.
     */
    public static int menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMenú de selección de usuario:\n" +
                "1. Cliente\n" +
                "2. Administrador de inventario\n");

        System.out.println("Ingresa el número de la opción que deseas");
        int option = sc.nextInt();

        return verificarOpcionValida(option);
    }

    public static int menuCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Menú Cliente:\n" +
                "1. Crear nuevo cliente\n" +
                "2. Ver inventario de la tienda\n" +
                "3. Crear nuevo carrito de compras\n" + // in -> cantidad de datos a ingresar
                "4. Añadir producto al carrito\n" + // true o false -> mayor a menor
                "5. Editar item del carrito\n" + // int -> codigo
                "6. Eliminar producto del carrito\n" + // int -> codigo
                "7. Mostrar factura del carrito\n" + // int -> codigo
                "8. \n" +
                "0. Salir");

        System.out.println("Ingresa el número de la opción que deseas");
        int option = sc.nextInt();

        return verificarOpcionValida(option);
    }

    public static int menuAdmin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMenú administrador:\n" +
                "1. Crear nuevo inventario ó regenerar el existente\n" +
                "2. Cargar inventario\n" + // in -> cantidad de datos a ingresar
                "3. Ordenar por codigo (Mayor a menor)\n" + // true o false -> mayor a menor
                "4. Buscar por codigo de producto\n" + // int -> codigo
                "5. Modificar campos del registro\n" + // in -> codigo
                "6. Eliminar campos del registro\n" + // in -> codigo
                "7. Mostras tabla del inventario\n" +
                "8. Mostrar tabla de clientes\n" +
                "9. Mostrar tabla de facturas\n" +
                "0. Salir");

        System.out.println("Ingresa el número de la opción que deseas");
        int option = sc.nextInt();

        return verificarOpcionValida(option);
    }

    public static int verificarOpcionValida(int num) { // verifica que la opción ingresada por el usuario este dentro de
                                                       // las posibilidades del menu
        Scanner sc = new Scanner(System.in);
        if (num >= 0 && num < 9) {
            return num;
        } else {
            System.out.println("Opción incorrecta, intentalo nuevamente");
            int option = sc.nextInt();
            verificarOpcionValida(option);
        }
        return 0;
    }

    public static int verificarExistenciaUsuarioYFactura(Cliente cliente, Factura factura) {
        if (cliente != null && factura != null) {
            return 1; // se retorna uno si ambos parametros existen y son diferentes de null
        } else if (cliente == null) {
            System.out.println("Al parecer no existe un cliente activo. Crea uno e intentalo nuevamente.");
            return 0;
        } else {
            System.out.println("Al parecer no hay una factura creada. Crea una e intentalo nuevamente.");
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventarioProductos inventario = new InventarioProductos();
        Object[][] data = inventario.getData();
        Cliente clienteActivo = null;
        Factura nuevaFactura = null;

        int tipoUsuario = menuPrincipal();
        while (tipoUsuario != -1) {
            if (tipoUsuario == 1) {
                // Usuario cliente
                // tendra solo las opciones de facturación
                int userEntry = -1;
                if (data == null) {
                    inventario.cargar();
                }
                while (userEntry != 0) {
                    
                    switch (userEntry) {
                        case 1:
                            // String nombre = sc.nextLine();
                            // int cedula = sc.nextInt();
                            clienteActivo = new Cliente(1003765269, "Johan");
                            System.out.println("Cliente creado correctamente");
                            break;
                        case 2:
                            inventario.imprimirVerificacion();
                            break;
                        case 3:
                            if (clienteActivo != null) {
                                nuevaFactura = new Factura(inventario, clienteActivo);
                                System.out.println("Carrito de comprar preparado, ya puedes añadir tus productos");
                            }else{
                                System.out.println("Al parecer no hay un cliente creado. Crea uno e intentalo nuevamente");
                            }
                            break;

                        case 4:
                            if (verificarExistenciaUsuarioYFactura(clienteActivo, nuevaFactura) == 1) {

                            }
                            break;
                        case 5:
                            int entrada = sc.nextInt();
                            // factura.añadirProducto(codigoProducto)
                            break;
                        default:
                            break;
                    }
                    userEntry = menuCliente();
                }

            } else if (tipoUsuario == 2) { // Usuario administrador
                // trendra todas las opciones de inventarioProductos
                // como modificar y eliminar productos del inventario
                int userEntry = menuAdmin();
                data = inventario.getData();
                while (userEntry != 0) {
                    switch (userEntry) {
                        case 1:
                            inventario = new InventarioProductos();
                            System.out.println("Inventario generado correctamente, ya puedes cargar los productos.");
                            break;
                        case 2:
                            if (inventario != null) { // Verifica si hay una factura creada.
                                // System.out.println("Ingresa la cantidad de productos a añadir -> ");
                                // int cantidadDatos = sc.nextInt();
                                inventario.cargar();
                                System.out.println("Productos añadidos correctamente");
                                break;
                            } else {
                                System.out.println("\nNo se ha creado ninguna factura, crea una y luego continúa.");
                                break;
                            }

                        case 3:
                            inventario.ordenarMayorMenor();
                            System.out.println("Productos ordenados correctamente.");
                            break;

                        case 4:
                            data = inventario.getData();
                            System.out.println("Ingresa el codigo del producto a buscar -> ");
                            int target = sc.nextInt();
                            int index = inventario.buscar(target);

                            if (index == -1) {
                                System.out.println("Codigo de producto no encontrado");
                            } else {
                                System.out.println("Codigo encontrado en el indice: " + index);
                                System.out.printf("%-12s%-22s%-12s%-15s%-15s%n", "1. Codigo", "2. Nombre",
                                        "3. Cantidad",
                                        "4. Valor IVA", "5. Precio Unitario");
                                System.out.printf("%-12d%-22s%-12d%-15s%-15.1f%n", data[index][0], data[index][1],
                                        data[index][2], data[index][3], data[index][4]);
                            }
                            break;

                        case 5:
                            System.out.println("Ingresa el codigo del producto a editar");
                            int code = sc.nextInt();
                            System.out.println("Columna a editar");
                            int column = sc.nextInt();
                            inventario.modificar(code, column);
                            break;

                        case 6:
                            System.out.println("Ingresa el codigo del producto a eliminar");
                            int delCode = sc.nextInt();
                            inventario.eliminarRegistro(delCode);
                            break;

                        case 7:
                            inventario.imprimirVerificacion();
                            break;
                    }
                    userEntry = menuAdmin();
                }
            }

            tipoUsuario = menuPrincipal();
        }
    }
}
