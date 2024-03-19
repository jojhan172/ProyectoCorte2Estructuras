package ean.corte2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class InventarioProductos {
    Random rm = new Random();
    Scanner sc = new Scanner(System.in);
    
    private int cantidadProductos;
    private Object[][] basicInfoProductos;// nombre del producto - cantidad en inventario - IVA producto - precio unitario
     // ** codigo - Nombre - cantidad - iva(int) - precioUnitario - Subtotal -
    // valorIva - Total **
     // multidimensional de 8 columnas, la cantidad de filas dependera del usuario
    public Object[][] data;
    // se usa el tipo de dato Object para que nuestro array pueda tener varios tipos
    // de datos.
    public InventarioProductos(){
        this.basicInfoProductos= new Object[5][4];

        this.basicInfoProductos[0][0] = "Arroz"; // nombres
        this.basicInfoProductos[0][1] = rm.nextInt(100); // cantidad
        this.basicInfoProductos[0][2] = 0; // IVA en %
        this.basicInfoProductos[0][3] = 30000.0;


        this.basicInfoProductos[1][0] = "Pescado"; // nombres
        this.basicInfoProductos[1][1] = rm.nextInt(100); // cantidad
        this.basicInfoProductos[1][2] = 0; // IVA en %
        this.basicInfoProductos[1][3] = 30000.0;

        this.basicInfoProductos[2][0] = "Aceite"; // nombres
        this.basicInfoProductos[2][1] = rm.nextInt(100); // cantidad
        this.basicInfoProductos[2][2] = 19; // IVA en %
        this.basicInfoProductos[2][3] = 30000.0;

        this.basicInfoProductos[3][0] = "Cereal"; // nombres
        this.basicInfoProductos[3][1] = rm.nextInt(100); // cantidad
        this.basicInfoProductos[3][2] = 19; // IVA en %
        this.basicInfoProductos[3][3] = 30000.0;

        this.basicInfoProductos[4][0] = "Chocolate"; // nombres
        this.basicInfoProductos[4][1] = rm.nextInt(100); // cantidad
        this.basicInfoProductos[4][2] = 5; // IVA en %
        this.basicInfoProductos[4][3] = 30000.0;

        this.cantidadProductos = basicInfoProductos.length;
    }
     
    /*
     * El metodo cargar, cargará los datos iniciales de nuestro array
     * es decir el codigo (0), el nombre(1), la cantidad de items por producto(2),
     * el valor del iva(3), y el precio unitario del producto(4)
     */

    
    public Object[][] getData() {
        return data;
    }
   

    public static int menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nMenú:\n" +
                "1. Crear nueva factura\n" +
                "2. Cargar\n" + // in -> cantidad de datos a ingresar
                "3. Ordenar\n" + // true o false -> mayor a menor
                "4. Buscar\n" + // int -> codigo
                "5. Modificar campos del registro\n" + // in -> codigo
                "6. Eliminar campos del registro\n" + // in -> codigo
                "7. Mostras en tablas las estructuras\n" +
                "0. Salir");

        System.out.println("Ingresa el número de la opción que deseas");
        int option = sc.nextInt();

        return verificarOpcionValida(option);
    }

    // esta función verifica si el numero ingresado por el usuario es valido para
    // nuestro menu
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

    public void cargar() {
        this.data = new Object[this.cantidadProductos][8];
        for (int i = 0; i < this.cantidadProductos; i++) {
            // System.out.println("Ingresa el codigo del producto\n");
            // this.data[i][0] = sc.nextInt();
            // System.out.println("Ingresa el nombre del producto\n");
            // this.data[i][1] = sc.next();
            // System.out.println("Ingresa la cantidad de items del producto\n");
            // this.data[i][2] = sc.nextFloat();
            // System.out.println("Ingresa el valor del IVA del producto en %\n");
            // this.data[i][3]= sc.nextInt();
            // System.out.println("Ingresa el precio por unidad del producto\n");
            // this.data[i][4] = sc.nextFloat();

            this.data[i][0] = rm.nextInt(10000); // codigo (int)
            this.data[i][1] = basicInfoProductos[i][0]; // nombre (String)
            this.data[i][2] = basicInfoProductos[i][1]; // cantidad (float)
            this.data[i][3] = basicInfoProductos[i][2]; // valor IVA en % (int)
            this.data[i][4] = basicInfoProductos[i][3]; // precio unitario (float)
        }
    }

    public void ordenarMayorMenor() { 
        // Uso del algoritmo de Bubble sorting -> compara por parejas // O(n^2)
        Object[] aux;
        for (int i = 0; i < this.data.length -1 ; i++) { // aquí usamos el operador de casting -> (int) -> para poder
                                                     // comprar nuestros datos dabido a que estamos usando una matriz
                                                     // tipo Object[][]
            for (int j = 0; j < this.data.length - i - 1; j++) {
                if ((int) this.data[j][0] < (int) this.data[j + 1][0]) {
                    aux = this.data[j];
                    this.data[j] = this.data[j + 1];
                    this.data[j + 1] = aux;
                }
            }
        }
        
        return;
    }
    public void ordenarMenorMayor() { 
        // Uso del algoritmo de Bubble sorting -> compara por parejas // O(n^2)
        Object[] aux;
        for (int i = 0; i < this.data.length -1 ; i++) { // aquí usamos el operador de casting -> (int) -> para poder
                                                     // comprar nuestros datos dabido a que estamos usando una matriz
                                                     // tipo Object[][]
            for (int j = 0; j < this.data.length - i - 1; j++) {
                if ((int) this.data[j][0] > (int) this.data[j + 1][0]) {
                    aux = this.data[j];
                    this.data[j] = this.data[j + 1];
                    this.data[j + 1] = aux;
                }
            }
        }
        
        return;
    }

    public int buscar(int target) { // Algoritmo de busqueda binaria -> funciona solo si el array esta ordenado
        this.ordenarMenorMayor();
        int menor = 0;
        int mayor = this.data.length - 1;

        while (menor <= mayor) {
            int medio = menor + (mayor - menor) / 2;
            int valor = (int) this.data[medio][0];

            if (valor < target) { // si mi objetivo es mayor que mi valor entonces descarto la mitad izquierda del
                                  // array
                menor = medio + 1;
            } else if (valor > target) { // si mi objetivo es menor que mi valor entonces descarto la mitad derecha del
                                         // array
                mayor = medio - 1;
            } else { // Y si mi objetivo no es mayor ni menor quiere que decir que ya solo tengo un
                     // elemento en el array o no encontre el valor deseado
                this.ordenarMayorMenor();
                return medio;
            }
        }
        this.ordenarMayorMenor();
        return -1; // se retorna -1 si el objetivo no fue encontrado
    }

    public void modificar(int target, int columna) {
         // ordenamos el array ya que es necesario para poder buscar
        int index = this.buscar(target); // buscamos si el dato ingresado existe dentro del array
        if (index == -1) { // si no existe simplemente avisamos
            System.out.println("Código de producto no encontrado");
        } else { // si si existe, le pedimos el usuario el parametro a modificar
            switch (columna) {
                case 1:
                    System.out.println("Ingresa el nuevo código del producto");
                    this.data[index][0] = sc.nextInt();
                    System.out.println("Producto editado correctamente");
                    break;
                case 2:
                    System.out.println("Ingresa el nuevo nombre del producto");
                    this.data[index][1] = sc.nextLine();
                    System.out.println("Producto editado correctamente");
                    break;
                case 3:
                    System.out.println("Ingresa la nueva cantidad");
                    this.data[index][2] = sc.nextInt();
                    System.out.println("Producto editado correctamente");
                    break;
                case 4:
                    System.out.println("Ingresa el nuevo valor de IVA");
                    this.data[index][3] = sc.nextInt();
                    System.out.println("Producto editado correctamente");
                case 5:
                    System.out.println("Ingresa el nuevo valor por unidad");
                    this.data[index][4] = sc.nextFloat();
                    System.out.println("Producto editado correctamente");

                default:
                    break;
            }
            // imprimimos la fila del elemento modificado.
            System.out.printf("%-12s%-22s%-12s%-15s%-15s%n", "1. Codigo", "2. Nombre", "3. Cantidad", "4. Valor IVA",
                    "5. Precio Unitario");
            System.out.printf("%-12d%-22s%-12d%-15s%-15.1f%n", this.data[index][0], this.data[index][1],
                    this.data[index][2], this.data[index][3], this.data[index][4]);
        }
        this.ordenarMayorMenor(); // se ordena nuevamente para evitar errores
    }

    public void eliminarRegistro(int codigo) {
        this.ordenarMayorMenor();
        // verificamos que la fila exista dentro del array
        int filaAELiminar = this.buscar(codigo);
        if (filaAELiminar >= 0 && filaAELiminar < this.data.length) {
            // creamos un array con una fila menos
            Object[][] arrayPivot = new Object[this.data.length - 1][8];

            // copiamos el antiguo array al nuevo
            for (int i = 0, k = 0; i < this.data.length; i++) {

                // saltar fila a eliminar
                if (i == filaAELiminar)
                    continue;

                arrayPivot[k++] = this.data[i];
            }
            this.data = arrayPivot;
            System.out.println("Eliminado correctamente");

        } else {
            System.out.println("Fila no encontrada");
        }
    }

    // metodo para imprimir la tabla completa, sin los totales calculados (Solo los
    // datos que el usuario ingreso)
    public void imprimirVerificacion() {
        // Titulos de la tabla
        System.out.printf("%-12s%-22s%-12s%-15s%-15s%n", "1. Codigo", "2. Nombre", "3. Cantidad", "4. Valor IVA",
                "5. Precio Unitario");
        System.out.println();
        if(this.data!= null){
            // recorrido de filas en data
            for (Object[] fila : this.data) {
                System.out.printf("%-12d%-22s%-12d%-15s%-15.2f%n", fila[0], fila[1], fila[2], fila[3], fila[4]);
            }
        }else{
            System.out.println("Ups, al parecer olvidaste cargar el inventario de productos primero");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        InventarioProductos main = null;// inicializar variable para almacenar la factura
        System.out.print("Bienvenido al programa de facturación. \n");
        int opcion = menu();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    main = new InventarioProductos();
                    System.out.println("Inventario creado correctamente, ya puedes cargar los productos.");
                    opcion = menu();
                    break;
                case 2:// Solicitud de la cantidad total de ingresos a realizar
                    try {
                        if (main != null) { // Verifica si hay una factura creada.
                            //System.out.println("Ingresa la cantidad de productos a añadir -> ");
                            //int cantidadDatos = sc.nextInt();
                            main.cargar();
                            System.out.println("Productos añadidos correctamente");
                            opcion = menu();
                        } else {
                            System.out.println("\nNo se ha creado ninguna factura, crea una y luego continúa.");
                            opcion = menu();
                        }

                    } catch (Exception e) {
                        System.out.println("Hubo un error, intenta crear una nueva factura");
                        opcion = menu();
                    }
                    break;

                case 3:
                    main.ordenarMayorMenor();
                    System.out.println("Productos ordenados correctamente.");
                    opcion = menu();
                    break;

                case 4:
                    System.out.println("Ingresa el codigo del producto a buscar -> ");
                    int target = sc.nextInt();
                    int index = main.buscar(target);

                    if (index == -1) {
                        System.out.println("Codigo de producto no encontrado");
                    } else {
                        System.out.println("Codigo encontrado en el indice: " + index);
                        System.out.printf("%-12s%-22s%-12s%-15s%-15s%n", "1. Codigo", "2. Nombre", "3. Cantidad",
                                "4. Valor IVA", "5. Precio Unitario");
                        System.out.printf("%-12d%-22s%-12d%-15s%-15.1f%n", main.data[index][0], main.data[index][1],
                                main.data[index][2], main.data[index][3], main.data[index][4]);
                    }
                    opcion = menu();
                    break;

                case 5:
                    System.out.println("Ingresa el codigo del producto a editar");
                    int code = sc.nextInt();
                    System.out.println("Columna a editar");
                    int column = sc.nextInt();
                    main.modificar(code, column);

                    opcion = menu();
                    break;

                case 6:
                    System.out.println("Ingresa el codigo del producto a eliminar");
                    int delCode = sc.nextInt();
                    main.eliminarRegistro(delCode);
                    opcion = menu();
                    break;

                case 7:
                    main.imprimirVerificacion();
                    opcion = menu();
                    break;

                default:
                    System.out.println("Algo salió mal, intenta crear una nueva factura");
                    opcion = menu();
                    break;
            }
        }
    }
}