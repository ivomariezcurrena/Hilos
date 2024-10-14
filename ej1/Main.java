package ej1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Producto> buffer = new ArrayBlockingQueue<>(12); // Tamaño del buffer
        ExecutorService application = Executors.newCachedThreadPool();
        application.execute(new Productor("1", buffer));
        application.execute(new Productor("2", buffer));
        application.execute(new Productor("3", buffer));
        application.execute(new Empacadora(buffer));
    }
}
