package dto;

import java.io.Serializable;
import java.time.LocalDate;
import model.Veterinario;

public final class ConsultaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
    private LocalDate fecha;
    private Veterinario veterinario;

    public ConsultaDTO (String codigo, String fecha, Veterinario veterinario) {
        setCodigo(codigo);
        setFecha(fecha);
        setVeterinario(veterinario);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(String fechaTexto) {
        this.codigo = codigo;
    }

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


