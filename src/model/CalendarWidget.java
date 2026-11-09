package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Widget informativo de calendario.
 */
public class CalendarWidget extends Widget {

    public CalendarWidget(String id, String nombre,
                          double memoria) {

        super(id, nombre, memoria);
    }

    @Override
    public String getConfiguracion() {
        return "Calendario del sistema";
    }

    @Override
    public String obtenerContenido() {

        String fecha = LocalDate.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
        );

        return "Fecha actual:\n" + fecha;
    }
}