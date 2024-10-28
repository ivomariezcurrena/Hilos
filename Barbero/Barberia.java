package Barbero;

import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

public class Barberia {
    private final int SILLAS_DE_ESPERA = 10;
    ArrayBlockingQueue<Cliente> clientes = new ArrayBlockingQueue<>(SILLAS_DE_ESPERA);

    public Barberia() {
        // ArrayBlockingQueue ya se inicializa en la declaración
    }

    // Método para cortar el pelo de un cliente (barbero lo ejecuta)
    public void cortarPelo() throws InterruptedException {
        Cliente cliente = clientes.take(); // Bloquea hasta que haya un cliente
        System.out.println("Barbero cortando el pelo de " + cliente.getNombre());
        Thread.sleep(2000); // Simular el tiempo de corte de cabello
        System.out.println("Barbero terminó con " + cliente.getNombre());
    }

    // Método para agregar un cliente (clientes lo ejecutan)
    public void addCliente(Cliente cliente) throws InterruptedException {
        if (clientes.offer(cliente)) { // Agrega si hay espacio en las sillas
            System.out.println("Cliente " + cliente.getNombre() + " se sienta a esperar.");
        } else {
            System.out.println("Barbería llena. El cliente " + cliente.getNombre() + " se va.");
        }
    }
}
