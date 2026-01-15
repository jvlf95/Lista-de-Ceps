import br.com.projetojoao.Modelo.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[]args) throws IOException, InterruptedException {
        Scanner tec = new Scanner(System.in);
        String entrada = "";

        List<Cep> ceps = new ArrayList<>();
        ConexaoApi conexao = new ConexaoApi();

        while(!entrada.equalsIgnoreCase("sair")) {
            System.out.println("Digite um cep para buscar suas informações ou 'sair' para encerrar: ");
            entrada = tec.nextLine();

            if(entrada.equalsIgnoreCase("sair")){
                break;
            }

            try {
                CepRe cepRe = conexao.getConexao(entrada);
                Cep cep1 = new Cep(cepRe);
                System.out.println("Objeto convertido");
                System.out.println(cep1);
                ceps.add(cep1);
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }

        GeradorDeArquivo arquivo = new GeradorDeArquivo();
        arquivo.gerarJson(ceps);

        System.out.println("Encerrando programa...");

    }
}
