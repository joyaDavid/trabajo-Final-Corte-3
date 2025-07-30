package view;

import controller.ControllerPersona;
import dao.MascotaDAO;
import dto.MascotaDTO;
import dto.PersonaDTO;
import exception.DatoInvalidoException;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormPersona extends JFrame {

    private final ControllerPersona controller = new ControllerPersona();
    private final MascotaDAO mascotaDAO = new MascotaDAO();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    private JTextField txtNombre;
    private JTextField txtCedula;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JComboBox<String> comboMascotas;
    private JList<String> listaPersonas;
    private JButton btnGuardar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public FormPersona() {
        setTitle("📇 Registro de Personas");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));
        add(crearFormulario(), BorderLayout.WEST);
        add(crearLista(), BorderLayout.CENTER);

        actualizarLista();
        llenarComboMascotas();

        listaPersonas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarSeleccionada();
            }
        });
    }

    private JPanel crearFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("📝 Datos de Persona"));
        panel.setPreferredSize(new Dimension(300, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField();
        txtCedula = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();
        comboMascotas = new JComboBox<>();

        // Fila 1: Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridy++;
        panel.add(txtNombre, gbc);

        // Fila 2: Cédula
        gbc.gridy++;
        panel.add(new JLabel("Cédula:"), gbc);
        gbc.gridy++;
        panel.add(txtCedula, gbc);

        // Fila 3: Teléfono
        gbc.gridy++;
        panel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridy++;
        panel.add(txtTelefono, gbc);

        // Fila 4: Correo
        gbc.gridy++;
        panel.add(new JLabel("Correo:"), gbc);
        gbc.gridy++;
        panel.add(txtCorreo, gbc);

        // Fila 5: Mascota
        gbc.gridy++;
        panel.add(new JLabel("Mascota:"), gbc);
        gbc.gridy++;
        panel.add(comboMascotas, gbc);

        // Fila 6: Botonera
        gbc.gridy++;
        JPanel botones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("💾 Guardar");
        btnEliminar = new JButton("🗑️ Eliminar");
        btnLimpiar = new JButton("🔄 Limpiar");

        btnGuardar.addActionListener(e -> guardar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());

        botones.add(btnGuardar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);
        panel.add(botones, gbc);

        return panel;
    }

    private JScrollPane crearLista() {
        listaPersonas = new JList<>(listModel);
        listaPersonas.setBorder(BorderFactory.createTitledBorder("👥 Personas registradas"));
        return new JScrollPane(listaPersonas);
    }

    private void guardar() {
        String nombre = txtNombre.getText().trim();
        String cedula = txtCedula.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❗ Todos los campos son obligatorios");
            return;
        }

        try {
            PersonaDTO persona = new PersonaDTO(nombre, cedula, telefono, correo);

            String seleccionMascota = (String) comboMascotas.getSelectedItem();
            if (seleccionMascota != null && !seleccionMascota.isBlank()) {
                MascotaDTO mascota = mascotaDAO.buscarPorNombre(seleccionMascota.split(" - ")[0]);
                persona.setMascota(mascota);
            }

            controller.registrarPersona(persona);
            actualizarLista();
            limpiar();
            JOptionPane.showMessageDialog(this, "✅ Persona guardada exitosamente");
        } catch (DatoInvalidoException e) {
            JOptionPane.showMessageDialog(this, "❌ " + e.getMessage());
        }
    }

    private void eliminar() {
        int index = listaPersonas.getSelectedIndex();
        if (index != -1) {
            String seleccion = listModel.get(index);
            String cedula = seleccion.substring(seleccion.indexOf('(') + 1, seleccion.indexOf(')'));
            controller.eliminarPersona(cedula);
            actualizarLista();
            limpiar();
        }
    }

    private void cargarSeleccionada() {
        int index = listaPersonas.getSelectedIndex();
        if (index != -1) {
            String seleccion = listModel.get(index);
            String cedula = seleccion.substring(seleccion.indexOf('(') + 1, seleccion.indexOf(')'));
            PersonaDTO persona = controller.buscarPorCedula(cedula);
            if (persona != null) {
                txtNombre.setText(persona.getNombre());
                txtCedula.setText(persona.getCedula());
                txtTelefono.setText(persona.getTelefono());
                txtCorreo.setText(persona.getCorreo());

                MascotaDTO mascota = persona.getMascota();
                if (mascota != null) {
                    comboMascotas.setSelectedItem(mascota.getNombre() + " - " + mascota.getEspecie());
                } else {
                    comboMascotas.setSelectedIndex(-1);
                }
            }
        }
    }

    private void actualizarLista() {
        listModel.clear();
        List<PersonaDTO> personas = controller.obtenerPersonas();
        for (PersonaDTO p : personas) {
            listModel.addElement(p.getNombre() + " (" + p.getCedula() + ")");
        }
    }

    private void llenarComboMascotas() {
        comboMascotas.removeAllItems();
        List<MascotaDTO> mascotas = mascotaDAO.cargarMascotas();
        for (MascotaDTO m : mascotas) {
            comboMascotas.addItem(m.getNombre() + " - " + m.getEspecie());
        }
    }

    private void limpiar() {
        txtNombre.setText("");
        txtCedula.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        comboMascotas.setSelectedIndex(-1);
        listaPersonas.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPersona().setVisible(true));
    }
}
