package controller;

import dao.ConsultaDAO;
import dto.ConsultaDTO;
import exception.DatoInvalidoException;
import java.time.LocalDate;
import java.util.List;

public class ControllerConsulta {

    private final ConsultaDAO dao = new ConsultaDAO();

    // Validación y guardado de la consulta
    public void registrarConsulta(ConsultaDTO consulta) throws DatoInvalidoException {
        if (consulta.getCodigo() == null || consulta.getCodigo().isBlank()) {
            throw new DatoInvalidoException("El código no puede estar vacío.");
        }

        if (consulta.getFecha() == null || consulta.getFecha() == null) {
            throw new DatoInvalidoException("La fecha no puede estar vacía.");
        }

        if (consulta.getVeterinario() == null || 
        consulta.getVeterinario().getNombre() == null || 
        consulta.getVeterinario().getNombre().isBlank()) {
            throw new DatoInvalidoException("El veterinario no puede ser nulo.");
        }

        // Validación de fecha futura (opcional)
        try {
            LocalDate fecha = LocalDate.parse(consulta.getFecha());
            if (fecha.isAfter(LocalDate.now())) {
                throw new DatoInvalidoException("La fecha no puede ser futura.");
            }
        } catch (Exception e) {
            throw new DatoInvalidoException("Formato de fecha inválido. Usa AAAA-MM-DD.");
        }

        dao.guardar(consulta);
    }

    // Obtener lista de todas las consultas
    public List<ConsultaDTO> obtenerConsultas() {
        return dao.listar();
    }

    // Eliminar consulta por índice
    public void eliminarConsulta(int indice) {
        dao.eliminar(indice);
    }

    // Actualizar una consulta existente
    public void actualizarConsulta(int indice, ConsultaDTO nuevaConsulta) throws DatoInvalidoException {
        registrarConsulta(nuevaConsulta); // Reutiliza validación
        dao.actualizar(indice, nuevaConsulta);
    }

    // Eliminar todas las consultas
    public void limpiarConsultas() {
        dao.limpiar();
    }
}