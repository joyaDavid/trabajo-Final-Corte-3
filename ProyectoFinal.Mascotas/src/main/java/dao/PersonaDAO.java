package dao;

import dto.PersonaDTO;
import model.Persona;
import persistence.GestorPersistencia;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    private static final String RUTA = "data/personas.dat";
    private final GestorPersistencia gest = GestorPersistencia.getInstance();

    public void guardarPersona(PersonaDTO nueva) {
    gest.crearArchivoSiNoExiste(RUTA); 
        List<PersonaDTO> lista = obtenerTodas();
        lista.add(nueva);
        GestorPersistencia.getInstance().guardarLista(RUTA, lista);
    }

    public List<Persona> listar() {
        List<Persona> lista = gest.cargarLista(RUTA);
        return (lista != null) ? lista : new ArrayList<>();
    }

    public List<PersonaDTO> obtenerTodas() {
        List<PersonaDTO> personas = GestorPersistencia.getInstance().cargarLista(RUTA);
        return personas != null ? personas : new ArrayList<>();
    }
    
    public void eliminarPorCedula(String cedula) {
        List<PersonaDTO> lista = obtenerTodas();
        lista.removeIf(p -> p.getCedula().equalsIgnoreCase(cedula));
        GestorPersistencia.getInstance().guardarLista(RUTA, lista);
    }

    public boolean actualizar(int indice, Persona personaActualizada) {
        List<Persona> lista = listar();
        if (indice >= 0 && indice < lista.size()) {
            lista.set(indice, personaActualizada);
            gest.guardarLista(RUTA, lista);
            return true;
        }
        return false;
    }

    public void limpiarTodo() {
        gest.guardarLista(RUTA, new ArrayList<>());
    }
}
