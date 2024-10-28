package acensor;

import java.util.List;
import java.util.Random;

public class CrearPersona implements Runnable {

    // Tiempo fijo que debe esperar entre la creación de personas (en milisegundos)
    private static final int TIEMPO_FIJO = 5000; // 5 segundos

    // Tiempo adicional variable que se suma al tiempo fijo
    private static final int TIEMPO_VARIABLE = 5000; // hasta 5 segundos adicionales

    // Generador aleatorio utilizado para decidir los pisos y los tiempos
    private final static Random generator = new Random();

    // Número total de pisos en el edificio
    private int nroPisos;

    // Número total de personas que se deben crear
    private int totalPersonas;

    // Cola de pedidos (donde se almacenan las personas que solicitan el ascensor)
    private Pedido pedidos;

    // Lista de pisos donde se originan y tienen destino las personas
    private List<Piso> pisos;

    // Constructor que inicializa el número de pisos, personas, la cola de pedidos y
    // los pisos
    public CrearPersona(int nroPisos, int totalPersonas, Pedido pedidos, List<Piso> pisos) {
        this.nroPisos = nroPisos; // Asigna el número de pisos
        this.totalPersonas = totalPersonas; // Asigna el número total de personas a crear
        this.pedidos = pedidos; // Asigna la cola de pedidos
        this.pisos = pisos; // Asigna la lista de pisos
    }

    // Método que será ejecutado cuando el hilo comience
    public void run() {
        int pisoOrigen;
        int pisoDestino;

        // Ciclo para crear la cantidad de personas especificada por 'totalPersonas'
        for (int count = 1; count <= totalPersonas; count++) {
            try {
                // Selecciona un piso de origen aleatorio
                pisoOrigen = generator.nextInt(nroPisos);

                // Selecciona un piso de destino aleatorio, asegurándose que no sea igual al
                // origen
                pisoDestino = generator.nextInt(nroPisos);
                while (pisoOrigen == pisoDestino) {
                    pisoDestino = generator.nextInt(nroPisos);
                }

                // Imprime información sobre la persona creada (su origen y destino)
                System.out.println(
                        "Persona: " + count + " está en el piso: " + pisoOrigen + " y va al piso: " + pisoDestino);

                // Crea una nueva persona con el número 'count', el piso de origen y el de
                // destino, y la añade a la cola de pedidos
                pedidos.set(new Persona(count, pisos.get(pisoOrigen), pisos.get(pisoDestino)));

                // Hace que el hilo se detenga por un tiempo aleatorio entre el tiempo fijo y
                // variable
                Thread.sleep(generator.nextInt(TIEMPO_VARIABLE) + TIEMPO_FIJO);

            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
