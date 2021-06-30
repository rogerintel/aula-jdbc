package br.com.impacto.modelo;

public class Produto {

    private int id;
    private String nome;
    private String descricao;
    private int categoriaId;

    public Produto(int id, String nome, String descricao, int categoriaId) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Produto(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Produto(String nome, String descricao) {

        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public int getCategoriaId() {
        return this.categoriaId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setCategoriaId(int categoriaId) {

        this.categoriaId = categoriaId;
    }
}
