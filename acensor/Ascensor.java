package acensor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ascensor implements Runnable {

    // Capacidad máxima de personas en el ascensor
    private static final int LUGARES = 6;

    // Tiempo que tarda el ascensor en viajar entre pisos (en milisegundos)
    private static final int TIEMPO_VIAJE = 1000; // 1 segundo por piso

    // Identificador del ascensor
    private int id;

    // Lista de pisos en el edificio
    private List<Piso> pisos;

    // Lista de personas actualmente dentro del ascensor
    private List<Persona> personas;

    // Indica si el ascensor está ocupado o no
    private boolean ocupado;

    // Indica si el ascensor está subiendo o bajando
    private boolean sube;

    // Piso actual en el que se encuentra el ascensor
    private int pisoActual;

    // Tablero de botones, donde cada posición indica si se ha solicitado un piso
    private boolean tablero[];

    // Número total de pisos en el edificio
    private int nroPisos;

    // Constructor del ascensor
    public Ascensor(int id, List<Piso> pisos) {
        this.id = id; // Asigna el ID del ascensor
        this.pisos = pisos; // Lista de pisos en el edificio
        this.personas = new ArrayList<Persona>(); // Inicializa la lista de personas dentro del ascensor
        nroPisos = pisos.size(); // Define el número total de pisos
        this.tablero = new boolean[nroPisos]; // Inicializa el tablero de botones
        ocupado = false; // Inicialmente el ascensor no está ocupado
        sube = true; // El ascensor comienza subiendo
        pisoActual = 0; // El ascensor empieza en el piso 0
    }

    // Método que ejecuta el comportamiento del ascensor (el hilo)
    public void run() {
        Persona persona;
        boolean hayPersona;
        ocupado = true; // Marca el ascensor como ocupado
        int piso;
        try {
            System.out.println("Arranca ascensor " + id); // Imprime el inicio del ascensor
            piso = pisoActual; // Inicializa el piso donde se encuentra el ascensor
            while (piso != -1) {
                Iterator<Persona> p = personas.iterator();

                // Baja personas del ascensor si su destino es el piso actual
                while (p.hasNext()) {
                    persona = p.next();
                    if (persona.getPisoDestino().getNumero() == piso) {
                        System.out.println("Ascensor " + id
                                + " Baja persona: " + persona);
                        p.remove(); // La persona sale del ascensor
                    }
                }

                // Suben personas al ascensor si hay espacio
                hayPersona = true;
                while (personas.size() < LUGARES && hayPersona) {
                    // Intenta obtener una persona que esté esperando en el piso actual
                    persona = pisos.get(piso).get();
                    if (persona == null) {
                        hayPersona = false; // No hay más personas en este piso
                    } else {
                        System.out.println("Ascensor " + id
                                + "  Sube persona: " + persona);
                        personas.add(persona); // Añade a la persona al ascensor
                        setTablero(persona.getPisoDestino().getNumero(), true); // Marca el piso de destino en el
                                                                                // tablero
                    }
                }

                // Actualiza el tablero para marcar que el ascensor ya no tiene como objetivo el
                // piso actual
                setTablero(pisoActual, false);

                // Determina el próximo piso al que debe ir el ascensor
                piso = proximoPiso();

                // Si hay un próximo piso
                if (piso != -1) {
                    // Simula el viaje del ascensor subiendo
                    if (pisoActual < piso) {
                        for (int i = pisoActual; i <= piso; i++) {
                            System.out.println("Piso: " + i);
                            Thread.sleep(TIEMPO_VIAJE); // Espera el tiempo de viaje entre pisos
                        }
                    }
                    // Simula el viaje del ascensor bajando
                    else {
                        for (int i = pisoActual; i >= piso; i--) {
                            System.out.println("Piso: " + i);
                            Thread.sleep(TIEMPO_VIAJE); // Espera el tiempo de viaje entre pisos
                        }
                    }

                    // Actualiza el piso actual del ascensor
                    pisoActual = piso;
                }
            }
            ocupado = false; // Marca el ascensor como desocupado cuando termina
            System.out.println("Para ascensor " + id); // Imprime que el ascensor se detiene
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    // Verifica si el ascensor está ocupado
    public synchronized boolean isOcupado() {
        return ocupado;
    }

    // Retorna el estado del tablero para un piso en particular
    public synchronized boolean getTablero(int piso) {
        return tablero[piso];
    }

    // Actualiza el estado del tablero para un piso en particular
    public synchronized void setTablero(int piso, boolean estado) {
        tablero[piso] = estado;
    }

    // Determina cuál es el próximo piso al que debe ir el ascensor
    private int proximoPiso() {
        // Si el ascensor está subiendo
        if (sube) {
            // Busca el próximo piso solicitado mientras sube
            for (int i = pisoActual; i < nroPisos; i++) {
                if (getTablero(i)) {
                    sube = true;
                    return i; // Devuelve el siguiente piso solicitado
                }
            }
            // Si no hay más pisos para subir, cambia a bajar
            for (int i = pisoActual; i >= 0; i--) {
                if (getTablero(i)) {
                    sube = false;
                    return i; // Devuelve el siguiente piso solicitado al bajar
                }
            }
        }
        // Si el ascensor está bajando
        else {
            // Busca el próximo piso solicitado mientras baja
            for (int i = pisoActual; i >= 0; i--) {
                if (getTablero(i)) {
                    sube = false;
                    return i; // Devuelve el siguiente piso solicitado
                }
            }
            // Si no hay más pisos para bajar, cambia a subir
            for (int i = pisoActual; i < nroPisos; i++) {
                if (getTablero(i)) {
                    sube = true;
                    return i; // Devuelve el siguiente piso solicitado al subir
                }
            }
        }
        // Si no hay más pisos solicitados, devuelve -1
        return -1;
    }
}
