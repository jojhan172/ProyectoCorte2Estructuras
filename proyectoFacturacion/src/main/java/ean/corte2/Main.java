package ean.corte2;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private final Scanner sc = new Scanner(System.in);
    /*
    * El metodo cargar, cargará los datos iniciales de nuestro array
    * es decir el codigo (0), el nombre(1), la cantidad de items por producto(2),
    * el valor del iva(3), y el precio unitario del producto(4)
    * */

    // se usa el tipo de dato Object para que nuestro array pueda tener varios tipos de datos.

    // codigo - Nombre - cantidad - iva(int) - precioUnitario - Subtotal - valorIva - Total
    private Object[][] data; // multidimensional de 8 columnas, la cantidad de filas dependera del usuario

    public static void menu(){
        System.out.println("Bienvenido al programa de facturación. Aquí tienes el menú:\n" +
                "1. Crear\n"+
                "2. Cargar\n"+ // in -> cantidad de datos a ingresar
                "3. Ordenar\n"+ // true o false -> mayor a menor
                "4. Buscar\n" + // in -> codigo
                "5. Modificar campos del registro\n"+// in -> codigo
                "6. Eliminar campos del registro\n"+// in -> codigo
                "7. Mostras en tablas las estructuras\n"+
                "0. Salir\n");
    }
    private void cargar(int cantidadDatos){
        this.data = new Object[cantidadDatos][8];
        for (int i = 0; i < cantidadDatos; i++){
            //System.out.println("Ingresa el codigo del producto\n");
            //this.data[i][0] = sc.nextInt();
            //System.out.println("Ingresa el nombre del producto\n");
            //this.data[i][1] = sc.next();
            //System.out.println("Ingresa la cantidad de items del producto\n");
            //this.data[i][2] = sc.nextFloat();
            //System.out.println("Ingresa el valor del IVA del producto en %\n");
            //this.data[i][3]= sc.nextInt();
            //System.out.println("Ingresa el precio por unidad del producto\n");
            //this.data[i][4] = sc.nextFloat();

            this.data[i][0] = 103765; // codigo (int)
            this.data[i][1] = "Libra carne de res"; // nombre (String)
            this.data[i][2] = 2; // cantidad (float)
            this.data[i][3] = 19; // valor IVA en % (int)
            this.data[i][4] = 10.18; // precio unitario (float)
        }
    }
    private void imprimirVerificacion(){
        // Titulos de la tabla
        System.out.printf("%-12s%-22s%-12s%-15s%-15s%n", "Codigo", "Nombre", "Cantidad","Valor IVA", "Precio Unitario");
        System.out.println();

        // recorrido de filas en data
        for (Object[] fila : this.data) {
            System.out.printf("%-12d%-22s%-12d%-15s%-15.1f%n", fila[0], fila[1], fila[2],fila[3], fila[4]);
        }
    }
    public static void main(String[] args) {
        int option;
        menu();

        Main main = new Main();// creación del objeto Main para poder hacer uso los diferentes metodos de la clase

        // Solicitud de la cantidad total de ingresos a realizar
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingresa la cantidad de productos a añadir -> ");
        int cantidadDatos = sc.nextInt();
        main.cargar(cantidadDatos);
        main.imprimirVerificacion();
    }
}