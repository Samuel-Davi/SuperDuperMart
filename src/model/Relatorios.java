package model;

import java.time.LocalDateTime;

public class Relatorios {

    private String tipo_relatorio, descricao;
    private LocalDateTime data_geracao;

    public Relatorios(String tipo_relatorio, String descricao, LocalDateTime data_geracao) {
        this.tipo_relatorio = tipo_relatorio;
        this.descricao = descricao;
        this.data_geracao = data_geracao;
    }

    // Getters and Setters

    public String getTipo_relatorio() {
        return tipo_relatorio;
    }

    public void setTipo_relatorio(String tipo_relatorio) {
        this.tipo_relatorio = tipo_relatorio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData_geracao() {
        return data_geracao;
    }

    public void setData_geracao(LocalDateTime data_geracao) {
        this.data_geracao = data_geracao;
    }

}
