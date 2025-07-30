package utils;

public class IDGenerador {

    private static int contadorConsulta = 1000;
    private static int contadorMascota = 2000;
    private static int contadorPropietario = 3000;
    private static int contadorVeterinario = 4000;

    // Evita instanciación
    private IDGenerador() {
    }

    public static synchronized String generarCodigoConsulta() {
        return "C" + (contadorConsulta++);
    }

    public static synchronized String generarCodigoMascota() {
        return "M" + (contadorMascota++);
    }

    public static synchronized String generarCodigoPropietario() {
        return "P" + (contadorPropietario++);
    }

    public static synchronized String generarCodigoVeterinario() {
        return "V" + (contadorVeterinario++);
    }
}