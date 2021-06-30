package br.com.impacto.controller;

import br.com.impacto.dao.ProdutoDAO;
import br.com.impacto.factory.ConnectionFactory;
import br.com.impacto.modelo.Produto;

import java.sql.SQLException;
import java.util.List;

public class ProdutoController {
    ProdutoDAO produtoDAO;

    public ProdutoController() {
        produtoDAO = new ProdutoDAO(new ConnectionFactory().getConnection());
    }

    public void alterar(Produto produto) {
            produtoDAO.alterar(produto);
    }

    public void deletar(Integer id) {
            produtoDAO.deletar(id);
    }

    public void salvar(Produto produto) {
        produtoDAO.salvarComCategoria(produto);
    }

    public List<Produto> listar() {
            return produtoDAO.listar();
    }
}
