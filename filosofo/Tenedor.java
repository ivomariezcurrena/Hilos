package filosofo;

public class Tenedor {
    private boolean ocupado = false;

    // Método sincronizado para tomar el tenedor
    public synchronized void tomar() throws InterruptedException {
        while (ocupado) {
            wait();
        }
        ocupado = true;
    }

    // Método sincronizado para soltar el tenedor
    public synchronized void soltar() {
        ocupado = false;
        notifyAll();
    }
}
