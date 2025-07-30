package view;

import controller.ControllerMascota;
import dto.MascotaDTO;
import exception.DatoInvalidoException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormMascota extends JFrame {

    private JTextField txtNombre;
    private JTextField txtRaza;
    private JTextField txtEdad;
    private JTextField txtEspecie;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JButton btnEliminar;
    private JButton btnEditar;
    private JTable tablaMascotas;
    private DefaultTableModel modeloTabla;

    private ControllerMascota controller = new ControllerMascota();

public FormMascota() {
    setTitle("Formulario de Mascota");
    setSize(700, 600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);

    initComponents();           // Inicializa los componentes visuales
    cargarMascotasEnTabla();    // Carga las mascotas en la tabla

    // Listener para cuando se selecciona una fila en la tabla
    tablaMascotas.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            cargarMascotaSeleccionada();
        }
    });

    }
private void initComponents() {
    // === Etiquetas y campos ===
    JLabel lblNombre = new JLabel("Nombre:");
    JLabel lblRaza = new JLabel("Raza:");
    JLabel lblEdad = new JLabel("Edad:");
    JLabel lblEspecie = new JLabel("Especie:");

    txtNombre = new JTextField(15);
    txtRaza = new JTextField(15);
    txtEdad = new JTextField(5);
    txtEspecie = new JTextField(15);

    // === Botones ===
    btnGuardar = new JButton("Guardar");
    btnCancelar = new JButton("Cancelar");
    btnEditar = new JButton("Editar");
    btnEliminar = new JButton("Eliminar");

    btnEditar.addActionListener(e -> editarMascota());
    btnEliminar.addActionListener(e -> eliminarMascota());
    btnGuardar.addActionListener(e -> guardarMascota());
    btnCancelar.addActionListener(e -> dispose());

    // === Formulario ===
    JPanel formulario = new JPanel(new GridLayout(4, 2, 10, 10));
    formulario.setBorder(BorderFactory.createTitledBorder("Registrar Mascota"));
    formulario.add(lblNombre);
    formulario.add(txtNombre);
    formulario.add(lblEdad);
    formulario.add(txtEdad);
    formulario.add(lblRaza);
    formulario.add(txtRaza);
    formulario.add(lblEspecie);
    formulario.add(txtEspecie);

    // === Panel de botones ===
    JPanel panelBotones = new JPanel(new GridLayout(1, 4, 10, 10));
    panelBotones.add(btnGuardar);
    panelBotones.add(btnCancelar);
    panelBotones.add(btnEditar);
    panelBotones.add(btnEliminar);

    // === Agrupar formulario + botones ===
    JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
    panelSuperior.add(formulario, BorderLayout.NORTH);
    panelSuperior.add(panelBotones, BorderLayout.SOUTH);

    // === Tabla ===
    modeloTabla = new DefaultTableModel(new Object[]{"Nombre", "Edad", "Raza", "Especie"}, 0);
    tablaMascotas = new JTable(modeloTabla);
    JScrollPane scrollPane = new JScrollPane(tablaMascotas);

    // === Panel principal ===
    JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
    panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
    panelPrincipal.add(scrollPane, BorderLayout.CENTER); 

    add(panelPrincipal);
}

    private void guardarMascota() {
        try {
            String nombre = txtNombre.getText().trim();
            String raza = txtRaza.getText().trim();
            String especie = txtEspecie.getText().trim();
            int edad = Integer.parseInt(txtEdad.getText().trim());

            MascotaDTO mascota = new MascotaDTO(nombre, especie, edad, raza);
            controller.registrarMascota(mascota);

            JOptionPane.showMessageDialog(this, "✅ Mascota registrada correctamente");

            limpiarCampos();
            cargarMascotasEnTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "⚠️ Edad inválida", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DatoInvalidoException e) {
            JOptionPane.showMessageDialog(this, "❌ " + e.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarMascotasEnTabla() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        List<MascotaDTO> mascotas = controller.obtenerMascotas();
        for (MascotaDTO m : mascotas) {
            modeloTabla.addRow(new Object[]{
                m.getNombre(), m.getEdad(), m.getRaza(), m.getEspecie()
            });
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtRaza.setText("");
        txtEdad.setText("");
        txtEspecie.setText("");
    }

    private void eliminarMascota() {
        int filaSeleccionada = tablaMascotas.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int confirmar = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar esta mascota?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                controller.eliminarMascota(filaSeleccionada);
                cargarMascotasEnTabla();
                JOptionPane.showMessageDialog(this, "🗑️ Mascota eliminada");
            }
        } else {
            JOptionPane.showMessageDialog(this, "⚠️ Selecciona una mascota de la tabla para eliminar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void editarMascota() {
    int filaSeleccionada = tablaMascotas.getSelectedRow();

    // Validación: debe seleccionarse una fila
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "⚠️ Debes seleccionar una mascota de la tabla para actualizarla.");
        return;
    }

    // Obtener datos actualizados del formulario
    String nombre = txtNombre.getText().trim();
    String edadTexto = txtEdad.getText().trim();
    String raza = txtRaza.getText().trim();
    String especie = txtEspecie.getText().trim();

    // Validación de campos vacíos
    if (nombre.isEmpty() || edadTexto.isEmpty() || raza.isEmpty() || especie.isEmpty()) {
        JOptionPane.showMessageDialog(this, "❌ Todos los campos son obligatorios.");
        return;
    }

    int edad;

    try {
        edad = Integer.parseInt(edadTexto);
        if (edad < 0) {
            JOptionPane.showMessageDialog(this, "⚠️ La edad no puede ser negativa.");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "❌ La edad debe ser un número válido.");
        return;
    }

    try {
        // Crea el objeto MascotaDTO actualizado
        MascotaDTO mascotaActualizada = new MascotaDTO(nombre, especie, edad, raza);

        // Llama al controlador para actualizar la mascota
        controller.actualizarMascota(filaSeleccionada, mascotaActualizada);

        JOptionPane.showMessageDialog(this, "✅ Mascota actualizada correctamente.");
        actualizarTabla();
        limpiarCampos();

    } catch (DatoInvalidoException e) {
        JOptionPane.showMessageDialog(this, "❌ " + e.getMessage(), "Error de datos", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "⚠️ Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


private void actualizarTabla() {
    modeloTabla.setRowCount(0); // Limpia las filas existentes
    List<MascotaDTO> lista = controller.obtenerMascotas(); 

    for (MascotaDTO m : lista) {
        // Mismo orden que en cargarMascotasEnTabla
        modeloTabla.addRow(new Object[]{
            m.getNombre(), m.getEdad(), m.getRaza(), m.getEspecie()
        });
    }
}

    
    // Carga los datos de la mascota seleccionada en el formulario
private void cargarMascotaSeleccionada() {
    int fila = tablaMascotas.getSelectedRow();

    if (fila == -1) return;

    String nombre = (String) modeloTabla.getValueAt(fila, 0);
    String edadTexto = modeloTabla.getValueAt(fila, 1).toString();
    String raza = (String) modeloTabla.getValueAt(fila, 2);
    String especie = (String) modeloTabla.getValueAt(fila, 3);

    txtNombre.setText(nombre);
    txtEdad.setText(edadTexto);
    txtRaza.setText(raza);
    txtEspecie.setText(especie);
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormMascota().setVisible(true));
    }
}
