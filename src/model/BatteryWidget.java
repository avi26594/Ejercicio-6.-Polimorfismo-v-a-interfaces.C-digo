package model;

/**
 * Widget informativo del nivel de bateria.
 * Utiliza un porcentaje de demostracion.
 */
public class BatteryWidget extends Widget {

    private int nivelBateria;

    public BatteryWidget(String id, String nombre,
                         double memoria, int nivel) {

        super(id, nombre, memoria);

        if (nivel < 0 || nivel > 100) {
            throw new IllegalArgumentException(
                    "La bateria debe estar entre 0 y 100.");
        }

        this.nivelBateria = nivel;
    }

    @Override
    public String getConfiguracion() {
        return "Nivel de bateria: " + nivelBateria + "%";
    }

    @Override
    public String obtenerContenido() {
        return "Bateria del servidor:\n"
                + nivelBateria + "%";
    }
}