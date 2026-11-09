package model;

/**
 * Widget interactivo de tareas pendientes.
 */
public class TodoListWidget extends Widget
        implements Interactivo {

    private int tareasPendientes;

    public TodoListWidget(String id, String nombre,
                          double memoria, int tareas) {

        super(id, nombre, memoria);

        if (tareas < 0) {
            throw new IllegalArgumentException(
                    "Las tareas no pueden ser negativas.");
        }

        this.tareasPendientes = tareas;
    }

    @Override
    public String getConfiguracion() {
        return "Tareas pendientes: " + tareasPendientes;
    }

    @Override
    public String obtenerContenido() {
        return "Tareas por completar: " + tareasPendientes;
    }

    @Override
    public String interactuar(String entrada) {

        if (tareasPendientes == 0) {
            return "No hay tareas pendientes.";
        }

        tareasPendientes--;

        return "Tarea completada correctamente.\n"
                + "Tareas restantes: " + tareasPendientes;
    }
}