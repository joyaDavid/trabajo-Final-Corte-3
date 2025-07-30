package dto;

import java.io.Serializable;
//import java.time.LocalDate;
import model.Veterinario;

public class ConsultaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
    private String fecha;
    private Veterinario veterinario;
    private String propietario;
    private String mascota;
    private String descripcion;
    
    
    public ConsultaDTO(String codigo, String fecha, Veterinario veterinario, String propietario, String mascota, String descripcion) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.veterinario = veterinario;
        this.propietario = propietario;
        this.mascota = mascota;
        this.descripcion = descripcion;
    }

    public String getCodigo() { return codigo; }
    public String getFecha() { return fecha; }
    public Veterinario getVeterinario() { return veterinario; }
    public String getPropietario() { return propietario; }
    public String getMascota() { return mascota; }
    public String getDescripcion() { return descripcion; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setVeterinario(Veterinario veterinario) { this.veterinario = veterinario; }
    public void setPropietario(String propietario) { this.propietario = propietario; }
    public void setMascota(String mascota) { this.mascota = mascota; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
