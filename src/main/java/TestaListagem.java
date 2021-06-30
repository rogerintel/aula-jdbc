import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();

        PreparedStatement statement = connection.prepareStatement("select * from produto");

        statement.execute();

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
