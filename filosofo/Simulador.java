package filosofo;

import filosofo.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulador {
    public static void main(String[] args) {
        ExecutorService HoraDePensar = Executors.newCachedThreadPool();

        // Crear tenedores
        Tenedor izquierdo = new Tenedor();
        Tenedor derecho = new Tenedor();

        Filosofo filosofo1 = new Filosofo("Aristotles", izquierdo, derecho);
        Filosofo filosofo2 = new Filosofo("Platon", izquierdo, derecho);

        HoraDePensar.execute(filosofo1);
        HoraDePensar.execute(filosofo2);

        HoraDePensar.shutdown();
    }
}
