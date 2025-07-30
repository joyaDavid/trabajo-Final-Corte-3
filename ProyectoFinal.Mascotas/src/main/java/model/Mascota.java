package Model;

import java.io.Serializable;

public class Mascota implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String especie;
    private int edad;
    private long id;

    public Mascota(String nombre, String especie, int edad) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
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

    public int getid() {
        return (int) id;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toLineaArchivo() {
        return nombre + "Nombre" + especie + "Especie" + edad + "edad";
    }
}




























//    public static Mascota desdeLineaArchivo(String linea) {
//        String[] partes = linea.split(",");
//        if (partes.length != 3) return null;
//        try {
//           int edad = Integer.parseInt(partes[2]);
//           return new Mascota(partes[0], partes[1], edad);
//        } catch (NumberFormatException e) {
//           return null;
//        }
//    }
