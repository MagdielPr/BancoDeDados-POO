package adapter;

import classes.BancoDados;
import servico.BancoDadosServico;

import java.sql.SQLException;
import java.util.List;

public class BancoDadosAdapter implements ClientBancoDadosInterface {
    private final BancoDadosServico bancoDadosServico;

    public BancoDadosAdapter(BancoDadosServico bancoDadosServico) {
        this.bancoDadosServico = bancoDadosServico;
    }

    @Override
    public void criarBancoDados(BancoDados bancoDados) throws SQLException {
        bancoDadosServico.criarBancoDados(bancoDados);
    }

    @Override
    public void removerBancoDados(String nomeBanco) throws SQLException {
        bancoDadosServico.removerBancoDados(nomeBanco);
    }

    @Override
    public List<BancoDados> listarBancosDados() throws SQLException {
        return bancoDadosServico.listarBancosDados();
    }
}
