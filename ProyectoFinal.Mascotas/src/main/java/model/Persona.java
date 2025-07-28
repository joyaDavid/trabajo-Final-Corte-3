
package model;

import java.io.Serializable;

public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String documento;
    private String telefono;
    private String tipo;     
    private String extra;    

    public Persona(String nombre, String documento, String telefono, String tipo, String extra) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.tipo = tipo;
        this.extra = extra;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return tipo + ": " + nombre + " (" + documento + "), Tel: " + telefono + ", Info: " + extra;
    }
}
