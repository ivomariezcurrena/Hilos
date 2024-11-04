package heladeria;

public class Cliente {
    private int id;
    private long ingreso;
    private long salida;

    public Cliente(int id) {

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public long getIngreso() {
        return ingreso;
    }

    public void setIngreso(long ingreso) {
        this.ingreso = ingreso;
    }

    public long getSalida() {
        return salida;
    }

    public void setSalida(long salida) {
        this.salida = salida;
    }

}
