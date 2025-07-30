package model;

public class Propietario extends Persona {

    private static final long serialVersionUID = 1L;

    private int id;

    public Propietario(String nombre, String documento, String telefono, long id) {
        super(nombre, documento, telefono);
        this.id = (int) id;
    }

    public int getid() {
        return id;
    }

    public void setDireccion(String direccion) {
        this.id = id;
    }

    public String getTipoPersona() {
        return "Propietario";
    }
}
