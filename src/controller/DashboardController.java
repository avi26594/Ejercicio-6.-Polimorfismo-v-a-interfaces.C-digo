package controller;
import model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
/** Controlador con una unica lista polimorfica. */
public class DashboardController {
    private final List<Widget> catalogoWidgets;
    public DashboardController() {
        catalogoWidgets = new ArrayList<>();
        inicializarCatalogo();
    }
    /** Carga inicial. Limpia primero para evitar duplicados. */
    public void inicializarCatalogo() {
        catalogoWidgets.clear();
        agregarWidget(new WeatherWidget("W-001", "Clima Local", 124.5, "Guatemala", 24));
        agregarWidget(new ClockWidget("W-002", "Reloj Guatemala", 45.0, "America/Guatemala"));
        agregarWidget(new TodoListWidget("W-003", "Tareas Sprint", 210.0, 5));
        agregarWidget(new AlertNotificationWidget("W-004", "Alerta Servidor", 85.2, "Critico"));
        agregarWidget(new SystemMonitorWidget("W-005", "Monitor CPU", 320.4, 68.5));
        agregarWidget(new NotesWidget("W-006", "Notas Reunion", 95.0, "Revisar arquitectura MVC"));
        agregarWidget(new CalendarWidget("W-007", "Calendario", 150.0));
        agregarWidget(new StockWidget("W-008", "Acciones Tech", 180.2, "TECH", 145.50));
        agregarWidget(new RSSWidget("W-009", "Noticias TI", 110.8, "Novedades de Java"));
        agregarWidget(new BatteryWidget("W-010", "Bateria UPS", 60.0, 92));
    }
    /** Registra cualquier subclase de Widget y evita IDs repetidos. */
    public void agregarWidget(Widget widget) {
        if (widget == null) throw new IllegalArgumentException("El widget no puede ser nulo.");
        if (buscarWidget(widget.getId()) != null) throw new IllegalArgumentException("Ya existe un widget con ese ID.");
        catalogoWidgets.add(widget);
    }
    /** Vista no modificable de la misma lista; no crea otra lista. */
    public List<Widget> getCatalogoWidgets() { return Collections.unmodifiableList(catalogoWidgets); }
    /** Overloading 1: busqueda exacta por ID. */
    public Widget buscarWidget(String id) {
        if (id == null || id.isBlank()) return null;
        for (Widget widget : catalogoWidgets) {
            if (widget.getId().equalsIgnoreCase(id.trim())) return widget;
        }
        return null;
    }
    /** Overloading 2: busqueda por tipo; retorna primera coincidencia. */
    public Widget buscarWidget(String texto, TipoBusqueda tipo) {
        if (texto == null || texto.isBlank() || tipo == null) return null;
        if (tipo == TipoBusqueda.ID) return buscarWidget(texto);
        String consulta = texto.trim().toLowerCase(Locale.ROOT);
        for (Widget widget : catalogoWidgets) {
            if (widget.getNombre().toLowerCase(Locale.ROOT).contains(consulta)) return widget;
        }
        return null;
    }
    /** Usa Comparable de Widget para ordenar por memoria. */
    public void ordenarPorMemoria() { Collections.sort(catalogoWidgets); }
    /** Polimorfismo por interfaz, sin depender del widget concreto. */
    public String ejecutarInteraccion(Interactivo widget, String entrada) {
        if (widget == null) return "Widget interactivo no valido.";
        return widget.interactuar(entrada);
    }
}
