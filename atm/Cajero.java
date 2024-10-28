package atm;

public class Cajero {
    private int idcajero;
    boolean ocupado;
    private double monto;

    public Cajero(int idcajero, double monto) {
        this.idcajero = idcajero;
        this.ocupado = false;
        this.monto = monto;
    }

    // Método sincronizado para tomar el tenedor
    public synchronized void usar(String operacion) throws InterruptedException {
        while (ocupado) {
            wait();
        }
        ocupado = true; // Aseguramos que esté reservado antes de realizar la operación.
        if (operacion.startsWith("E")) {
            double cantidad = Math.random() * 1000;
            if (monto >= cantidad) {
                System.out.println("Cliente sacando dinero...");
                monto -= cantidad;
            } else {
                System.out.println("No hay suficiente dinero para sacar");
            }
        } else {
            System.out.println("Depositando dinero...");
            monto += Math.random() * 1000;
        }
    }

    // Método sincronizado para soltar el tenedor
    public synchronized void irse() {
        ocupado = false;
        notifyAll();
    }
}
