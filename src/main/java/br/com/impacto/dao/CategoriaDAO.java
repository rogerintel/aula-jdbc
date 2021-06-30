package br.com.impacto.dao;

import br.com.impacto.modelo.Categoria;
import br.com.impacto.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;

    public CategoriaDAO(Connection connection){

        this.connection = connection;
    }

    public List<Categoria> listarComProduto() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        Categoria ultima = null;
        String sql = "SELECT * FROM categoria c join produto p on c.id = p.categoria_id";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                if (ultima == null || !ultima.getTitulo().equals(resultSet.getString(2))) {
                    Categoria categoria = new Categoria(resultSet.getInt(1), resultSet.getString(2));
                    categorias.add(categoria);
                    ultima = categoria;
                }
                    ultima.adicionar(new Produto(resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5),
                     resultSet.getInt(6)));
            }
        }
        return categorias;
    }

    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM categoria")){
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                categorias.add(new Categoria(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
           throw new RuntimeException(throwables);
        }
        return categorias;
    }
}
