 package controller;

import dao.PersonaDAO;
import dto.PersonaDTO;
import exception.DatoInvalidoException;

import java.util.List;

public class ControllerPersona {

    private final PersonaDAO personaDAO = new PersonaDAO();

    public void registrarPersona(PersonaDTO persona) throws DatoInvalidoException {
        if (persona.getNombre() == null || persona.getNombre().isEmpty()) {
            throw new DatoInvalidoException("El nombre no puede estar vacío");
        }
        if (persona.getCedula() == null || persona.getCedula().isEmpty()) {
            throw new DatoInvalidoException("La cédula no puede estar vacía");
        }
        if (persona.getCorreo() == null || persona.getCorreo().isEmpty()) {
            throw new DatoInvalidoException("El correo no puede estar vacío");
        }
        if (persona.getTelefono() == null || persona.getTelefono().isEmpty()) {
            throw new DatoInvalidoException("El teléfono no puede estar vacío");
        }

        personaDAO.guardarPersona(persona);
    }

    public List<PersonaDTO> obtenerPersonas() {
        return personaDAO.obtenerTodas();
    }

    public void eliminarPersona(String cedula) {
        personaDAO.eliminarPorCedula(cedula);
    }

    public PersonaDTO buscarPorCedula(String cedula) {
        List<PersonaDTO> personas = personaDAO.obtenerTodas();
        for (PersonaDTO p : personas) {
            if (p.getCedula().equalsIgnoreCase(cedula)) {
                return p;
            }
        }
        return null;
    }
}

