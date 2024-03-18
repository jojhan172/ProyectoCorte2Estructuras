package ean.corte2;

public class Cliente {
    private int cedula;
    private String nombre;
    private int telefono;
    public Cliente(int cedula, String nombre, int telefono){ 
        // cliente id -> Cedula
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    public int getCedula() {
        return cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
