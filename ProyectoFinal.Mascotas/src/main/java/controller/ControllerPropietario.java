package controller;

import dao.PropietarioDAO;
import dto.PropietarioDTO;
import exception.DatoInvalidoException;
import java.util.List;

public class ControllerPropietario {

    private final PropietarioDAO dao = new PropietarioDAO();

    public String registrarPropietario(PropietarioDTO propietario) throws DatoInvalidoException {
        if (propietario.getNombre() == null || propietario.getNombre().isBlank()) {
            throw new DatoInvalidoException("El nombre del propietario no puede estar vacío.");
        }
        if (propietario.getTelefono() == null || propietario.getTelefono().isBlank()) {
            throw new DatoInvalidoException("El teléfono del propietario no puede estar vacío.");
        }
        dao.guardar(propietario);
        return "Propietario registrado correctamente.";
    }

    public List<PropietarioDTO> obtenerPropietarios() {
        return dao.listar();
    }

    public void eliminarPropietario(int indice) {
        dao.eliminar(indice);
    }

    public void actualizarPropietario(int indice, PropietarioDTO propietario) throws DatoInvalidoException {
        if (propietario.getNombre() == null || propietario.getNombre().isBlank()) {
            throw new DatoInvalidoException("El nombre no puede estar vacío.");
        }
        if (propietario.getTelefono() == null || propietario.getTelefono().isBlank()) {
            throw new DatoInvalidoException("El teléfono no puede estar vacío.");
        }
        dao.actualizar(indice, propietario);
    }
}
