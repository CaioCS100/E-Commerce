package cesmac.si.util;

import java.util.List;

public class VerificadorUtil {
    public static Boolean estaNulo(Object object) {
        return object == null;
    }

    public static boolean naoEstaNulo(Object objeto) {
        return !estaNulo(objeto);
    }

    public static Boolean estaVazio(Object objeto) {
        return objeto.toString().isEmpty();
    }

    public static Boolean naoEstaVazio(Object objeto) {
        return !estaVazio(objeto);
    }

    public static Boolean estaVazioOuNulo(Object objeto) {
        return estaNulo(objeto) || estaVazio(objeto);
    }

    public static Boolean naoEstaVazioOuNulo(Object objeto) {
        return naoEstaNulo(objeto) && naoEstaVazio(objeto);
    }

    public static Boolean listaNulaEVazia(List<? extends Object> lista) {
        return estaNulo(lista) && estaVazio(lista);
    }

    public static Boolean listaNulaOuVazia(List<? extends Object> lista) {
        return estaNulo(lista) || estaVazio(lista);
    }
}
