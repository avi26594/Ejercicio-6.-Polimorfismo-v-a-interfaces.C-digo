package model;

import java.util.Locale;
import java.util.Objects;

/**
 * Clase abstracta base de los widgets.
 *
 * Implementa Renderizable para mostrar
 * informacion y Comparable para ordenar
 * los widgets por consumo de memoria.
 */
public abstract class Widget
        implements Renderizable, Comparable<Widget> {

    private final String id;
    private final String nombre;
    private final double consumoMemoriaKB;

    public Widget(String id, String nombre,
                  double memoria) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "El ID es obligatorio.");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre es obligatorio.");
        }

        if (!Double.isFinite(memoria) || memoria < 0) {
            throw new IllegalArgumentException(
                    "La memoria debe ser valida.");
        }

        this.id = id.trim();
        this.nombre = nombre.trim();
        this.consumoMemoriaKB = memoria;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getConsumoMemoriaKB() {
        return consumoMemoriaKB;
    }

    public abstract String getConfiguracion();

    @Override
    public abstract String obtenerContenido();

    /**
     * Orden natural ascendente por memoria.
     * El ID se utiliza para desempatar.
     */
    @Override
    public int compareTo(Widget otro) {

        int resultado = Double.compare(
                consumoMemoriaKB,
                otro.consumoMemoriaKB
        );

        if (resultado != 0) {
            return resultado;
        }

        return id.compareToIgnoreCase(otro.id);
    }

    /**
     * Dos widgets son iguales cuando
     * tienen el mismo identificador.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Widget otro)) {
            return false;
        }

        return id.equalsIgnoreCase(otro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id.toLowerCase(Locale.ROOT)
        );
    }

    @Override
    public String toString() {

        return String.format(
                Locale.US,
                "[%s] %s - %.1f KB",
                id, nombre, consumoMemoriaKB
        );
    }
}