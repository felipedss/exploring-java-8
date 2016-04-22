package model;

public final class Cliente {

    private String nome;

    private Cliente(String nome) {
        this.nome = nome;
    }

    public static Cliente of(String nome) {
        return new Cliente(nome);
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome;
    }
}
