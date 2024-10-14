package ej1;

public class Producto {
    private String descripcion;

    public Producto(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Producto [descripcion=" + descripcion + "]";
    }

}
