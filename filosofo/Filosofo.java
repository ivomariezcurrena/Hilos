package filosofo;

import java.util.Random;

public class Filosofo implements Runnable {
    private String nombre;
    private Tenedor izquierdo;
    private Tenedor derecho;
    private Random random = new Random();
    private int platosTerminados = 0;

    public Filosofo(String nombre, Tenedor izquierdo, Tenedor derecho) {
        this.nombre = nombre;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    @Override
    public void run() {
        try {
            while (platosTerminados < 15) {
                pensar();
                comer();
            }
            System.out.println(nombre + " ha terminado de comer.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println(nombre + " está pensando.");
        Thread.sleep(random.nextInt(5000));
    }

    private void comer() throws InterruptedException {
        // Tomar los tenedores
        izquierdo.tomar();
        derecho.tomar();

        System.out.println(nombre + " comienza a comer.");
        Thread.sleep(random.nextInt(5000));
        platosTerminados++;
        System.out.println(nombre + " ha terminado de comer. Comidas terminadas: " + platosTerminados);

        // Soltar los tenedores
        izquierdo.soltar();
        derecho.soltar();
    }
}
