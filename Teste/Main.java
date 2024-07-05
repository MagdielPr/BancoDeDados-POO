package Teste;

import adapter.ClientBancoDadosInterface;
import adapter.ClientColunaInterface;
import adapter.ClientTabelaInterface;
import adapter.BancoDadosAdapter;
import adapter.ColunaAdapter;
import adapter.TabelaAdapter;

import classes.Coluna;
import classes.Operacao;
import classes.Tabela;
import classes.TabelaAssociativa;
import classes.Tipo;

import servico.BancoDadosServico;
import servico.ColunaServico;
import servico.TabelaServico;

import java.sql.SQLException;

public class Main {
	
    private static final ClientBancoDadosInterface bancoDadosAdapter = new BancoDadosAdapter(new BancoDadosServico());
    private static final ClientTabelaInterface tabelaAdapter = new TabelaAdapter(new TabelaServico());
    private static final ClientColunaInterface colunaAdapter = new ColunaAdapter(new ColunaServico());

    public static void main(String[] args) {
        try {
            String nomeBanco = "Bancoteste2145";
            Metodos.criarBancoDados(nomeBanco);

            // Teste de Criação de tabelas
            Tabela tabela3 = Metodos.criarTabela(nomeBanco, "Tabela3");
            Metodos.adicionarColuna(tabela3, "ID", Tipo.INT, 0, true, true, true);
            Metodos.adicionarColuna(tabela3, "Nome", Tipo.VARCHAR, 50);
            tabelaAdapter.criarTabela(nomeBanco, tabela3);

            Tabela tabela4 = Metodos.criarTabela(nomeBanco, "Tabela4");
            Metodos.adicionarColuna(tabela4, "ID", Tipo.INT, 0, true, true, true);
            Metodos.adicionarColuna(tabela4, "Descricaozinha", Tipo.VARCHAR, 50);
            tabelaAdapter.criarTabela(nomeBanco, tabela4);

            TabelaAssociativa tabelaAssociativa2 = Metodos.criarTabelaAssociativa(nomeBanco, "tabelaassociativa2");
            Metodos.adicionarColuna(tabelaAssociativa2, "Tabela3_ID", Tipo.INT, 0);
            Metodos.adicionarColuna(tabelaAssociativa2, "Tabela4_ID", Tipo.INT, 0);
            tabelaAssociativa2.adicionarRelacao("Tabela3_ID", "Tabela3", "ID");
            tabelaAssociativa2.adicionarRelacao("Tabela4_ID", "Tabela4", "ID");
            tabelaAdapter.criarTabela(nomeBanco, tabelaAssociativa2);

            Metodos.adicionarChaveEstrangeira(nomeBanco, "Tabela4", "ID", "Tabela3", "ID");

            Metodos.listarTabelas(nomeBanco);

            // Teste de ColunaServico
            Metodos.listarColunas(nomeBanco, "tabelaassociativa2");

            // Teste de alteração de coluna
            Coluna<Tipo> novaColuna = new Coluna<>("Descricaozinha", Tipo.VARCHAR);
            Metodos.tabelaAdapter.alterarTabela(nomeBanco, tabela4, Operacao.MODIFY, novaColuna);

            // Teste de adição de nova coluna
            Coluna<Tipo> colunaAdicional = new Coluna<>("NovaColuna", Tipo.INT);
            Metodos.tabelaAdapter.alterarTabela(nomeBanco, tabela4, Operacao.ADD, colunaAdicional);

            // Verificar as alterações
            Metodos.listarColunas(nomeBanco, "Tabela4");

            // Teste de remoção de coluna
            Metodos.tabelaAdapter.alterarTabela(nomeBanco, tabela4, Operacao.DROP, colunaAdicional);
            
            // Teste de remoção de tabela
            Tabela tabela7 = Metodos.criarTabela(nomeBanco, "Tabela7");
            Metodos.adicionarColuna(tabela7, "ID", Tipo.INT, 0, true, true, true); // Adiciona ao menos uma coluna
            Metodos.tabelaAdapter.criarTabela(nomeBanco, tabela7);
            Metodos.tabelaAdapter.removerTabela(nomeBanco, "Tabela7");
            
            // Teste de remoção de banco
            String nomeBanco2 = "bancotestepkefk";
            Metodos.removerBancoDados(nomeBanco2);
            
            // Verificar a remoção
            Metodos.listarBancosDados();

            System.out.println("Todos os testes concluídos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }


}
