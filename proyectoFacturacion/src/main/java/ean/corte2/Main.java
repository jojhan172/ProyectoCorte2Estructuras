package ean.corte2;
import ean.corte2.*;

// workin' in
public class Main {
    public static void main(String[] args) {
        InventarioProductos inventario = new InventarioProductos(20);
        inventario.cargar();
        inventario.ordenar();
        inventario.imprimirVerificacion();
    }
}
