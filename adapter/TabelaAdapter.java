package adapter;

import classes.Coluna;
import classes.Tabela;
import classes.Operacao;
import classes.Tipo;
import servico.TabelaServico;

import java.sql.SQLException;
import java.util.List;

public class TabelaAdapter implements ClientTabelaInterface {
    private final TabelaServico tabelaServico;

    public TabelaAdapter(TabelaServico tabelaServico) {
        this.tabelaServico = tabelaServico;
    }

    @Override
    public void criarTabela(String nomeBanco, Tabela tabela) throws SQLException {
        tabelaServico.criarTabela(nomeBanco, tabela);
    }

    @Override
    public List<Tabela> listarTabelas(String nomeBanco) throws SQLException {
        return tabelaServico.listarTabelas(nomeBanco);
    }

    @Override
    public void adicionarChaveEstrangeira(String nomeBanco, String nomeTabela, String coluna, String tabelaReferencia, String colunaReferencia) throws SQLException {
        tabelaServico.adicionarChaveEstrangeira(nomeBanco, nomeTabela, coluna, tabelaReferencia, colunaReferencia);
    }
    
    @Override
    public void alterarTabela(String nomeBanco, Tabela tabela, Operacao operacao, Coluna<Tipo> coluna) throws SQLException {
        tabelaServico.alterarTabela(nomeBanco, tabela, operacao, coluna);
    }

    @Override
    public void removerTabela(String nomeBanco, String nomeTabela) throws SQLException {
        tabelaServico.removerTabela(nomeBanco, nomeTabela);
    }
}
