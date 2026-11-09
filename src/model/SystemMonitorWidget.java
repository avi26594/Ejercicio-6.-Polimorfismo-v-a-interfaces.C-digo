package model;

/**
 * Widget informativo del uso de CPU.
 * Utiliza un porcentaje de demostracion.
 */
public class SystemMonitorWidget extends Widget {

    private double usoCpu;

    public SystemMonitorWidget(
            String id, String nombre,
            double memoria, double cpu) {

        super(id, nombre, memoria);

        if (!Double.isFinite(cpu)
                || cpu < 0 || cpu > 100) {

            throw new IllegalArgumentException(
                    "El porcentaje de CPU debe estar "
                    + "entre 0 y 100.");
        }

        this.usoCpu = cpu;
    }

    @Override
    public String getConfiguracion() {
        return "Uso de CPU: " + usoCpu + "%";
    }

    @Override
    public String obtenerContenido() {
        return "Monitor del sistema\n"
                + "CPU: " + usoCpu + "%";
    }
}