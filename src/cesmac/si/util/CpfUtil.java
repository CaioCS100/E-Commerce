package cesmac.si.util;

public class CpfUtil {

    private static final int[] PESO_CPF = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

    public static Boolean validarCpf(String cpf) {
        cpf = removerCaracteresEspeciais(cpf);

        if (verificarSeDigitosSaoIguais(cpf))
            return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9));
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1);

        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

    private static String removerCaracteresEspeciais(String cnpj) {
        return cnpj.replaceAll("\\D*","");
    }

    private static Boolean verificarSeDigitosSaoIguais(String cpf) {
        return cpf.equals("00000000000") || cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                cpf.length() != 11;
    }

    private static Integer calcularDigito(String cpf) {
        Integer soma = 0;
        Integer digito = 0;

        for (int i = cpf.length() - 1; i >= 0; i--) {
            digito = Integer.parseInt(cpf.substring(i, i+1));
            soma += digito * PESO_CPF[(cpf.length() - 1) - i];
        }

        return soma % 11 == 0 || soma % 11 == 1 ? 0 : 11 - (soma % 11);
    }
}
