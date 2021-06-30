import java.sql.*;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {

        String nome = "Mouse";
        String descricao = "Mouse sem fio";
        Connection connection = new ConnectionFactory().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into produto (nome, descricao) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, descricao);
        preparedStatement.execute();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        while (generatedKeys.next()){
            int id = generatedKeys.getInt(1);
            System.out.println("O id criado foi: " + id);
        }
        connection.close();
    }
}
