package planta_de_tratamiento;

public class Agua {
    private int id_lote;
    private int cantidad;
    private int estado;

    public Agua(int id_lote, int cantidad, int estado) {
        this.id_lote = id_lote;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public int getId_lote() {
        return id_lote;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getEstado() {
        return estado;
    }
}
