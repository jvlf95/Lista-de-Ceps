package br.com.projetojoao.Modelo;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ConexaoApi {

    public CepRe getConexao(String entrada)  {
        try {
            String url = "https://viacep.com.br/ws/" + entrada + "/json/";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), CepRe.class);
        }catch (Exception e) {
            throw new FormatoCepInvalidoException("CEP inválido (mais de 8 dígitos) ou não encontrado no sistema!");
        }
    }


}
