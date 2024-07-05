package adapter;

import classes.Coluna;
import classes.Operacao;
import classes.Tabela;
import classes.TabelaAssociativa;
import classes.Tipo;

import java.sql.SQLException;
import java.util.List;

public interface ClientTabelaInterface {
    void criarTabela(String nomeBanco, Tabela tabela) throws SQLException;
    List<Tabela> listarTabelas(String nomeBanco) throws SQLException;
    void adicionarChaveEstrangeira(String nomeBanco, String nomeTabela, String coluna, String tabelaReferencia, String colunaReferencia) throws SQLException;
	void removerTabela(String nomeBanco, String string) throws SQLException;;
	void alterarTabela(String nomeBanco, Tabela tabela, Operacao operacao, Coluna<Tipo> coluna) throws SQLException;
}
