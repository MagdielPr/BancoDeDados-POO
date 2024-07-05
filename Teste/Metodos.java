package Teste;

import java.sql.SQLException;
import java.util.List;

import adapter.BancoDadosAdapter;
import adapter.ClientBancoDadosInterface;
import adapter.ClientColunaInterface;
import adapter.ClientTabelaInterface;
import adapter.ColunaAdapter;
import adapter.TabelaAdapter;
import classes.BancoDados;
import classes.Coluna;
import classes.Tabela;
import classes.TabelaAssociativa;
import classes.Tipo;
import servico.BancoDadosServico;
import servico.ColunaServico;
import servico.TabelaServico;

public class Metodos {
	
    public static final ClientBancoDadosInterface bancoDadosAdapter = new BancoDadosAdapter(new BancoDadosServico());
    public static final ClientTabelaInterface tabelaAdapter = new TabelaAdapter(new TabelaServico());
    public static final ClientColunaInterface colunaAdapter = new ColunaAdapter(new ColunaServico());
	
    public static void criarBancoDados(String nomeBanco) throws SQLException {
        System.out.println("---------------");
        BancoDados bancoDados = new BancoDados(nomeBanco);
        bancoDadosAdapter.criarBancoDados(bancoDados);
        System.out.println("Banco de dados " + nomeBanco + " criado com sucesso!");
    }

    public static Tabela criarTabela(String nomeBanco, String nomeTabela) {
        System.out.println("---------------");
        Tabela tabela = new Tabela(nomeTabela);
        System.out.println("Tabela " + nomeTabela + " criada com sucesso no banco de dados " + nomeBanco);
        return tabela;
    }

    public static TabelaAssociativa criarTabelaAssociativa(String nomeBanco, String nomeTabela) {
        TabelaAssociativa tabelaAssociativa = new TabelaAssociativa(nomeTabela);
        System.out.println("Tabela associativa " + nomeTabela + " criada com sucesso no banco de dados " + nomeBanco);
        return tabelaAssociativa;
    }

    public static void adicionarColuna(Tabela tabela, String nomeColuna, Tipo tipo, int tamanho, boolean isPrimaria, boolean isAutoincre, boolean isNotnull) {
        Coluna coluna = new Coluna(nomeColuna, tipo);
        coluna.setTamanho(tamanho);
        coluna.setPrimaria(isPrimaria);
        coluna.setAutoincre(isAutoincre);
        coluna.setNotnull(isNotnull);
        tabela.adicionarColuna(coluna);
        System.out.println("Coluna " + nomeColuna + " adicionada com sucesso à tabela " + tabela.getNome());
    }

    public static void adicionarColuna(Tabela tabela, String nomeColuna, Tipo tipo, int tamanho) {
        adicionarColuna(tabela, nomeColuna, tipo, tamanho, false, false, false);
        System.out.println("");
    }

    public static void listarColunas(String nomeBanco, String nomeTabela) throws SQLException {
        System.out.println("---------------");
        System.out.println("Colunas da tabela " + nomeTabela + " do banco de dados " + nomeBanco + ":");
        for (Coluna<Tipo> coluna : colunaAdapter.listarColunas(nomeBanco, nomeTabela)) {
            System.out.println("- " + coluna.getNome() + " (" + coluna.getTipo() + ")");
        }
    }

    public static void listarTabelas(String nomeBanco) throws SQLException {
        System.out.println("---------------");
        System.out.println("Tabelas do banco de dados " + nomeBanco + ":");
        List<Tabela> tabelas = tabelaAdapter.listarTabelas(nomeBanco);
        for (Tabela tabela : tabelas) {
            System.out.println("- " + tabela.getNome());
        }
    }

    public static void adicionarChaveEstrangeira(String nomeBanco, String nomeTabela, String coluna, String tabelaReferencia, String colunaReferencia) throws SQLException {
        System.out.println("---------------");
        tabelaAdapter.adicionarChaveEstrangeira(nomeBanco, nomeTabela, coluna, tabelaReferencia, colunaReferencia);
        System.out.println("Chave estrangeira adicionada: " + nomeTabela + "." + coluna + " -> " + tabelaReferencia + "." + colunaReferencia);
    }
    
    public static void listarBancosDados() throws SQLException {
        System.out.println("---------------");
        System.out.println("Bancos de dados existentes:");
        List<BancoDados> bancos = bancoDadosAdapter.listarBancosDados();
        for (BancoDados banco : bancos) {
            System.out.println("- " + banco.getNome());
        }
    }

    public static void removerBancoDados(String nomeBanco) throws SQLException {
        System.out.println("---------------");
        bancoDadosAdapter.removerBancoDados(nomeBanco);
        System.out.println("Banco de dados " + nomeBanco + " removido com sucesso!");
    }
	
}
