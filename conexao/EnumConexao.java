package conexao;

public enum EnumConexao {
    SQLCONNECTION("sqlconnection"),
    TEST("test"),
    PRODUCTION("production");

    private final String nome;

    EnumConexao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}