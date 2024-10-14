package ej2;

public class Documento {
    private final int id;
    private final int hojas; // Número de hojas en el documento

    public Documento(int id, int hojas) {
        this.id = id;
        this.hojas = hojas;
    }

    public int getHojas() {
        return hojas;
    }

    @Override
    public String toString() {
        return "Documento{" + "id=" + id + ", hojas=" + hojas + '}';
    }

    public int getId() {
        return id;
    }
}
