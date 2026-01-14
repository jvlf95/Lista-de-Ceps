package br.com.projetojoao.Modelo;

public class FormatoCepInvalidoException extends RuntimeException {
    private String mensagem;

    public FormatoCepInvalidoException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage(){
        return this.mensagem;
    }
}
