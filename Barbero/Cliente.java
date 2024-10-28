package Barbero;

public class Cliente implements Runnable {
    private String nombre;
    private Barberia barberia;

    public Cliente(String nombre, Barberia barberia) {
        this.barberia = barberia;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 10000)); // Simula la llegada de los clientes en diferentes momentos
            barberia.addCliente(this); // Cliente llega a la barbería
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
