import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {
    public static void main(String[] args) throws SQLException {

        Connection connection = new ConnectionFactory().getConnection();

        Statement statement = connection.createStatement();

        statement.execute("insert into produto (nome, descricao) values ('Mouse', 'Mouse sem fio')", Statement.RETURN_GENERATED_KEYS);

        ResultSet keys = statement.getGeneratedKeys();

        while (keys.next()) {
            int id = keys.getInt(1);
            System.out.println("O id criado foi: " + id);
        }
    }
}
