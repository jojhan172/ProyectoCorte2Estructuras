package ean.corte2;

import java.util.Scanner;
import ean.corte2.*;

// workin' in
public class Main {

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

    public static int menuCliente(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Menú Cliente:\n" +
                "#. Crear nuevo cliente\n" +
                "#. Ver inventario de la tienda"+
                "#. Crear nuevo carrito de compras\n" + // in -> cantidad de datos a ingresar
                "#. Añadir producto al carrito\n" + // true o false -> mayor a menor
                "#. Editar item del carrito\n" + // int -> codigo
                "#. Eliminar producto del carrito\n" + // int -> codigo
                "#. Mostrar factura del carrito\n" + // int -> codigo
                "#. \n" +
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
                "3. Ordenar codigos\n" + // true o false -> mayor a menor
                "4. Buscar por codigo de producto\n" + // int -> codigo
                "5. Modificar campos del registro\n" + // in -> codigo
                "6. Eliminar campos del registro\n" + // in -> codigo
                "7. Mostras tabla del inventario\n" + 
                "8. Mostrar tabla de clientes\n"+
                "9. Mostrar tabla de facturas\n"+
                "0. Salir");

        System.out.println("Ingresa el número de la opción que deseas");
        int option = sc.nextInt();

        return verificarOpcionValida(option);
    }

    public static int verificarOpcionValida(int num) {
        Scanner sc = new Scanner(System.in);
        if (num >= 0 && num < 8) {
            return num;
        } else {
            System.out.println("Opción incorrecta, intentalo nuevamente");
            int option = sc.nextInt();
            verificarOpcionValida(option);
        }
        return 0;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventarioProductos inventario = new InventarioProductos();
        Object[][] data = inventario.getData();

        int tipoUsuario = menuPrincipal();
        while (tipoUsuario != 1) {
            if (tipoUsuario == 1) { // Usuario cliente
                // tendra solo las opciones de facturación
                if (data == null){
                    inventario.cargar();
                }           

            } else if (tipoUsuario == 2) { // Usuario administrador
                // trendra todas las opciones de inventarioProductos
                // como modificar y eliminar productos del inventario
                int userEntry = menuAdmin();
                while (userEntry != 0) { 
                    switch (userEntry) {
                        case 1:
                            inventario = new InventarioProductos();
                            System.out.println("Inventario generado correctamente, ya puedes cargar los productos.");
                            userEntry = menuAdmin();
                            break;
                        case 2:
                            if (inventario != null) { // Verifica si hay una factura creada.
                                // System.out.println("Ingresa la cantidad de productos a añadir -> ");
                                // int cantidadDatos = sc.nextInt();
                                inventario.cargar();
                                System.out.println("Productos añadidos correctamente");
                                userEntry = menuAdmin();
                            } else {
                                System.out.println("\nNo se ha creado ninguna factura, crea una y luego continúa.");
                                userEntry = menuAdmin();
                            } 
                            break;
    
                        case 3:
                            inventario.ordenarMayorMenor();
                            System.out.println("Productos ordenados correctamente.");
                            userEntry = menuAdmin();
                            break;
    
                        case 4:
                            System.out.println("Ingresa el codigo del producto a buscar -> ");
                            int target = sc.nextInt();
                            int index = inventario.buscar(target);
    
                            if (index == -1) {
                                System.out.println("Codigo de producto no encontrado");
                            } else {
                                System.out.println("Codigo encontrado en el indice: " + index);
                                System.out.printf("%-12s%-22s%-12s%-15s%-15s%n", "1. Codigo", "2. Nombre", "3. Cantidad",
                                        "4. Valor IVA", "5. Precio Unitario");
                                System.out.printf("%-12d%-22s%-12d%-15s%-15.1f%n", data[index][0], data[index][1],
                                        data[index][2], data[index][3], data[index][4]);
                            }
                            userEntry = menuAdmin();
                            break;
    
                        case 5:
                            System.out.println("Ingresa el codigo del producto a editar");
                            int code = sc.nextInt();
                            System.out.println("Columna a editar");
                            int column = sc.nextInt();
                            inventario.modificar(code, column);
    
                            userEntry = menuAdmin();
                            break;
    
                        case 6:
                            System.out.println("Ingresa el codigo del producto a eliminar");
                            int delCode = sc.nextInt();
                            inventario.eliminarRegistro(delCode);
                            userEntry = menuAdmin();
                            break;
    
                        case 7:
                            inventario.imprimirVerificacion();
                            userEntry = menuAdmin();
                            break;
    
                        default:
                            System.out.println("Saliendo al menú principal");
                            break;
                    }
                    if (userEntry == 0) {
                        break;
                    }
                }
            }
            tipoUsuario = menuPrincipal();
        }
    }
}
