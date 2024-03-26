package ean.corte2;

import java.util.Arrays;

public class Cliente {
    private int cedula;
    private String nombre;
    private Factura[] facturasCliente;
    public Cliente(int cedula, String nombre){ 
        // cliente id -> Cedula
        this.cedula = cedula;
        this.nombre = nombre;
        this.facturasCliente = new Factura[0];
    }
    public int getCedula() {
        return cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void añadirFactura(Factura factura){
        Factura[] arraycopy = Arrays.copyOf(this.facturasCliente, this.facturasCliente.length +1);
        arraycopy[arraycopy.length-1] = factura;
        this.facturasCliente = arraycopy;
    }
}
