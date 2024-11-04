package traficoaereo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Pista {
    private Semaphore pista;

    public Pista() {
        pista = new Semaphore(1);
    }

    public void despegar(int id) {
        try {
            pista.acquire();
            System.out.println(id + " despegando...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
            System.out.println(id + " ha despegado");

        } catch (InterruptedException e) {
            System.out.println("Error en el despegue del avión " + id);
            e.printStackTrace();

        } finally {
            pista.release();
        }
    }
}