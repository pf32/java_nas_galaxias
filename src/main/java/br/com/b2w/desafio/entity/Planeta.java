package br.com.b2w.desafio.entity;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "planeta")
public class Planeta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Field("id")
    private BigInteger id;

    @Field("nome")
    private String nome;

    @Field("clima")
    private String clima;

    @Field("terreno")
    private String terreno;

    @Field("qtd_aparicao")
    private Integer qtdAparicao;


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public Integer getQtdAparicao() {
        return qtdAparicao;
    }

    public void setQtdAparicao(Integer qtdAparicao) {
        this.qtdAparicao = qtdAparicao;
    }

}
