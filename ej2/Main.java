package ej2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Documento> buffer1 = new ArrayBlockingQueue<>(5); // Tamaño del buffer impresora 1
        BlockingQueue<Documento> buffer2 = new ArrayBlockingQueue<>(5); // Tamaño del buffer impresorta 2
        ExecutorService application = Executors.newCachedThreadPool();
        // Ponen documentos a imprimir
        application.execute(new Productor(buffer1, 1));
        application.execute(new Productor(buffer1, 2));
        application.execute(new Productor(buffer1, 3));
        application.execute(new Productor(buffer1, 4));

        application.execute(new Impresora(buffer1, 1));
        application.execute(new Impresora(buffer1, 2));
    }
}
