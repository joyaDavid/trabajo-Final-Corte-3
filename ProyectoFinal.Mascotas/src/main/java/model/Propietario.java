package model;

import java.io.Serializable;

public class Propietario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String telefono;
    private long id;

    public Propietario(String telefono, long id, String nombre) {
        this.telefono = telefono;
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public String getTelefono() {
        return telefono;

    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return "Propietario";
    }
}
