
package model;

import java.io.Serializable;

public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String documento;
    private String telefono;   

    public Persona(String nombre, String documento, String telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre + ",nombre:" + documento + ",documento:" + telefono  + ",telefono:";
    }
}
