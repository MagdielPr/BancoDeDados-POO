package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD<T extends ConfiBD> {
    private final T config;

    public ConexaoBD(T config) {
        this.config = config;
    }

    public Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s", 
            config.getHost(), config.getPort(), config.getDatabase().getNome());
        
        try {
            Connection connection = DriverManager.getConnection(url, config.getUsername(), config.getPassword());
            System.out.println("Conectado a conexão: " + config.getDatabase().getNome() + " com sucesso!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw e;
        }
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}