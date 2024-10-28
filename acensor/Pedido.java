package acensor;

import java.util.concurrent.ArrayBlockingQueue;

public class Pedido {

    // Cola para almacenar personas, con capacidad máxima
    private final ArrayBlockingQueue<Persona> cola;

    // Capacidad máxima de la cola (en este caso, 1000 personas)
    private final int MAX_COLA = 1000;

    // Constructor de la clase Pedido
    public Pedido() {
        // Inicializa la cola con una capacidad máxima de 1000 personas
        cola = new ArrayBlockingQueue<Persona>(MAX_COLA);
    }

    // Método para añadir una persona a la cola
    public void set(Persona persona) throws InterruptedException {
        // Añade una persona a la cola; si la cola está llena, espera hasta que haya
        // espacio disponible
        cola.put(persona);
    }

    // Método para recuperar una persona de la cola
    public Persona get() throws InterruptedException {
        // Extrae y retorna una persona de la cola; si está vacía, espera hasta que haya
        // una disponible
        return cola.take();
    }

    // Método que devuelve el número de personas en la cola
    public int size() {
        // Retorna el tamaño actual de la cola (cuántas personas están esperando)
        return cola.size();
    }
}
