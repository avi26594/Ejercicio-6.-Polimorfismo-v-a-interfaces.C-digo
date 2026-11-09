package model;

/**
 * Widget informativo de noticias.
 * El titular es un dato de demostracion.
 */
public class RSSWidget extends Widget {

    private String titular;

    public RSSWidget(String id, String nombre,
                     double memoria, String titular) {

        super(id, nombre, memoria);
        this.titular = titular;
    }

    @Override
    public String getConfiguracion() {
        return "Titular configurado: " + titular;
    }

    @Override
    public String obtenerContenido() {
        return "Ultima noticia:\n" + titular;
    }
}