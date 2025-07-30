package model;

import java.io.Serializable;

public final class Veterinario extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    public static boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String especialidad;

    public Veterinario(String nombre, String documento, String telefono, String especialidad) {
        super(nombre, documento, telefono);
        this.especialidad = especialidad;
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

    public String getTipoPersona() {
        return "Propietario";
    }
}

//    public void mostrarPerfil() {
//        System.out.println("Veterinario: " + nombre + " | Especialidad: " + especialidad);
//    }

/*public void setNombre(String nombre) {
        this.nombre = nombre;
    }*/
//        if (nombre == null || nombre.isBlank()) {
//        throw new IllegalArgumentException("El nombre del veterinario no puede estar vacío.");
//        } 

