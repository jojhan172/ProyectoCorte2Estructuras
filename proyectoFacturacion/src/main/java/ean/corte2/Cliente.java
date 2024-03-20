package ean.corte2;

import java.util.Arrays;

public class Cliente {
    private int cedula;
    private String nombre;
    private int[] codigosFacturas;
    public Cliente(int cedula, String nombre){ 
        // cliente id -> Cedula
        this.cedula = cedula;
        this.nombre = nombre;
        this.codigosFacturas = new int[0];
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
    public void addCodigoFactura(int nuevoCodigo) {
        int[] arraycopy = Arrays.copyOf(this.codigosFacturas, this.codigosFacturas.length +1);
        arraycopy[arraycopy.length-1] = nuevoCodigo;
        this.codigosFacturas = arraycopy;
    }
    public int[] getCodigoFacturas() {
        return codigosFacturas;
    }

}
