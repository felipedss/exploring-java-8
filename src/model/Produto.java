package model;

import java.math.BigDecimal;
import java.nio.file.Path;

public final class Produto {

    private String nome;
    private Path arquivo;
    private BigDecimal preco;

    private Produto(String nome, Path arquivo, BigDecimal preco) {
        this.nome = nome;
        this.arquivo = arquivo;
        this.preco = preco;
    }

    public static Produto of(String nome, Path arquivo, BigDecimal preco) {
        return new Produto(nome, arquivo, preco);
    }

    public String getNome() {
        return nome;
    }

    public Path getArquivo() {
        return arquivo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + ", Preco: " + preco;
    }
}
