package model;

/**
 * Widget interactivo para administrar notas.
 */
public class NotesWidget extends Widget
        implements Interactivo {

    private String nota;

    public NotesWidget(String id, String nombre,
                       double memoria, String nota) {

        super(id, nombre, memoria);
        this.nota = nota;
    }

    @Override
    public String getConfiguracion() {
        return "Contenido de la nota: " + nota;
    }

    @Override
    public String obtenerContenido() {
        return "Nota actual:\n" + nota;
    }

    @Override
    public String interactuar(String entrada) {

        if (entrada == null || entrada.isBlank()) {
            return "La nota no puede estar vacia.";
        }

        nota = entrada.trim();

        return "Nota actualizada correctamente.";
    }
}