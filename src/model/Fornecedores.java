package model;

public class Fornecedores {
    private String nome, contato;

    public Fornecedores(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
