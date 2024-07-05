package conexao;

public class MySqlConfig extends ConfiBD {
    public MySqlConfig(String host, int port, EnumConexao database, String username, String password) {
        super(host, port, database, username, password);
    }
}