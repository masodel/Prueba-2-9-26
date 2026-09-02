package ni.edu.uam.prueba2926.utils;

import java.time.LocalDate;

public final class ValidacionUtil {

    private ValidacionUtil() {
    }

    public static boolean estaVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean longitudMenorQue(String texto, int minimo) {
        return texto == null || texto.trim().length() < minimo;
    }

    public static boolean fechaFutura(LocalDate fecha) {
        return fecha != null && fecha.isAfter(LocalDate.now());
    }
}

