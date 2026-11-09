
package view;

import controller.DashboardController;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.*;

/**
 * CC2008 - Programacion Orientada a Objetos
 * Ejercicio 6 - Polimorfismo via Interfaces
 *
 * Vista principal del Dashboard Modular.
 *
 * Construye la interfaz grafica utilizando
 * Java Swing y se comunica con el controlador.
 *
 * Tema visual: Morado tecnologico.
 */
public class DashboardFrame extends JFrame {

    // Colores del tema grafico.
    private static final Color COLOR_FONDO =
            Color.decode("#F3F0FA");

    private static final Color COLOR_ENCABEZADO =
            Color.decode("#38235E");

    private static final Color COLOR_TARJETA =
            Color.decode("#FFFFFF");

    private static final Color COLOR_PRINCIPAL =
            Color.decode("#7C3AED");

    private static final Color COLOR_TEXTO =
            Color.decode("#29233B");

    // Atributos de la vista.
    private final DashboardController controller;
    private JPanel panelWidgetsContainer;

    /**
     * Constructor de la ventana principal.
     */
    public DashboardFrame(
            DashboardController controller) {

        if (controller == null) {
            throw new IllegalArgumentException(
                    "El controlador es obligatorio.");
        }

        this.controller = controller;

        setTitle("Dashboard Modular Empresarial");
        setSize(1050, 720);
        setMinimumSize(new Dimension(750, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(COLOR_FONDO);

        initComponents();
        refrescarPanelesWidgets();
    }

    /**
     * Construye los componentes de la ventana.
     */
    private void initComponents() {

        setLayout(new BorderLayout(10, 10));

        // Encabezado principal.
        JPanel encabezado = new JPanel(
                new BorderLayout(10, 10)
        );

        encabezado.setBackground(COLOR_ENCABEZADO);

        encabezado.setBorder(
                new EmptyBorder(15, 15, 10, 15)
        );

        JLabel titulo = new JLabel(
                "Dashboard Modular Empresarial"
        );

        titulo.setFont(
                new Font("SansSerif", Font.BOLD, 23)
        );

        titulo.setForeground(Color.WHITE);

        encabezado.add(titulo, BorderLayout.NORTH);

        // Menu de operaciones.
        JPanel menu = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 8)
        );

        menu.setBackground(COLOR_ENCABEZADO);

        JButton btnListar =
                new JButton("Listar todos");

        JButton btnBuscar =
                new JButton("Buscar widget");

        JButton btnOrdenar =
                new JButton("Ordenar por memoria");

        JButton btnInteractuar =
                new JButton("Interactuar por ID");

        // Estilo de los botones del menu.
        JButton[] botones = {
                btnListar,
                btnBuscar,
                btnOrdenar,
                btnInteractuar
        };

        for (JButton boton : botones) {

            boton.setBackground(COLOR_PRINCIPAL);
            boton.setForeground(Color.WHITE);

            boton.setFont(
                    new Font("SansSerif", Font.BOLD, 12)
            );

            boton.setFocusPainted(false);
            boton.setOpaque(true);
            boton.setBorderPainted(false);

            boton.setMargin(
                    new Insets(10, 15, 10, 15)
            );

            boton.setCursor(
                    new Cursor(Cursor.HAND_CURSOR)
            );
        }

        menu.add(btnListar);
        menu.add(btnBuscar);
        menu.add(btnOrdenar);
        menu.add(btnInteractuar);

        encabezado.add(menu, BorderLayout.CENTER);

        add(encabezado, BorderLayout.NORTH);

        // Contenedor visual de las tarjetas.
        panelWidgetsContainer = new JPanel(
                new GridLayout(0, 3, 12, 12)
        );

        panelWidgetsContainer.setBackground(COLOR_FONDO);

        panelWidgetsContainer.setBorder(
                new EmptyBorder(15, 15, 15, 15)
        );

        JScrollPane scroll = new JScrollPane(
                panelWidgetsContainer
        );

        scroll.getViewport().setBackground(COLOR_FONDO);

        scroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        add(scroll, BorderLayout.CENTER);

        // Pie de la ventana.
        JLabel pie = new JLabel(
                "  CC2008 - Programacion Orientada "
                + "a Objetos | Ejercicio 6"
        );

        pie.setBackground(COLOR_ENCABEZADO);
        pie.setForeground(Color.WHITE);
        pie.setOpaque(true);

        pie.setBorder(
                new EmptyBorder(8, 10, 8, 10)
        );

        add(pie, BorderLayout.SOUTH);

        // Evento: listar widgets.
        btnListar.addActionListener(e -> {
            refrescarPanelesWidgets();
        });

        // Evento: buscar widgets.
        btnBuscar.addActionListener(e -> {
            mostrarBusqueda();
        });

        // Evento: ordenar por memoria.
        btnOrdenar.addActionListener(e -> {

            controller.ordenarPorMemoria();

            refrescarPanelesWidgets();

            JOptionPane.showMessageDialog(
                    this,
                    "Widgets ordenados por memoria "
                    + "de menor a mayor."
            );
        });

        // Evento: interactuar mediante ID.
        btnInteractuar.addActionListener(e -> {

            String id = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el ID del widget:"
            );

            if (id == null || id.isBlank()) {
                return;
            }

            Widget widget =
                    controller.buscarWidget(id.trim());

            if (widget == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No se encontro ningun widget "
                        + "con ese identificador."
                );

                return;
            }

            mostrarInteraccion(widget);
        });

        /*
         * El temporizador actualiza la vista
         * cada segundo para mostrar la hora actual.
         *
         * No modifica la lista polimorfica.
         */
        Timer temporizador = new Timer(
                1000,
                e -> refrescarPanelesWidgets()
        );

        temporizador.start();
    }

    /**
     * Construye una tarjeta mediante Renderizable.
     *
     * Utiliza polimorfismo para obtener el
     * contenido sin conocer la clase concreta.
     */
    private JPanel crearTarjeta(
            Renderizable widget) {

        Widget datos = (Widget) widget;

        JPanel tarjeta = new JPanel(
                new BorderLayout(8, 8)
        );

        tarjeta.setPreferredSize(
                new Dimension(280, 170)
        );

        tarjeta.setBackground(COLOR_TARJETA);

        tarjeta.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 210, 235),
                                1
                        ),
                        new EmptyBorder(12, 12, 12, 12)
                )
        );

        // Nombre del widget.
        JLabel nombre = new JLabel(
                datos.getNombre()
        );

        nombre.setFont(
                new Font("SansSerif", Font.BOLD, 15)
        );

        nombre.setForeground(COLOR_ENCABEZADO);

        tarjeta.add(nombre, BorderLayout.NORTH);

        // Contenido polimorfico.
        JTextArea contenido = new JTextArea(
                widget.obtenerContenido()
        );

        contenido.setEditable(false);
        contenido.setFocusable(false);
        contenido.setOpaque(false);
        contenido.setLineWrap(true);
        contenido.setWrapStyleWord(true);

        contenido.setForeground(COLOR_TEXTO);

        contenido.setFont(
                new Font("SansSerif", Font.PLAIN, 12)
        );

        tarjeta.add(contenido, BorderLayout.CENTER);

        // Seccion inferior.
        JPanel inferior = new JPanel(
                new BorderLayout(5, 5)
        );

        inferior.setOpaque(false);

        JLabel memoria = new JLabel(
                datos.toString()
        );

        memoria.setFont(
                new Font("SansSerif", Font.PLAIN, 11)
        );

        memoria.setForeground(COLOR_TEXTO);

        inferior.add(memoria, BorderLayout.NORTH);

        /*
         * Solo se agrega el boton cuando
         * el widget implementa Interactivo.
         */
        if (datos instanceof Interactivo) {

            JButton btnAccion =
                    new JButton("Interactuar");

            btnAccion.setBackground(COLOR_PRINCIPAL);
            btnAccion.setForeground(Color.WHITE);

            btnAccion.setFont(
                    new Font("SansSerif", Font.BOLD, 12)
            );

            btnAccion.setFocusPainted(false);
            btnAccion.setOpaque(true);
            btnAccion.setBorderPainted(false);

            btnAccion.setCursor(
                    new Cursor(Cursor.HAND_CURSOR)
            );

            btnAccion.addActionListener(e -> {
                mostrarInteraccion(datos);
            });

            inferior.add(
                    btnAccion,
                    BorderLayout.SOUTH
            );
        }

        tarjeta.add(inferior, BorderLayout.SOUTH);

        return tarjeta;
    }

    /**
     * Actualiza las tarjetas del dashboard.
     *
     * Recorre la unica lista polimorfica
     * administrada por el controlador.
     */
    private void refrescarPanelesWidgets() {

        panelWidgetsContainer.removeAll();

        for (Widget widget :
                controller.getCatalogoWidgets()) {

            panelWidgetsContainer.add(
                    crearTarjeta(widget)
            );
        }

        panelWidgetsContainer.revalidate();
        panelWidgetsContainer.repaint();
    }

    /**
     * Permite buscar un widget por ID o nombre.
     *
     * Utiliza los metodos sobrecargados
     * definidos en DashboardController.
     */
    private void mostrarBusqueda() {

        String[] opciones = {
                "Buscar por ID",
                "Buscar por nombre"
        };

        String opcion = (String)
                JOptionPane.showInputDialog(
                        this,
                        "Seleccione el tipo de busqueda:",
                        "Buscar widget",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

        if (opcion == null) {
            return;
        }

        String texto = JOptionPane.showInputDialog(
                this,
                "Ingrese el ID o nombre del widget:"
        );

        if (texto == null || texto.isBlank()) {
            return;
        }

        Widget encontrado;

        if (opcion.equals("Buscar por ID")) {

            // Primera version del metodo.
            encontrado = controller.buscarWidget(
                    texto.trim()
            );

        } else {

            // Segunda version del metodo.
            encontrado = controller.buscarWidget(
                    texto.trim(),
                    TipoBusqueda.NOMBRE
            );
        }

        if (encontrado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontro ningun widget "
                    + "con ese criterio de busqueda."
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                encontrado.toString()
                + "\n\n"
                + encontrado.getConfiguracion(),
                "Configuracion del widget",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Ejecuta acciones solamente cuando
     * el widget implementa Interactivo.
     */
    private void mostrarInteraccion(Widget widget) {

        if (!(widget instanceof Interactivo interactivo)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Este widget es informativo "
                    + "y no admite interacciones."
            );

            return;
        }

        String entrada = "";

        /*
         * NotesWidget necesita recibir texto.
         * Las tareas y alertas no requieren
         * informacion adicional.
         */
        if (widget instanceof NotesWidget) {

            entrada = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el nuevo contenido "
                    + "de la nota:",
                    ""
            );

            // Cancelar no modifica la nota.
            if (entrada == null) {
                return;
            }
        }

        // Ejecucion mediante la interfaz.
        String resultado =
                controller.ejecutarInteraccion(
                        interactivo,
                        entrada
                );

        JOptionPane.showMessageDialog(
                this,
                resultado,
                "Resultado de la interaccion",
                JOptionPane.INFORMATION_MESSAGE
        );

        refrescarPanelesWidgets();
    }
}