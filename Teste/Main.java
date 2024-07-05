package Teste;

import adapter.ClientBancoDadosInterface;
import adapter.ClientColunaInterface;
import adapter.ClientTabelaInterface;
import adapter.BancoDadosAdapter;
import adapter.ColunaAdapter;
import adapter.TabelaAdapter;

import classes.BancoDados;
import classes.Coluna;
import classes.Operacao;
import classes.Tabela;
import classes.TabelaAssociativa;
import classes.Tipo;

import servico.BancoDadosServico;
import servico.ColunaServico;
import servico.TabelaServico;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final ClientBancoDadosInterface bancoDadosAdapter = new BancoDadosAdapter(new BancoDadosServico());
    private static final ClientTabelaInterface tabelaAdapter = new TabelaAdapter(new TabelaServico());
    private static final ClientColunaInterface colunaAdapter = new ColunaAdapter(new ColunaServico());

    public static void main(String[] args) {
        try {
            String nomeBanco = "Bancoteste2145";
            criarBancoDados(nomeBanco);

            // Teste de Criação de tabelas
            Tabela tabela3 = criarTabela(nomeBanco, "Tabela3");
            adicionarColuna(tabela3, "ID", Tipo.INT, 0, true, true, true);
            adicionarColuna(tabela3, "Nome", Tipo.VARCHAR, 50);
            tabelaAdapter.criarTabela(nomeBanco, tabela3);

            Tabela tabela4 = criarTabela(nomeBanco, "Tabela4");
            adicionarColuna(tabela4, "ID", Tipo.INT, 0, true, true, true);
            adicionarColuna(tabela4, "Descricaozinha", Tipo.VARCHAR, 50);
            tabelaAdapter.criarTabela(nomeBanco, tabela4);

            TabelaAssociativa tabelaAssociativa2 = criarTabelaAssociativa(nomeBanco, "tabelaassociativa2");
            adicionarColuna(tabelaAssociativa2, "Tabela3_ID", Tipo.INT, 0);
            adicionarColuna(tabelaAssociativa2, "Tabela4_ID", Tipo.INT, 0);
            tabelaAssociativa2.adicionarRelacao("Tabela3_ID", "Tabela3", "ID");
            tabelaAssociativa2.adicionarRelacao("Tabela4_ID", "Tabela4", "ID");
            tabelaAdapter.criarTabela(nomeBanco, tabelaAssociativa2);

            adicionarChaveEstrangeira(nomeBanco, "Tabela4", "ID", "Tabela3", "ID");

            listarTabelas(nomeBanco);

            // Teste de ColunaServico
            listarColunas(nomeBanco, "tabelaassociativa2");

            // Teste de alteração de coluna
            Coluna<Tipo> novaColuna = new Coluna<>("Descricaozinha", Tipo.VARCHAR);
            tabelaAdapter.alterarTabela(nomeBanco, tabela4, Operacao.MODIFY, novaColuna);

            // Teste de adição de nova coluna
            Coluna<Tipo> colunaAdicional = new Coluna<>("NovaColuna", Tipo.INT);
            tabelaAdapter.alterarTabela(nomeBanco, tabela4, Operacao.ADD, colunaAdicional);

            // Verificar as alterações
            listarColunas(nomeBanco, "Tabela4");

            // Teste de remoção de coluna
            tabelaAdapter.alterarTabela(nomeBanco, tabela4, Operacao.DROP, colunaAdicional);
            
            // Teste de remoção de tabela
            Tabela tabela7 = criarTabela(nomeBanco, "Tabela7");
            adicionarColuna(tabela7, "ID", Tipo.INT, 0, true, true, true); // Adiciona ao menos uma coluna
            tabelaAdapter.criarTabela(nomeBanco, tabela7);
            tabelaAdapter.removerTabela(nomeBanco, "Tabela7");
            
            // Teste de remoção de banco
            String nomeBanco2 = "bancotestepkefk";
            removerBancoDados(nomeBanco2);
            
            // Verificar a remoção
            listarBancosDados();

            System.out.println("Todos os testes concluídos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private static void criarBancoDados(String nomeBanco) throws SQLException {
        System.out.println("---------------");
        BancoDados bancoDados = new BancoDados(nomeBanco);
        bancoDadosAdapter.criarBancoDados(bancoDados);
        System.out.println("Banco de dados " + nomeBanco + " criado com sucesso!");
    }

    private static Tabela criarTabela(String nomeBanco, String nomeTabela) {
        System.out.println("---------------");
        Tabela tabela = new Tabela(nomeTabela);
        System.out.println("Tabela " + nomeTabela + " criada com sucesso no banco de dados " + nomeBanco);
        return tabela;
    }

    private static TabelaAssociativa criarTabelaAssociativa(String nomeBanco, String nomeTabela) {
        TabelaAssociativa tabelaAssociativa = new TabelaAssociativa(nomeTabela);
        System.out.println("Tabela associativa " + nomeTabela + " criada com sucesso no banco de dados " + nomeBanco);
        return tabelaAssociativa;
    }

    private static void adicionarColuna(Tabela tabela, String nomeColuna, Tipo tipo, int tamanho, boolean isPrimaria, boolean isAutoincre, boolean isNotnull) {
        Coluna coluna = new Coluna(nomeColuna, tipo);
        coluna.setTamanho(tamanho);
        coluna.setPrimaria(isPrimaria);
        coluna.setAutoincre(isAutoincre);
        coluna.setNotnull(isNotnull);
        tabela.adicionarColuna(coluna);
        System.out.println("Coluna " + nomeColuna + " adicionada com sucesso à tabela " + tabela.getNome());
    }

    private static void adicionarColuna(Tabela tabela, String nomeColuna, Tipo tipo, int tamanho) {
        adicionarColuna(tabela, nomeColuna, tipo, tamanho, false, false, false);
        System.out.println("");
    }

    private static void listarColunas(String nomeBanco, String nomeTabela) throws SQLException {
        System.out.println("---------------");
        System.out.println("Colunas da tabela " + nomeTabela + " do banco de dados " + nomeBanco + ":");
        for (Coluna<Tipo> coluna : colunaAdapter.listarColunas(nomeBanco, nomeTabela)) {
            System.out.println("- " + coluna.getNome() + " (" + coluna.getTipo() + ")");
        }
    }

    private static void listarTabelas(String nomeBanco) throws SQLException {
        System.out.println("---------------");
        System.out.println("Tabelas do banco de dados " + nomeBanco + ":");
        List<Tabela> tabelas = tabelaAdapter.listarTabelas(nomeBanco);
        for (Tabela tabela : tabelas) {
            System.out.println("- " + tabela.getNome());
        }
    }

    private static void adicionarChaveEstrangeira(String nomeBanco, String nomeTabela, String coluna, String tabelaReferencia, String colunaReferencia) throws SQLException {
        System.out.println("---------------");
        tabelaAdapter.adicionarChaveEstrangeira(nomeBanco, nomeTabela, coluna, tabelaReferencia, colunaReferencia);
        System.out.println("Chave estrangeira adicionada: " + nomeTabela + "." + coluna + " -> " + tabelaReferencia + "." + colunaReferencia);
    }
    
    private static void listarBancosDados() throws SQLException {
        System.out.println("---------------");
        System.out.println("Bancos de dados existentes:");
        List<BancoDados> bancos = bancoDadosAdapter.listarBancosDados();
        for (BancoDados banco : bancos) {
            System.out.println("- " + banco.getNome());
        }
    }

    private static void removerBancoDados(String nomeBanco) throws SQLException {
        System.out.println("---------------");
        bancoDadosAdapter.removerBancoDados(nomeBanco);
        System.out.println("Banco de dados " + nomeBanco + " removido com sucesso!");
    }
}
