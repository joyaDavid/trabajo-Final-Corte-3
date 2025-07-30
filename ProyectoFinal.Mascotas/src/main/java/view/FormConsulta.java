package view;

import controller.ControllerConsulta;
import dao.ConsultaDAO;
import dao.PersonaDAO;
import dao.MascotaDAO;
import dto.ConsultaDTO;
import dto.PersonaDTO;
import dto.MascotaDTO;
import exception.DatoInvalidoException;
import model.Veterinario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class FormConsulta extends JFrame {

    private JComboBox<String> comboPropietario;
    private JComboBox<String> comboMascota;
    private JTextField txtVeterinario;
    private JTextField txtFecha;
    private JTextField txtCodigo;
    private JTextArea txtDescripcion;
    private JButton btnGuardar, btnEditar, btnEliminar;
    private JTable tablaConsultas;
    private DefaultTableModel modeloTabla;
    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    private final PersonaDAO personaDAO = new PersonaDAO();
    private final MascotaDAO mascotaDAO = new MascotaDAO();
    private final ControllerConsulta controller = new ControllerConsulta();

    public FormConsulta() {
        setTitle("📋 Registro de Consultas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        initComponents();  // Inicializa el formulario
        cargarTabla();      // Carga las consultas registradas en la tabla
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Nueva Consulta"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Etiquetas
        JLabel lblCodigo = new JLabel("Código:");
        JLabel lblPropietario = new JLabel("Propietario:");
        JLabel lblMascota = new JLabel("Mascota:");
        JLabel lblVeterinario = new JLabel("Veterinario:");
        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD:");
        JLabel lblDescripcion = new JLabel("Descripción:");

        // Campos del formulario
        comboPropietario = new JComboBox<>();
        comboMascota = new JComboBox<>();
        txtVeterinario = new JTextField(16);
        txtFecha = new JTextField(14);
        txtDescripcion = new JTextArea(4, 20);
        txtCodigo = new JTextField(16);
        txtCodigo.setEditable(false); // Código generado automáticamente

        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);

        // Botones
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarConsulta());

        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> editarConsulta());

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarTabla());

        llenarCombos(); // Llena los combos con propietarios y mascotas

        // Agregar componentes al panel
        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblCodigo, gbc);
        gbc.gridx = 1; panel.add(txtCodigo, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(lblPropietario, gbc);
        gbc.gridx = 1; panel.add(comboPropietario, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(lblMascota, gbc);
        gbc.gridx = 1; panel.add(comboMascota, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(lblVeterinario, gbc);
        gbc.gridx = 1; panel.add(txtVeterinario, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panel.add(lblFecha, gbc);
        gbc.gridx = 1; panel.add(txtFecha, gbc);

        gbc.gridx = 0; gbc.gridy = 5; panel.add(lblDescripcion, gbc);
        gbc.gridx = 1; panel.add(scrollDescripcion, gbc);

        gbc.gridx = 0; gbc.gridy = 6; panel.add(btnGuardar, gbc);
        gbc.gridx = 1; panel.add(btnEditar, gbc);
        gbc.gridx = 2; panel.add(btnEliminar, gbc);

        add(panel, BorderLayout.NORTH);

        // Crear la tabla y su modelo
        modeloTabla = new DefaultTableModel(new String[]{"Código", "Fecha", "Veterinario", "Propietario", "Mascota", "Descripción"}, 0);
        tablaConsultas = new JTable(modeloTabla);

        // Cargar datos en los campos al seleccionar una fila
        tablaConsultas.getSelectionModel().addListSelectionListener(e -> cargarCamposDesdeTabla());
        add(new JScrollPane(tablaConsultas), BorderLayout.CENTER);
    }

    private void llenarCombos() {
        comboPropietario.removeAllItems();
        for (PersonaDTO persona : personaDAO.obtenerTodas()) {
            comboPropietario.addItem(persona.getNombre());
        }

        comboMascota.removeAllItems();
        for (MascotaDTO mascota : mascotaDAO.listar()) {
            comboMascota.addItem(mascota.getNombre());
        }
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0); // Limpia la tabla
        for (ConsultaDTO consulta : consultaDAO.listar()) {
            modeloTabla.addRow(new Object[]{
                consulta.getCodigo(),
                consulta.getFecha(),
                consulta.getVeterinario().getNombre(),
                consulta.getPropietario(),
                consulta.getMascota(),
                consulta.getDescripcion()
            });
        }
    }

    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtVeterinario.setText("");
        txtFecha.setText("");
        txtDescripcion.setText("");
        comboPropietario.setSelectedIndex(0);
        comboMascota.setSelectedIndex(0);
    }

    private void cargarCamposDesdeTabla() {
        int fila = tablaConsultas.getSelectedRow();
        if (fila >= 0) {
            txtCodigo.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtFecha.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtVeterinario.setText(modeloTabla.getValueAt(fila, 2).toString());
            comboPropietario.setSelectedItem(modeloTabla.getValueAt(fila, 3).toString());
            comboMascota.setSelectedItem(modeloTabla.getValueAt(fila, 4).toString());
            txtDescripcion.setText(modeloTabla.getValueAt(fila, 5).toString());
        }
    }

    private void guardarConsulta() {
        String codigo = UUID.randomUUID().toString().substring(0, 8); // Genera código único
        String fechaTexto = txtFecha.getText().trim();
        String veterinarioNombre = txtVeterinario.getText().trim();
        String propietario = (String) comboPropietario.getSelectedItem();
        String mascota = (String) comboMascota.getSelectedItem();
        String descripcion = txtDescripcion.getText().trim();

        // Validación básica
        if (fechaTexto.isEmpty() || veterinarioNombre.isEmpty() || propietario == null || mascota == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ Todos los campos son obligatorios");
            return;
        }

        try {
            // Conversión de fecha (puede lanzar excepción si el formato es inválido)
            //LocalDate fecha = LocalDate.parse(fechaTexto);
            Veterinario vet = new Veterinario(veterinarioNombre, "0000", "0000", "general");
            ConsultaDTO consulta = new ConsultaDTO(codigo, fechaTexto, vet, propietario, mascota, descripcion);
            consultaDAO.guardar(consulta);

            JOptionPane.showMessageDialog(this, "✅ Consulta guardada correctamente");
            limpiarFormulario();
            cargarTabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
        }
    }

    private void editarConsulta() {
        int fila = tablaConsultas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "⚠️ Debes seleccionar una consulta para editar.");
            return;
        }

        String codigo = txtCodigo.getText().trim();
        String fechaTexto = txtFecha.getText().trim();
        String veterinarioNombre = txtVeterinario.getText().trim();
        String propietario = (String) comboPropietario.getSelectedItem();
        String mascota = (String) comboMascota.getSelectedItem();
        String descripcion = txtDescripcion.getText().trim();
        LocalDate fecha;

        if (codigo.isEmpty() || fechaTexto.isEmpty() || veterinarioNombre.isEmpty() || propietario == null || mascota == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Todos los campos son obligatorios.");
            return;
        }

        try {
            // Validar formato de fecha con mensaje personalizado
            //LocalDate fecha;
            try {
                //fecha = LocalDate.parse(fechaTexto);
                fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.ISO_DATE); //.parse(fechaTexto);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "⚠️ Fecha inválida. Usa el formato AAAA-MM-DD, por ejemplo: 2025-07-29");
                return;
            }

            Veterinario vet = new Veterinario(veterinarioNombre, "0000", "0000", "general");
            ConsultaDTO consultaActualizada = new ConsultaDTO(codigo, fechaTexto ,vet, propietario, mascota, descripcion);

            controller.actualizarConsulta(fila, consultaActualizada);
            JOptionPane.showMessageDialog(this, "✅ Consulta actualizada correctamente.");
            limpiarFormulario();
            cargarTabla();
        } catch (DatoInvalidoException e) {
            JOptionPane.showMessageDialog(this, "❌ " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "⚠️ Error inesperado: " + e.getMessage());
        }
    }

    private void eliminarTabla() {
        int fila = tablaConsultas.getSelectedRow();
        if (fila >= 0) {
            int confirmar = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar esta consulta?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                controller.eliminarConsulta(fila);
                cargarTabla();
                JOptionPane.showMessageDialog(this, "🗑️ Consulta eliminada");
                limpiarFormulario();
            }
        } else {
            JOptionPane.showMessageDialog(this, "⚠️ Selecciona una consulta de la tabla para eliminar");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormConsulta().setVisible(true));
    }
    private void cargarConsulta() {
    int fila = modeloTabla.getColumnCount();

    if (fila == -1) return;

    String fechaTexto = modeloTabla.getValueAt(fila, 0).toString();
    String codigo = (String) modeloTabla.getValueAt(fila, 1);
    String veterinario = (String) modeloTabla.getValueAt(fila, 2);
    String macota = (String) modeloTabla.getValueAt(fila, 3);
    String descripcion = (String) modeloTabla.getValueAt(fila, 4);
    String propietario = (String) modeloTabla.getValueAt(fila, 5);
   
    txtCodigo.setText(codigo);
    txtVeterinario.setText(veterinario);
    txtFecha.setText(fechaTexto);
    txtDescripcion.setText(descripcion);
    comboPropietario.setSelectedItem(propietario);
    comboMascota.setSelectedItem(macota);

  }
}
