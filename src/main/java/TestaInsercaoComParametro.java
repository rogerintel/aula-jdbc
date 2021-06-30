import java.sql.*;

public class TestaInsercaoComParametro {

    public static void main(String[] args) {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("insert into produto (nome, " +
                    "descricao) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                adicionarProduto("SmartTV", "45 polegadas", preparedStatement);
                adicionarProduto("Rádio", "Rádio a bateria", preparedStatement);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void adicionarProduto(String nome, String descricao, PreparedStatement preparedStatement)
            throws SQLException {
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, descricao);
        preparedStatement.execute();
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            while (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
        }
    }
}
