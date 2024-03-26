package ean.corte2;

import java.util.Scanner;
import java.util.Stack;

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
        System.out.println("-----------------------------------------------------------");
        System.out.println("\nMenú de selección de usuario:\n" +
                "1. Cliente\n" +
                "2. Administrador de inventario\n"+
                "0. Salir del programa");

        System.out.println("Ingresa el número de la opción que deseas");
        int option = sc.nextInt();

        return verificarOpcionValidaPrincipal(option);
    }

    public static int menuCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------");
        System.out.println("\n Menú Cliente:\n" +
                "1. Crear nuevo cliente\n" +
                "2. Ver inventario de la tienda\n" +
                "3. Crear nuevo carrito de compras\n" + // in -> cantidad de datos a ingresar
                "4. Añadir producto al carrito\n" + // true o false -> mayor a menor
                "5. Editar cuanto quieres de cada producto\n" + // int -> codigo
                "6. Eliminar producto del carrito\n" + // int -> codigo
                "7. Mostrar factura del carrito\n" + // int -> codigo
                "8. Finalizar y pagar factura\n" +
                "0. Salir");

        System.out.println("Ingresa el número de la opción que deseas");
        int option = sc.nextInt();

        return verificarOpcionValidaCliente(option);
    }

    public static int menuAdmin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------------------------");
        System.out.println("\nMenú administrador:\n" +
                "1. Crear nuevo inventario ó regenerar el existente\n" +
                "2. Cargar inventario\n" + // in -> cantidad de datos a ingresar
                "3. Ordenar por codigo (Mayor a menor)\n" + // true o false -> mayor a menor
                "4. Buscar por codigo de producto\n" +
                "5. Añadir producto al inventario\n"+ // int -> codigo
                "6. Modificar campos del registro\n" + // in -> codigo
                "7. Eliminar campos del registro\n" + // in -> codigo
                "8. Mostras tabla del inventario\n" +
                "9. Mostrar tabla de clientes\n" +
                "10. Mostrar tabla de facturas\n" +
                "0. Salir");

        System.out.println("Ingresa el número de la opción que deseas");
        int option = sc.nextInt();

        return verificarOpcionValidaAdmin(option);
    }

    public static int verificarOpcionValidaPrincipal(int num) { // verifica que la opción ingresada por el usuario este
        // dentro de
        // las posibilidades del menu
        Scanner sc = new Scanner(System.in);
        if (num >= 0 && num < 3) {
            return num;
        } else {
            System.out.println("Opción incorrecta, intentalo nuevamente");
            int option = sc.nextInt();
            verificarOpcionValidaPrincipal(option);
        }
        return 0;
    }

    public static int verificarOpcionValidaCliente(int num) { // verifica que la opción ingresada por el usuario este
                                                              // dentro de
        // las posibilidades del menu
        Scanner sc = new Scanner(System.in);
        if (num >= 0 && num < 9) {
            return num;
        } else {
            System.out.println("Opción incorrecta, intentalo nuevamente");
            int option = sc.nextInt();
            verificarOpcionValidaCliente(option);
        }
        return 0;
    }

    public static int verificarOpcionValidaAdmin(int num) { // verifica que la opción ingresada por el usuario este
                                                            // dentro de
        // las posibilidades del menu
        Scanner sc = new Scanner(System.in);
        if (num >= 0 && num < 11) {
            return num;
        } else {
            System.out.println("Opción incorrecta, intentalo nuevamente");
            int option = sc.nextInt();
            verificarOpcionValidaAdmin(option);
        }
        return 0;
    }

    public static int verificarExistenciaUsuarioYFactura(Cliente cliente, Factura factura) {
        if (cliente != null && factura != null) {
            return 1; // se retorna 1 si ambos parametros existen y son diferentes de null
        } else if (cliente == null) {
            System.out.println("Al parecer no existe un cliente activo. Crea uno e intentalo nuevamente.");
            return 0;
        } else {
            System.out.println("Al parecer no hay una factura creada. Crea una e intentalo nuevamente.");
            return -1;
        }
    }

    public static void imprimirClientes(Stack<Cliente> stackClientes) {
        Stack<Cliente> pivot = new Stack<Cliente>();
        pivot.addAll(stackClientes);

        System.out.println("Elementos de la pila clientes: \n");

        System.out.printf("%-15s%-22s%n", "1. Cedula", "2. Nombre");
        System.out.println();
        while (!pivot.isEmpty()) {
            Object activoObject = pivot.pop();
            Cliente activo = (Cliente) activoObject;
            if (activo != null) {
                System.out.printf("%-15d%-22s%n", activo.getCedula(), activo.getNombre());
            }
        }
    }

    public static void imprimirFacturas(Stack<Factura> stackFacturas) {
        Stack<Factura> pivot = new Stack<Factura>();
        pivot.addAll(stackFacturas);

        System.out.println("Elementos de la pila facturas: \n");
        System.out.println();
        while (!pivot.isEmpty()) {
            Object activoObject = pivot.pop();
            Factura activo = (Factura) activoObject;
            if (activo != null) {
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                activo.imprimirFactura();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Stacks o pilas para el almacenamiento de todos los usuarios que se creen
        Stack clientes = new Stack<Cliente>();
        Stack facturas = new Stack<Factura>();

        // Quemamos cliente para las pruebas del codigo
        Cliente cliente1 = new Cliente(23306457, "Blanca Daza");
        Cliente cliente2 = new Cliente(4327437, "Eugenio Hernández");
        Cliente cliente3 = new Cliente(1023782439, "Steven Daza");
        Cliente cliente4 = new Cliente(1234576892, "Geral Mahecha");
        Cliente cliente5 = new Cliente(1039762139, "Jenny Carrillo");

        clientes.push(cliente1);
        clientes.push(cliente2);
        clientes.push(cliente3);
        clientes.push(cliente4);
        clientes.push(cliente5);

        // quemamos facturas para las pruebas de codigo

        // generamos un inventario para quemar facturas
        InventarioProductos inventarioPrueba = new InventarioProductos();
        inventarioPrueba.cargar();

        Factura factura1 = new Factura(inventarioPrueba, cliente1);
        factura1.quemarFactura(2);
        Factura factura2 = new Factura(inventarioPrueba, cliente2);
        factura2.quemarFactura(4);
        Factura factura3 = new Factura(inventarioPrueba, cliente3);
        factura3.quemarFactura(4);
        Factura factura4 = new Factura(inventarioPrueba, cliente5);
        factura4.quemarFactura(4);

        facturas.push(factura1);
        facturas.push(factura2);
        facturas.push(factura3);
        facturas.push(factura4);

        // Importamos e instaciamos el inventario para poder que tanto administrador y
        // cliente lo manipulen
        InventarioProductos inventario = new InventarioProductos(); // Importamos
        Object[][] data = inventario.getData(); // instanciamos en una variable local

        // Instanciamos ambas variables para poder vericifar si estan vacias o no en
        // ejecuciones posteriores
        Cliente clienteActivo = null;
        Factura facturaActiva = null;

        // antes de iniciar el while principal, preguntamos el tipo de usuario a iniciar
        int tipoUsuario = menuPrincipal();

        // Este while mantendra el menu principal y la ejecuación del programa
        while (tipoUsuario != 0) {

            // CLIENTE
            if (tipoUsuario == 1) {
                // tendra solo las opciones de facturación

                // Verificamos la existencia del inventario por cuestiones practicas para evitar
                // errores
                if (data == null) {
                    inventario.cargar();
                }

                int userEntry = menuCliente();

                // While que mantendra al usuario dentro del menu cliente,
                // si ingresa 0 este While se rompre y sale al while del menu principal
                while (userEntry != 0) {

                    switch (userEntry) {
                        case 1: // Crear un nuevo cliente
                            // String nombre = sc.nextLine();
                            // int cedula = sc.nextInt();
                            int stackCapacity = clientes.size();
                            clientes.setSize(stackCapacity + 1); // se aumenta el tamaño del stack de uno en uno cada se agrega un cliente
                            
                            System.out.println("Ingresa tu nombre");
                            String nombre = sc.nextLine();
                            System.out.println("Ingresa tu numero de cedula");
                            int cedula = sc.nextInt();
                            clienteActivo = new Cliente(cedula, nombre);
                            clientes.push(clienteActivo);
                            System.out.println(clientes.capacity());

                            // consiguiendo los codigos de las facturas del cliente
                            // sera util a la hora de armar la base de facturas

                            System.out.println("Cliente creado correctamente");
                            break;
                        case 2: // Mostrar inventario de la tienda
                            inventario.imprimirVerificacion();
                            break;
                        case 3: // Crear factura o "Carrito de compras"
                            if (clienteActivo != null) {
                                facturaActiva = new Factura(inventario, clienteActivo);

                                System.out.println("Carrito de comprar preparado, ya puedes añadir tus productos");
                            } else {
                                System.out.println(
                                        "Al parecer no hay un cliente creado. Crea uno e intentalo nuevamente");
                            }
                            break;

                        case 4: // Añadir producto al carrito
                            if (verificarExistenciaUsuarioYFactura(clienteActivo, facturaActiva) == 1) {
                                System.out.println("Ingresa el codigo del producto a añadir");
                                int codigoProducto = sc.nextInt();
                                System.out.println("Ingresa la cantidad que deseas de este producto");
                                int cantidadProducto = sc.nextInt();
                                if (facturaActiva.añadirAFactura(codigoProducto, cantidadProducto) == true){
                                    System.out.println("Producto añadido correcamtene");
                                }else{
                                    System.out.println("Oops! Al parecer no encontramos un producto con ese codigo, intentalo nuevamente.");
                                }
                                
                                
                                // facturaActiva.imprimirFactura();

                                break;
                            } else {
                                break;
                            }

                        case 5: // Cambiar cantidad de producto en carrito
                            if (verificarExistenciaUsuarioYFactura(clienteActivo, facturaActiva) == 1) {
                                facturaActiva.imprimirFactura();
                                System.out.println("Ingresa en que posición de la lista esta el producto a editar");
                                int indexProducto = sc.nextInt();
                                System.out.println("Ingresa la nueva cantidad para este producto");
                                facturaActiva.editarProductoFactura(indexProducto);
                                facturaActiva.imprimirFactura();
                                break;
                            } else {
                                break;
                            }
                            // factura.añadirProducto(codigoProducto)

                        case 6: // Eliminar producto de factura
                            if (verificarExistenciaUsuarioYFactura(clienteActivo, facturaActiva) == 1) {
                                System.out.println("Ingresa en que fila se encuentra el producto que deseas eliminar");
                                int indexProducto = sc.nextInt() - 1;
                                facturaActiva.eliminarDeFactura(indexProducto);
                                System.out.println("Producto eliminado correctamente");
                                break;
                            } else {
                                break;
                            }

                        case 7: // Mostrar la factura del carrito
                            if (verificarExistenciaUsuarioYFactura(clienteActivo, facturaActiva) == 1) {
                                facturaActiva.imprimirFactura();
                                break;
                            }
                            break;

                        case 8: // Finalizar y pagar factura -> Esto la añade a la lista de facturas del cliente
                                // y al stack de facutras del programa
                            if (verificarExistenciaUsuarioYFactura(clienteActivo, facturaActiva) == 1) {
                                clienteActivo.añadirFactura(facturaActiva);
                                facturas.push(facturaActiva);
                                System.out.println("Factura finalizada correctamente");
                                break;
                            }
                            break;

                        default:
                            break;
                    }
                    userEntry = menuCliente();
                }

                // ADMINISTRADOR
            } else if (tipoUsuario == 2) {
                // tendra todas las opciones de inventarioProductos como modificar y eliminar
                // productos del inventario
                int userEntry = menuAdmin();
                while (userEntry != 0) {
                    switch (userEntry) {
                        case 1: // crear inventario
                            inventario = new InventarioProductos();
                            System.out.println("Inventario generado correctamente, ya puedes cargar los productos.");
                            break;
                        case 2: // cargar inventario
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

                        case 3: // ordenar inventario
                            inventario.ordenarMenorMayor();
                            System.out.println("Productos ordenados correctamente.");
                            break;

                        case 4: // buscar en inventario
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

                        case 5: // añadir al inventario
                            inventario.añadirProductoInventario();
                            inventario.cargar();
                            break;

                        case 6: // editar inventario
                            System.out.println("Ingresa el codigo del producto a editar");
                            int code = sc.nextInt();
                            System.out.println("Columna a editar");
                            int column = sc.nextInt();
                            inventario.modificar(code, column);
                            break;

                        case 7: // eliminar de inventario
                            System.out.println("Ingresa el codigo del producto a eliminar");
                            int delCode = sc.nextInt();
                            inventario.eliminarRegistro(delCode);
                            break;

                        case 8: // mostrar inventario
                            inventario.imprimirVerificacion();
                            break;
                        case 9: // Mostrar listado de clientes
                            imprimirClientes(clientes);
                            break;
                        case 10: // mostrar listado de facturas.
                            imprimirFacturas(facturas);
                            break;
                        default:
                            break;
                    }
                    userEntry = menuAdmin();
                }
            }
            tipoUsuario = menuPrincipal();
        }
    }
}
