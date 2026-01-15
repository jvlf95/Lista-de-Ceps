package br.com.projetojoao.Modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeradorDeArquivo {
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void gerarJson(List<Cep> ceps) throws IOException {
        String novoJson = gson.toJson(ceps);
        File file = new File("ListaCeps.JSON");
        FileWriter writer = new FileWriter(file);
        writer.write(novoJson);
        writer.close();
    }
}
