
package controller;

import dao.VeterinarioDAO;
import dto.VeterinarioDTO;
import exception.DatoInvalidoException;
import java.util.List;

public class ControllerVeterinario {
    
    private final VeterinarioDAO dao = new VeterinarioDAO ();

    public String registrarVeterinario(VeterinarioDTO veterinario) throws DatoInvalidoException {
        if (veterinario.getNombre() == null || veterinario.getNombre().isBlank()) {
            throw new DatoInvalidoException("El nombre del propietario no puede estar vacío.");
        }
        if (veterinario.getEspecialidad()== null || veterinario.getEspecialidad().isBlank()) {
            throw new DatoInvalidoException("El teléfono del propietario no puede estar vacío.");
        }
        dao.guardar(veterinario);
        return "Veterinario registrado correctamente.";
    }
    public List<VeterinarioDTO> obtenerPropietarios() {
        return dao.listar();
    }

    public void eliminarPropietario(int indice) {
        dao.eliminar(indice);
    }

    public void actualizarVeterinario(int indice, VeterinarioDTO Veterinario) throws DatoInvalidoException {
        if (Veterinario.getNombre() == null || Veterinario.getNombre().isBlank()) {
            throw new DatoInvalidoException("El nombre no puede estar vacío.");
        }
        if (Veterinario.getEspecialidad()== null || Veterinario.getEspecialidad().isBlank()) {
            throw new DatoInvalidoException("El teléfono no puede estar vacío.");
        }
        dao.actualizar(indice, Veterinario);
    }
}
