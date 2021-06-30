package br.com.impacto.modelo;

import java.util.List;

public class Categoria {
    private int id;
    private String titulo;
    private List<Produto> produtos;

    public Categoria(int id, String titulo) {

        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void adicionar(Produto produto) {
        this.produtos.add(produto);
    }

    @Override
    public String toString() {
        return titulo;
    }
}
