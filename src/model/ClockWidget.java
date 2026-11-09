package model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Widget informativo de reloj.
 * Utiliza la zona horaria configurada.
 */
public class ClockWidget extends Widget {

    private String zonaHoraria;

    public ClockWidget(String id, String nombre,
                       double memoria, String zonaHoraria) {

        super(id, nombre, memoria);

        // Verifica que la zona horaria exista.
        ZoneId.of(zonaHoraria);
        this.zonaHoraria = zonaHoraria;
    }

    @Override
    public String getConfiguracion() {
        return "Zona horaria: " + zonaHoraria;
    }

    @Override
    public String obtenerContenido() {

        ZonedDateTime ahora = ZonedDateTime.now(
                ZoneId.of(zonaHoraria)
        );

        String hora = ahora.format(
                DateTimeFormatter.ofPattern("HH:mm:ss")
        );

        return "Zona: " + zonaHoraria
                + "\nHora actual: " + hora;
    }
}