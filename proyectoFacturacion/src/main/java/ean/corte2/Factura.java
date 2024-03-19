package ean.corte2;
import ean.corte2.*;


// Debe traer los datos del cliente y mostarlos en el factura, pero solo debe ingresar la cedula del usuario.

public class Factura {
    InventarioProductos inventario;
    Cliente cliente;
    Object[][] factura;
    Object[][] data;
    public Factura(InventarioProductos inventario, Cliente cliente){
        this.inventario = inventario;
        this.cliente = cliente;
        this.factura = new Object[0][0];
        this.data = inventario.getData();
    }

    public void añadirAFactura(int codigo){
        this.aumentarTamañoFactura();
        // datos producto:
        int index = inventario.buscar(codigo);
        Object[] fila = data[index];
        System.out.println(fila[0]);

    }

    public void imprimirFactura(){
        // Datos Clientes 
        System.out.println("Datos cliente:\n"+
        "Cedula"+cliente.getCedula()+
        "Nombre"+cliente.getNombre());
        // Titulos de la tabla
        System.out.printf("%-12s%-22s%-12s%-15s%-15s%n", "1. Codigo", "2. Nombre", "3. Cantidad", "4. Valor IVA",
                "5. Precio Unitario");
        System.out.println();
        if (this.factura != null){
            for (Object[] fila : this.factura) {
                System.out.printf("%-12d%-22s%-12d%-15s%-15.2f%n", fila[0], fila[1], fila[2], fila[3], fila[4]);
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
        int filasOriginales = this.factura.length;
        int columnas = this.factura[0].length;
        
        // Crear una nueva matriz con el tamaño aumentado
        Object[][] nuevaMatriz = new Object[filasOriginales + 1][columnas];
        
        // Copiar los elementos de la matriz original a la nueva matriz
        for (int i = 0; i < filasOriginales; i++) {
            System.arraycopy(this.factura[i], 0, nuevaMatriz[i], 0, columnas);
        }
        this.factura = nuevaMatriz;
        
        return;
    }
    public static void eliminarDeFactura(){
        
    }

}
