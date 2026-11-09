import controller.DashboardController;
import view.DashboardFrame;
import javax.swing.SwingUtilities;
/** Punto de entrada de Ejercicio 6: polimorfismo via interfaces. */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardController controller = new DashboardController();
            DashboardFrame vista = new DashboardFrame(controller);
            vista.setVisible(true);
        });
    }
}
