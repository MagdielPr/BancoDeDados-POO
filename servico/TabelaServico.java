package servico;

import banco.TabelaDAO;
import classes.ChaveFK;
import classes.Coluna;
import classes.Operacao;
import classes.Tabela;
import conexao.ConexaoBD;
import conexao.EnumConexao;
import conexao.MySqlConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TabelaServico implements TabelaDAO {
    private ConexaoBD<MySqlConfig> conexaoBD;

    public TabelaServico() {
        MySqlConfig config = new MySqlConfig("localhost", 3306, EnumConexao.SQLCONNECTION, "root", "1234");
        this.conexaoBD = new ConexaoBD<>(config);
    }

    @Override
    public void criarTabela(String nomeBanco, Tabela tabela) throws SQLException {
        StringBuilder sql = new StringBuilder("CREATE TABLE " + nomeBanco + "." + tabela.getNome() + " (");
        List<String> colunaDefinicoes = new ArrayList<>();
        List<String> chavePrimaria = new ArrayList<>();
        List<String> chavesEstrangeiras = new ArrayList<>();
        
        for (Coluna<?> coluna : tabela.getColunas()) {
            String definicaoColuna = coluna.getNome() + " " + coluna.getTipo();
            if ("VARCHAR".equals(coluna.getTipo().toString())) {
                definicaoColuna += "(" + coluna.getTamanho() + ")";
            }
            if ("CHAR".equals(coluna.getTipo().toString())) {
                definicaoColuna += "(" + coluna.getTamanho() + ")";
            }
            if ("DECIMAL".equals(coluna.getTipo().toString())) {
                definicaoColuna += "(" + coluna.getTamanho() + ")";
            }
            if ("FLOAT".equals(coluna.getTipo().toString())) {
                definicaoColuna += "(" + coluna.getTamanho() + ")";
            }
            if ("INT".equals(coluna.getTipo().toString())) {
                definicaoColuna += "(" + coluna.getTamanho() + ")";
            }
            if (coluna.isPrimaria()) {
                chavePrimaria.add(coluna.getNome());
            }
            if (coluna.isNotnull()) {
                definicaoColuna += " NOT NULL";
            }
            if (coluna.isUnique()) {
                definicaoColuna += " UNIQUE";
            }
            if (coluna.isAutoincre()) {
                definicaoColuna += " AUTO_INCREMENT";
            }
            colunaDefinicoes.add(definicaoColuna);
        }
        sql.append(String.join(", ", colunaDefinicoes));
        if (!chavePrimaria.isEmpty()) {
            sql.append(", PRIMARY KEY (").append(String.join(", ", chavePrimaria)).append(")");
        }

        for (ChaveFK chaveEstrangeira : tabela.getChavesEstrangeiras()) {
            chavesEstrangeiras.add("CONSTRAINT " + chaveEstrangeira.getNome() + " FOREIGN KEY (" + chaveEstrangeira.getNome() + ") REFERENCES " + chaveEstrangeira.getReferenciaTabela() + "(" + chaveEstrangeira.getReferenciaColuna() + ")");
        }

        if (!chavesEstrangeiras.isEmpty()) {
            sql.append(", ").append(String.join(", ", chavesEstrangeiras));
        }

        sql.append(")");
        System.out.println("-Comando SQL gerado:-");
        System.out.println(sql.toString());
        System.out.println("-------------------");

        
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql.toString());
        }
    }

    @Override
    public void alterarTabela(String nomeBanco, Tabela tabela, Operacao operacao, Coluna<?> coluna) throws SQLException {
        String sql;
        switch (operacao) {
            case ADD:
                sql = "ALTER TABLE " + nomeBanco + "." + tabela.getNome() + " ADD COLUMN " + coluna.getNome() + " " + coluna.getTipo();
                if ("VARCHAR".equals(coluna.getTipo().toString())) {
                    sql += "(" + coluna.getTamanho() + ")";
                }
                break;
            case DROP:
                sql = "ALTER TABLE " + nomeBanco + "." + tabela.getNome() + " DROP COLUMN " + coluna.getNome();
                break;
            case MODIFY:
                sql = "ALTER TABLE " + nomeBanco + "." + tabela.getNome() + " MODIFY COLUMN " + coluna.getNome() + " " + coluna.getTipo();
                if ("VARCHAR".equals(coluna.getTipo().toString())) {
                    sql += "(" + coluna.getTamanho() + ")";
                }
                break;
            default:
                throw new IllegalArgumentException("Operação inválida: " + operacao);
        }
        System.out.println("-Comando SQL gerado:-");
        System.out.println(sql);
        System.out.println("-------------------");
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
    
    @Override
    public void removerTabela(String nomeBanco, String nomeTabela) throws SQLException {
        String sql = "DROP TABLE " + nomeBanco + "." + nomeTabela;
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
        System.out.println("-Comando SQL gerado:-");
        System.out.println(sql);
        System.out.println("-------------------");
    }

    public void adicionarChaveEstrangeira(String nomeBanco, String nomeTabela, String coluna, String tabelaReferencia, String colunaReferencia) throws SQLException {
        String sql = "ALTER TABLE " + nomeBanco + "." + nomeTabela +
                     " ADD CONSTRAINT FK_" + coluna +
                     " FOREIGN KEY (" + coluna + ") " +
                     " REFERENCES " + nomeBanco + "." + tabelaReferencia + "(" + colunaReferencia + ")";

        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
        System.out.println("-Comando SQL gerado:-");
        System.out.println(sql);
        System.out.println("-------------------");
    }

    @Override
    public List<Tabela> listarTabelas(String nomeBanco) throws SQLException {
        String sql = "SHOW TABLES FROM " + nomeBanco;
        List<Tabela> tabelas = new ArrayList<>();
        try (Connection conn = conexaoBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nomeTabela = rs.getString(1);
                tabelas.add(new Tabela(nomeTabela));
            }
        }
        return tabelas;
    }
}