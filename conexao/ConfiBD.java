package conexao;

public abstract class ConfiBD {
    private String host;
    private int port = 3306;
    private EnumConexao database;
    private String username = "root";
    private String password = "1234";

    public ConfiBD(String host, int port, EnumConexao database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public String getHost() { 
        return host; 
    }
    public int getPort() {
        return port;
    }
    public EnumConexao getDatabase() { 
        return database; 
    }
    public String getUsername() { 
        return username;
    }
    public String getPassword() {
        return password;
    }
}