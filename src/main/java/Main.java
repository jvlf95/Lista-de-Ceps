import br.com.projetojoao.Modelo.Cep;
import br.com.projetojoao.Modelo.CepRe;
import br.com.projetojoao.Modelo.FormatoCepInvalidoException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[]args) throws IOException, InterruptedException {
        Scanner tec = new Scanner(System.in);
        String entrada = " ";
        List<Cep> ceps = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        while(!entrada.equalsIgnoreCase("sair")) {
            System.out.println("Digite um cep para buscar suas informações ou 'sair' para encerrar: ");
            entrada = tec.nextLine();

            if(entrada.equalsIgnoreCase("sair")){
                break;
            }

            try {
                String url = "https://viacep.com.br/ws/" + entrada + "/json/";

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                CepRe cepRe = gson.fromJson(json, CepRe.class);

                Cep cep1 = new Cep(cepRe);
                System.out.println("Objeto convertido");
                System.out.println(cep1);

                ceps.add(cep1);

            } catch (FormatoCepInvalidoException e) {
                System.out.println(e);
            }

        }

        String novoJson = gson.toJson(ceps);

        File file = new File("ListaCeps.JSON");
        FileWriter writer = new FileWriter(file);
        writer.write(novoJson);
        writer.close();

        System.out.println("Encerrando programa...");

    }
}
