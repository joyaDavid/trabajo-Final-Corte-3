package dto;

import java.io.Serializable;

public class MascotaDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String especie;
    private int edad;
    private String raza;

    public MascotaDTO(String nombre, String especie, int edad, String raza) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.raza = raza;
    }

    public String getNombre() { 
        return nombre;
    }
    public String getEspecie() { 
        return especie; 
    }
    public int getEdad() { 
        return edad; 
    }
    public String getRaza() {
        return raza;
    }
    
    @Override
    public String toString() {
        return nombre + " - " + especie + " - " + edad + " años";
    }
}
