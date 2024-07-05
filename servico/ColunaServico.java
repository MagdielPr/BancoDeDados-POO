package servico;

import banco.ColunaTabelaDAO;
import classes.Coluna;
import classes.Tipo;
import conexao.ConexaoBD;
import conexao.EnumConexao;
import conexao.MySqlConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ColunaServico implements ColunaTabelaDAO<Coluna<Tipo>> {
    private ConexaoBD<MySqlConfig> conexaoBD;

    public ColunaServico() {
        MySqlConfig config = new MySqlConfig("localhost", 3306, EnumConexao.SQLCONNECTION, "root", "1234");
        this.conexaoBD = new ConexaoBD<>(config);
    }

    @Override
    public void adicionarColuna(String nomeBanco, String nomeTabela, Coluna<Tipo> coluna) throws SQLException {
        String sql = "ALTER TABLE " + nomeBanco + "." + nomeTabela + " ADD " + coluna.getNome() + " " + coluna.getTipo();
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
        System.out.println("-Comando SQL gerado:-");
        System.out.println(sql);
        System.out.println("-------------------");
    }

    @Override
    public void alterarColuna(String nomeBanco, String nomeTabela, Coluna<Tipo> coluna) throws SQLException {
        String sql = "ALTER TABLE " + nomeBanco + "." + nomeTabela + " MODIFY COLUMN " + coluna.getNome() + " " + coluna.getTipo();
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
        System.out.println("-Comando SQL gerado:-");
        System.out.println(sql);
        System.out.println("-------------------");
    }

    @Override
    public void removerColuna(String nomeBanco, String nomeTabela, String nomeColuna) throws SQLException {
        String sql = "ALTER TABLE " + nomeBanco + "." + nomeTabela + " DROP COLUMN " + nomeColuna;
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
        System.out.println("-Comando SQL gerado:-");
        System.out.println(sql);
        System.out.println("-------------------");
    }

    @Override
    public List<Coluna<Tipo>> listarColunas(String nomeBanco, String nomeTabela) throws SQLException {
        String sql = "SHOW COLUMNS FROM " + nomeBanco + "." + nomeTabela;
        List<Coluna<Tipo>> colunas = new ArrayList<>();
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String nomeColuna = rs.getString("Field");
                String tipoColuna = rs.getString("Type").toUpperCase();

                Tipo tipo;
                if (tipoColuna.startsWith("VARCHAR")) {
                    tipo = Tipo.VARCHAR;
                } else if (tipoColuna.startsWith("INT")) {
                    tipo = Tipo.INT;
                } else {
                    tipo = Tipo.valueOf(tipoColuna);
                }

                Coluna<Tipo> coluna = new Coluna<>(nomeColuna, tipo);
                colunas.add(coluna);
            }
        }
        return colunas;
    }
}
