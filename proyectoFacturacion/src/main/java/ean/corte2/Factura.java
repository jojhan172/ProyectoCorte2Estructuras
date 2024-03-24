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

            System.out.printf("%-3s%-12s%-22s%-12s%-15s%-15s%n", "   ", "1. Codigo", "2. Nombre", "3. Cantidad",
                    "4. Valor IVA",
                    "5. Precio Unitario");
            for (int i = 0; i < this.productosFactura.length; i++) {
                Object castObsObject = this.productosFactura[i];
                Object[] cast = (Object[]) castObsObject;
                System.out.printf("%-3s%-12d%-22s%-12d%-15s%-15.2f%n", i + 1 + " ", cast[0], cast[1], cast[2], cast[3],
                        cast[4]);

            }
            for (Object fila : this.productosFactura) {

                // System.out.printf("%-12d%-22s%-12d%-15s%-15.2f%n", this.factura[0], fila[1],
                // fila[2], fila[3], fila[4]);
            }
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
