package view;

import javax.swing.*;
import java.awt.*;
import view.FormConsulta;
import view.FormMascota;
import view.FormPersona;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Gestión de Mascotas");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JButton btnMascota = new JButton("Gestionar Mascotas");
        JButton btnPersona = new JButton("Gestionar Personas");
        JButton btnConsulta = new JButton("Gestionar Consultas");
        JButton btnSalir = new JButton("Salir");

        // Acciones de los botones
        btnMascota.addActionListener(e -> new FormMascota().setVisible(true));
        btnPersona.addActionListener(e -> new FormPersona().setVisible(true));
        btnConsulta.addActionListener(e -> new FormConsulta().setVisible(true));
        btnSalir.addActionListener(e -> System.exit(0));

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        panel.add(btnMascota);
        panel.add(btnPersona);
        panel.add(btnConsulta);
        panel.add(btnSalir);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}

