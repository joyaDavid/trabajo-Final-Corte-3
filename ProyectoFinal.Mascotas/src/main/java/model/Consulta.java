package model;

import java.io.Serializable;
//import java.time.LocalDate;

public final class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
    private String fecha;
    private Veterinario veterinario;

    public Consulta(String codigo, String fecha, Veterinario veterinario) {
        setCodigo(codigo);
        setFecha(fecha);
        setVeterinario(veterinario);
    }

//    public void mostrarConsulta() {
//        System.out.println("Consulta Código: " + codigo);
//        System.out.println("Fecha: " + fecha);
//        if (veterinario != null) {
//            veterinario.mostrarPerfil();
//        } else {
//            System.out.println("Sin veterinario asignado.");
//        }
//    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
//                if (codigo == null || codigo.isBlank()) {
//            throw new IllegalArgumentException("Código inválido.");
//        }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fechaTexto) {
        this.codigo = codigo;
    }
//        try {
//            this.fecha = LocalDate.parse(fechaTexto); // Formato YYYY-MM-DD
//        } catch (DateTimeParseException e) {
//            throw new IllegalArgumentException("Formato de fecha inválido.");
//        }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public String toLineaArchivo() {
        return codigo + "Codifgo" + fecha + "Fecha" + veterinario + "Veterinario";
    }
}
//        if (veterinario == null) {
//            throw new IllegalArgumentException("Veterinario requerido.");
//        }

