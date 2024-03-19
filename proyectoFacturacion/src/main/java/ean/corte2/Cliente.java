package ean.corte2;

public class Cliente {
    private int cedula;
    private String nombre;
    private double telefono;
    public Cliente(int cedula, String nombre){ 
        // cliente id -> Cedula
        this.cedula = cedula;
        this.nombre = nombre;
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

}
