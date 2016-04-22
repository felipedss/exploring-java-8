package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

public final class Compra {

    private Cliente cliente;
    private LocalDate data;
    private Collection<Produto> produtos = new HashSet<>();

    private Compra(Cliente cliente, LocalDate data) {
        this.cliente = cliente;
        this.data = data;
    }

    public static Compra of(Cliente cliente, LocalDate data) {
        return new Compra(cliente, data);
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public Collection<Produto> getProdutos() {
        return Collections.unmodifiableCollection(produtos);
    }

    public BigDecimal totalCompra() {
        return produtos.stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente.getNome()
                + ", Data: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + ", Produtos: " + produtos;
    }
}
