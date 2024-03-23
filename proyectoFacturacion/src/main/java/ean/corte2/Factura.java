package ean.corte2;
import java.util.Arrays;

import ean.corte2.*;


// Debe traer los datos del cliente y mostarlos en el factura, pero solo debe ingresar la cedula del usuario.

public class Factura {
    InventarioProductos inventario;
    Cliente cliente;
    Object[] factura;
    Object[][] data;
    public Factura(InventarioProductos inventario, Cliente cliente){
        this.inventario = inventario;
        this.cliente = cliente;
        this.factura = new Object[0];
        this.data = inventario.getData();
    }

    public void añadirAFactura(int codigoProducto, int cantidadProducto){
        this.aumentarTamañoFactura();
        // datos producto:
        int index = inventario.buscar(codigoProducto);
        data[index][2] = cantidadProducto;
        this.factura[this.factura.length-1] = data[index];
        
    }

    public void imprimirFactura(){
        // Datos Clientes 
        //System.out.println("Datos cliente:\n"+
        //"Cedula"+cliente.getCedula()+
        //"Nombre"+cliente.getNombre());
        //// Titulos de la tabla
        //System.out.printf("%-12s%-22s%-12s%-15s%-15s%n", "1. Codigo", "2. Nombre", "3. Cantidad", "4. Valor IVA",
        //        "5. Precio Unitario");
        System.out.println();
        if (this.factura != null){
            
            System.out.printf("%-3s%-12s%-22s%-12s%-15s%-15s%n", "   ","1. Codigo", "2. Nombre", "3. Cantidad", "4. Valor IVA",
            "5. Precio Unitario");
            for (int i = 0; i<this.factura.length; i++){
                Object castObsObject = this.factura[i];
                Object[] cast = (Object[])castObsObject;
                System.out.printf("%-3s%-12d%-22s%-12d%-15s%-15.2f%n", i+1+" ",cast[0],cast[1], cast[2], cast[3], cast[4]);


            }
            for (Object fila : this.factura) {
                
                //System.out.printf("%-12d%-22s%-12d%-15s%-15.2f%n", this.factura[0], fila[1], fila[2], fila[3], fila[4]);
            }
        }else{
            System.out.println("Al parecer olvidaste crear una factura");
        }
    }
    
    public void aumentarTamañoFactura() { 
        /*
         * Cada que el usuario pida añadir un elemento a la factura nuetra
         * matriz aumentara en uno
         */
        
        // copiar nuestros elementos a un nuevo array con un espacio libre al final
        Object[] nuevaFactura =  Arrays.copyOf(this.factura, this.factura.length +1);
        
        // remplazamos el array original por el que tiene el espacio vacio al final.
        this.factura = nuevaFactura;
        
        return;
    }


}

