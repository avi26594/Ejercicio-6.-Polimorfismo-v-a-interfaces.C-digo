package model;

/**
 * Widget informativo del clima.
 * Utiliza datos de demostracion.
 */
public class WeatherWidget extends Widget {

    private String ciudad;
    private int temperatura;

    public WeatherWidget(String id, String nombre,
                         double memoria, String ciudad,
                         int temperatura) {

        super(id, nombre, memoria);
        this.ciudad = ciudad;
        this.temperatura = temperatura;
    }

    @Override
    public String getConfiguracion() {
        return "Ciudad: " + ciudad
                + "\nTemperatura: " + temperatura + " °C";
    }

    @Override
    public String obtenerContenido() {
        return "Clima en " + ciudad
                + "\nTemperatura: " + temperatura + " °C";
    }
}