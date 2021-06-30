import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {
    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        Statement statement = connection.createStatement();
        statement.execute("delete from produto where id = 4");
        System.out.println("Quantidade de linhas que foram modificadas: " + statement.getUpdateCount());
        connection.close();
    }
}
