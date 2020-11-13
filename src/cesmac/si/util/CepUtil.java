package cesmac.si.util;

import cesmac.si.model.Endereco;
import cesmac.si.model.EnderecoViaCep;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static cesmac.si.shared.Constantes.*;
import static cesmac.si.util.VerificadorUtil.naoEstaNulo;

public class CepUtil {

    private static final Gson GSON = new Gson();

    public static Endereco buscarCep(String cep) {
        return GSON.fromJson(fazerConsultaApi(cep), EnderecoViaCep.class).toEndereco();
    }

    private static String fazerConsultaApi(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        StringBuilder response = new StringBuilder("");

        try {
            HttpURLConnection conURL = (HttpURLConnection) new URL(url).openConnection();
            conURL.setRequestMethod(GET);
            conURL.setRequestProperty("Content-Type", CONTENT_TYPE);
            conURL.setRequestProperty("User-Agent", USER_AGENT);
            conURL.setDoOutput(true);

            response = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conURL.getInputStream(), StandardCharsets.UTF_8))) {
                String inputLine;
                while (naoEstaNulo(inputLine = reader.readLine())) {
                    response.append(inputLine);
                }
            }

            conURL.disconnect();
        } catch (IOException ex) {
            System.out.println("Ocorreu um erro ao tentar consultar o CEP! \nErro: " + ex.getMessage());
        }

        return response.toString();
    }
}
