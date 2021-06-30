package br.com.impacto.dao;

import br.com.impacto.modelo.Categoria;
import br.com.impacto.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    public void salvarComCategoria(Produto produto){
       try(PreparedStatement statement = connection.prepareStatement("INSERT INTO produto (nome, descricao, " +
                       "categoria_id) VALUES (?,?,?)",
               Statement.RETURN_GENERATED_KEYS)){
           statement.setString(1, produto.getNome());
           statement.setString(2, produto.getDescricao());
           statement.setInt(3, produto.getCategoriaId());
           statement.execute();

           try(ResultSet resultSet = statement.getGeneratedKeys()){
               while (resultSet.next()) {
                   produto.setId(resultSet.getInt(1));
               }
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
    }

    public List<Produto> listar() {
        try {
            return this.buscar(null);
        } catch (SQLException throwables) {
           throw new RuntimeException(throwables);
        }
    }

    public List<Produto> buscar(Categoria categoria) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        if (categoria != null)
            sql += " WHERE categoria_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            if (categoria != null)
                statement.setInt(1, categoria.getId());
            statement.execute();
            transformarResultSetEmProduto(produtos, statement);
        }
        return produtos;
    }

    public void deletar(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM produto WHERE id = ?")){
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException throwables) {
           throw new RuntimeException(throwables);
        }
    }

    public void alterar(Produto produto) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE produto SET nome = ?, descricao = ? " +
                "WHERE id = ?")){
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getDescricao());
            statement.setInt(3, produto.getId());
            statement.execute();

        } catch (SQLException throwables) {
           throw new RuntimeException(throwables);
        }
    }

    private void transformarResultSetEmProduto(List<Produto> produtos, PreparedStatement statement) throws SQLException {
        try (ResultSet set = statement.getResultSet()){
            while (set.next()){
                produtos.add(new Produto(
                        set.getInt(1),
                        set.getString(2),
                        set.getString(3),
                        set.getInt(4)
                ));
            }
        }
    }
}
