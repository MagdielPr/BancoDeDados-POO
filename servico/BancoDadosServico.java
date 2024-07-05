package servico;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import banco.BancoDadosDAO;
import classes.BancoDados;
import conexao.ConexaoBD;
import conexao.MySqlConfig;
import conexao.EnumConexao;

public class BancoDadosServico implements BancoDadosDAO {
    private ConexaoBD<MySqlConfig> conexaoBD;

    public BancoDadosServico() {
        MySqlConfig config = new MySqlConfig("localhost", 3306, EnumConexao.SQLCONNECTION, "root", "1234");
        this.conexaoBD = new ConexaoBD<>(config);
    }

    @Override
    public void criarBancoDados(BancoDados bancoDados) throws SQLException {
        String sql = "CREATE DATABASE " + bancoDados.getNome();
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
        System.out.println("-Comando SQL gerado:-");
        System.out.println(sql);
        System.out.println("-------------------");

    }

    @Override
    public void removerBancoDados(String nomeBanco) throws SQLException {
        String sql = "DROP DATABASE " + nomeBanco;
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
        System.out.println("-Comando SQL gerado:-");
        System.out.println(sql);
        System.out.println("-------------------");

    }

    @Override
    public List<BancoDados> listarBancosDados() throws SQLException {
        String sql = "SHOW DATABASES";
        List<BancoDados> bancosDados = new ArrayList<>();
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nomeBanco = rs.getString("Database");
                bancosDados.add(new BancoDados(nomeBanco));
            }
        }
        return bancosDados;
    }
}
