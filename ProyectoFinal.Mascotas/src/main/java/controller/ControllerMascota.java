package controller;

import dao.MascotaDAO;
import dto.MascotaDTO;
import exception.DatoInvalidoException;

import java.util.List;

public class ControllerMascota {

    private MascotaDAO mascotaDAO = new MascotaDAO();

    public void registrarMascota(MascotaDTO mascota) throws DatoInvalidoException {
        if (mascota.getNombre() == null || mascota.getNombre().isEmpty()) {
            throw new DatoInvalidoException("El nombre de la mascota es obligatorio");
        }
        if (mascota.getEdad() < 0) {
            throw new DatoInvalidoException("La edad no puede ser negativa");
        }
        mascotaDAO.guardar(mascota); // ✅ nombre correcto
    }

    public List<MascotaDTO> obtenerMascotas() {
        return mascotaDAO.cargarMascotas(); // ✅ nombre correcto
    }
    public void eliminarMascota(int indice) {
    mascotaDAO.eliminar(indice);
}

public void actualizarMascota(int indice, MascotaDTO mascota) throws DatoInvalidoException {
    if (mascota.getNombre().isBlank() || mascota.getEspecie().isBlank()) {
        throw new DatoInvalidoException("Nombre y especie no pueden estar vacíos.");
    }
    mascotaDAO.actualizar(indice, mascota);
}

}
