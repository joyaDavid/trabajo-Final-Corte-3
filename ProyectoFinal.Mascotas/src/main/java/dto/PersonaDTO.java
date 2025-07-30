package dto;

import java.io.Serializable;

public class PersonaDTO implements Serializable {
private static final long serialVersionUID = 1L;

    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;
    private MascotaDTO mascota;

    public PersonaDTO(String nombre, String cedula, String telefono, String correo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }
    
    public MascotaDTO getMascota() {
    return mascota;
}


    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void setMascota(MascotaDTO mascota) {
    this.mascota = mascota;
}

    @Override
    public String toString() {
        return nombre + " (" + cedula + ")";
    }
}

