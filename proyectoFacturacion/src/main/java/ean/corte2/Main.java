package ean.corte2;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private final Random rm = new Random();
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

            this.data[i][0] = rm.nextInt(10); // codigo (int)
            this.data[i][1] = "Libra carne de res"; // nombre (String)
            this.data[i][2] = 2; // cantidad (float)
            this.data[i][3] = rm.nextInt(30); // valor IVA en % (int)
            this.data[i][4] = 10.18; // precio unitario (float)
        }
    }

    private Object[][] Ordenar(){ // Uso del algoritmo de Bubble sorting -> compara por parejas // O(n^2)
        Object[] aux;
        for (int i = 0; i < this.data.length; i++){ // aquí usamos el operador de casting -> (int) -> para poder comprar nuestros datos
            for (int j = 0; j < this.data.length - i - 1; j++){ 
                if ( (int) this.data[j][0] > (int) this.data[j+1][0]){
                    aux = this.data[j];
                    this.data[j] = this.data[j+1];
                    this.data[j+1] = aux;
                }
            }
        }
        return this.data;
    }

    private int buscar(int target){ // Algoritmo de busqueda binaria -> funciona solo si el array esta ordenado
        int menor = 0;
        int mayor = this.data.length -1;

        while (menor <= mayor){
            int medio = menor + (mayor - menor) / 2;
            int valor = (int) this.data[medio][0];

            if (valor < target){  // si mi objetivo es mayor que mi valor entonces descarto la mitad izquierda del array
                menor = medio + 1;
            }else if(valor > target){ // si mi objetivo es menor que mi valor entonces descarto la mitad derecha del array
                mayor = medio - 1;
            }
            else{ // Y si mi objetivo no es mayor ni menor quiere que decir que ya solo tengo un elemento en el array o no encontre el valor deseado
                return medio;
            }
        }
        return -1; // se retorna -1 si el objetivo no fue encontrado
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
        main.Ordenar();
        main.imprimirVerificacion();
        
        // busqueda
        int target = 5;
        int index = main.buscar(target);
        if (index == -1){
            System.out.println("Codigo de producto no encontrado");
        }
        else {
            System.out.println("Codigo encontrado en el indice: " + index);
        }

    }
}