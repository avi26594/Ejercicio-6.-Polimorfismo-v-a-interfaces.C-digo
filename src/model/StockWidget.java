package model;

import java.util.Locale;

/**
 * Widget informativo de cotizaciones.
 * Utiliza valores de demostracion.
 */
public class StockWidget extends Widget {

    private String simbolo;
    private double valor;

    public StockWidget(String id, String nombre,
                       double memoria, String simbolo,
                       double valor) {

        super(id, nombre, memoria);

        if (!Double.isFinite(valor) || valor < 0) {
            throw new IllegalArgumentException(
                    "La cotizacion debe ser valida.");
        }

        this.simbolo = simbolo;
        this.valor = valor;
    }

    @Override
    public String getConfiguracion() {
        return String.format(
                Locale.US,
                "Simbolo: %s\nValor: $%.2f",
                simbolo, valor
        );
    }

    @Override
    public String obtenerContenido() {
        return String.format(
                Locale.US,
                "Accion: %s\nCotizacion: $%.2f",
                simbolo, valor
        );
    }
}