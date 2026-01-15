package br.com.projetojoao.Modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeradorDeArquivo {

    public void gerarJson(List<Cep> ceps) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String novoJson = gson.toJson(ceps);
        FileWriter writer = new FileWriter("ListaCeps.JSON");
        writer.write(novoJson);
        writer.close();
    }
}
