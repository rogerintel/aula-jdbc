package br.com.impacto.controller;

import br.com.impacto.dao.CategoriaDAO;
import br.com.impacto.factory.ConnectionFactory;
import br.com.impacto.modelo.Categoria;

import java.sql.SQLException;
import java.util.List;

public class CategoriaController {

    private CategoriaDAO categoriaDAO;

    public CategoriaController() {
        categoriaDAO = new CategoriaDAO(new ConnectionFactory().getConnection());
    }

    public List<Categoria> listar() {
        return categoriaDAO.listar();
    }
}
