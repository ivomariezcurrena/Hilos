package heladeria;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulacion {
    public static void main(String[] args) {
        simular(2, 6, 40);
    }

    public static void simular(int nroCajas, int nroAtencion, int clientes) {

        ExecutorService application = Executors.newCachedThreadPool();
        BlockingQueue<Cliente> colaCaja = new ArrayBlockingQueue<>(12);
        BlockingQueue<Cliente> colaPedido = new ArrayBlockingQueue<>(12);
        List<Caja> cajas = new ArrayList<Caja>();
        List<Atencion> atenciones = new ArrayList<Atencion>();

        application.execute(new GeneradorDeClientes(colaCaja, clientes));

        Caja c;
        for (int i = 1; i <= nroCajas; i++) {
            c = new Caja(i, colaCaja, colaPedido);
            cajas.add(c);
            application.execute(c);
        }

        Atencion a;
        for (int i = 1; i <= nroAtencion; i++) {
            a = new Atencion(i, colaPedido);
            atenciones.add(a);
            application.execute(a);
        }
        application.shutdown();

        try {
            boolean tareasTerminaron = application.awaitTermination(120, TimeUnit.SECONDS);

        } catch (InterruptedException ex) {
            System.out.println("Hubo una interrupcion mientras esperaba a que terminaran las tareas.");
        }

    }
}
