package model;

/**
 * Widget interactivo de notificaciones.
 */
public class AlertNotificationWidget
        extends Widget implements Interactivo {

    private String nivelAlerta;

    public AlertNotificationWidget(
            String id, String nombre,
            double memoria, String nivel) {

        super(id, nombre, memoria);
        this.nivelAlerta = nivel;
    }

    @Override
    public String getConfiguracion() {
        return "Nivel de alerta: " + nivelAlerta;
    }

    @Override
    public String obtenerContenido() {
        return "Estado del sistema: " + nivelAlerta;
    }

    @Override
    public String interactuar(String entrada) {

        if ("Normal".equalsIgnoreCase(nivelAlerta)) {
            return "No existen alertas pendientes.";
        }

        String alertaAnterior = nivelAlerta;
        nivelAlerta = "Normal";

        return "Alerta '" + alertaAnterior
                + "' reconocida correctamente.";
    }
}