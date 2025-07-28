
package dto;

import java.io.Serializable;

public final class VeterinarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String especialidad;

    public VeterinarioDTO (String nombre, String especialidad) {
        setNombre(nombre);
        setEspecialidad(especialidad);
    }

//    public void mostrarPerfil() {
//        System.out.println("Veterinario: " + nombre + " | Especialidad: " + especialidad);
//    }
    public String getNombre() {
        return nombre;
    }

    /*public void setNombre(String nombre) {
        this.nombre = nombre;
    }*/
    public void setNombre(String nombre) {
        this.nombre = nombre;
        //        if (nombre == null || nombre.isBlank()) {
        //        throw new IllegalArgumentException("El nombre del veterinario no puede estar vacío.");
        //        } 
    }

    public String getEspecialidad() {
        return especialidad;
    }

    /*public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }*/
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
//            if (especialidad == null || especialidad.isBlank()) {
//            throw new IllegalArgumentException("La especialidad no puede estar vacía.");
//        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Especialidad: " + especialidad;
    }
}

