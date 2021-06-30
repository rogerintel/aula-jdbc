import java.sql.*;

public class TestaMensagem {
    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();

        Statement statement = connection.createStatement();

        statement.execute("select * from produto");

        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()){
            Integer id = resultSet.getInt("id");
            System.out.println(id);
            String nome = resultSet.getString("nome");
            System.out.println(nome);
            String descricao = resultSet.getString("descricao");
            System.out.println(descricao);
        }
        connection.close();
    }

}
