package ean.corte2;

import java.util.Arrays;
import java.util.Scanner;

import ean.corte2.*;

// Debe traer los datos del cliente y mostarlos en el factura, pero solo debe ingresar la cedula del usuario.

public class Factura {
    InventarioProductos inventario;
    Cliente cliente;
    Object[] productosFactura;
    Object[][] data;

    public Factura(InventarioProductos inventario, Cliente cliente) {
        this.inventario = inventario;
        this.cliente = cliente;
        this.productosFactura = new Object[0];
        this.data = inventario.getData();
    }

    public void añadirAFactura(int codigoProducto, int cantidadProducto) {
        this.aumentarTamañoFactura();
        // datos producto:
        inventario.ordenarMenorMayor();
        int index = inventario.buscar(codigoProducto);
        data[index][2] = cantidadProducto;
        this.productosFactura[this.productosFactura.length - 1] = data[index];
    }

    public void imprimirFactura() {
        // Datos Clientes
        // System.out.println("Datos cliente:\n"+
        // "Cedula"+cliente.getCedula()+
        // "Nombre"+cliente.getNombre());
        //// Titulos de la tabla
        // System.out.printf("%-12s%-22s%-12s%-15s%-15s%n", "1. Codigo", "2. Nombre",
        // "3. Cantidad", "4. Valor IVA",
        // "5. Precio Unitario");
        System.out.println();
        if (this.productosFactura != null) {
            this.calcularValorIVAProductos();
            this.calcularSubtotalProductos();
            this.calcularTotalProducto();

            System.out.printf("%-3s%-12s%-22s%-12s%-10s%-20s%-17s%-15s%-15s%n", "   ", "1. Codigo", "2. Nombre", "3. Cantidad", "4. % IVA", "5. Precio Unitario", "6. IVA unitario", "7. Subtotal", "8. Total Prod.");
            for (int i = 0; i < this.productosFactura.length; i++) {
                Object castObsObject = this.productosFactura[i];
                Object[] cast = (Object[]) castObsObject;
                System.out.printf("%-3s%-12d%-22s%-12d%-10d%-20.2f%-17.2f%-15.2f%-15.2f%n", i + 1 + " ", cast[0], cast[1], cast[2], cast[3],
                        cast[4], cast[5], cast[6],cast[7]);

            }
        float totalFactura = calcularTotalFactura();
        float subtotalFactura = calcularSubtotalFactura(); 
        System.out.println("\nEl subtotal de tu factura es: "+ subtotalFactura);
        System.out.println("\nEl valor total de la factura es: " + totalFactura);
        } else {
            System.out.println("Al parecer olvidaste crear una factura");
        }
    }

    // metodo para que el cliente modifique la cantidad de elementos que desea
    // del producto de su elección
    public void editarProductoFactura(int indexProducto) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        if (this.productosFactura != null) {
            Object castObject = this.productosFactura[indexProducto - 1];
            Object[] cast = (Object[]) castObject;
            int userEntry = sc.nextInt();
            cast[2] = userEntry;
            this.productosFactura[indexProducto -1] = cast;

        } else {
            System.out.println("Al parecer no hay ningun producto en tu factura.");
        }
        System.out.println("Producto editado correctamente");
    }
    
    // subtotal,valor IVA y totalProducto
    public void calcularValorIVAProductos(){
        
         // valor de IVA por cada unidad de producto 

        for (int i = 0; i < this.productosFactura.length; i++) {

            // obtenemos el array completo del producto
            Object castObsObject = this.productosFactura[i];
            Object[] cast = (Object[]) castObsObject;

            // calculamos el valor del  IVA por unidad
            float precioUnitarioProducto = (float) cast[4];
            int ivaEntero = (int) cast[3];
            
            float ivaPorcentual = (float) ivaEntero / 100;  

            float ivaFinal = (float) (precioUnitarioProducto * ivaPorcentual);
            
            //  editamos el array que habiamos obtenido añadiendole el valor calculado
            cast[5] = ivaFinal;

            // finalmente remplazamos el array original por el array editado con el array calculado
            this.productosFactura[i] = cast;
        }
         
       
        
        
    }
    
    public void calcularSubtotalProductos(){ // precio producto sin IVA
        for (int i = 0; i < this.productosFactura.length; i++) {
            // obtenemos el array completo del producto de la facturas
            Object castObsObject = this.productosFactura[i];
            Object[] cast = (Object[]) castObsObject;

            // calculamos el valor
            float precioUnitarioProducto = (float) cast[4];
            int cantidad = (int) cast[2];
            float subtotal = cantidad * precioUnitarioProducto;
            // remplazamos en nuestro objeto
            cast[6] = subtotal;
            this.productosFactura[i] = cast;
        }
        
    }


    public void calcularTotalProducto(){
        for (int i = 0; i < this.productosFactura.length; i++) {

            // obtenemos el array completo del producto
            Object castObsObject = this.productosFactura[i];
            Object[] cast = (Object[]) castObsObject;

            // calculamos el valor del  IVA por unidad
            int cantidadProducto = (int) cast[2];
            float subtotal = (float) cast[6];
            float valorIva = (float) cast[5] * cantidadProducto;
            
            float totalProducto = valorIva + subtotal;  

            //  editamos el array que habiamos obtenido añadiendole el valor calculado
            cast[7] = totalProducto;

            // finalmente remplazamos el array original por el array editado con el array calculado
            this.productosFactura[i] = cast;
        }
        return;
    }

    public float calcularTotalFactura(){
        float totalFactura = 0;
        for (int i = 0; i < this.productosFactura.length; i++) {

            // obtenemos el array completo del producto
            Object castObsObject = this.productosFactura[i];
            Object[] cast = (Object[]) castObsObject;

            // calculamos el valor del  IVA por unidad
            totalFactura += (float) cast[7];
        }
        return totalFactura;
    }

    public float calcularSubtotalFactura(){
        float subtotalFactura = 0;
        for (int i = 0; i < this.productosFactura.length; i++) {

            // obtenemos el array completo del producto
            Object castObsObject = this.productosFactura[i];
            Object[] cast = (Object[]) castObsObject;

            // calculamos el valor del  IVA por unidad
            subtotalFactura += (float) cast[6];
        }
        return subtotalFactura;
        
    }

    public void aumentarTamañoFactura() {
        /*
         * Cada que el usuario pida añadir un elemento a la factura nuetra
         * matriz aumentara en uno
         */

        // copiar nuestros elementos a un nuevo array con un espacio libre al final
        Object[] nuevaFactura = Arrays.copyOf(this.productosFactura, this.productosFactura.length + 1);

        // remplazamos el array original por el que tiene el espacio vacio al final.
        this.productosFactura = nuevaFactura;

        return;
    }

}
