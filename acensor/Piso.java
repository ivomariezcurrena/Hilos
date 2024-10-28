package acensor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Piso {
    // Número del piso
    private int numero;

    // Cola para almacenar personas que esperan el ascensor
    private final ArrayBlockingQueue<Persona> cola;

    // Tiempo máximo que el método get() esperará para recuperar una persona de la
    // cola
    private final int MAX_TIEMPO = 1000; // en milisegundos

    // Capacidad máxima de la cola
    private final int MAX_COLA = 100;

    // Constructor que inicializa el piso con su número y crea la cola con capacidad
    // máxima
    public Piso(int numero) {
        this.numero = numero; // Asigna el número del piso
        cola = new ArrayBlockingQueue<Persona>(MAX_COLA); // Inicializa la cola con un tamaño máximo
    }

    // Método para añadir una persona a la cola
    public void set(Persona persona) throws InterruptedException {
        cola.put(persona); // Añade la persona a la cola, esperando si es necesario
    }

    // Método para recuperar una persona de la cola, esperando hasta un máximo de
    // tiempo
    public Persona get() throws InterruptedException {
        return cola.poll(MAX_TIEMPO, TimeUnit.MILLISECONDS); // Intenta obtener una persona de la cola, esperando si es
                                                             // necesario
    }

    // Método que devuelve el número de personas en la cola
    public int size() {
        return cola.size(); // Retorna el tamaño actual de la cola
    }

    // Método que devuelve el número del piso
    public int getNumero() {
        return numero; // Retorna el número del piso
    }
}
