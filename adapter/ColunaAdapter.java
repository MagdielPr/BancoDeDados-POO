package adapter;

import classes.Coluna;
import classes.Tipo;
import servico.ColunaServico;

import java.sql.SQLException;
import java.util.List;

public class ColunaAdapter implements ClientColunaInterface {
    private final ColunaServico colunaServico;

    public ColunaAdapter(ColunaServico colunaServico) {
        this.colunaServico = colunaServico;
    }

    @Override
    public List<Coluna<Tipo>> listarColunas(String nomeBanco, String nomeTabela) throws SQLException {
        return colunaServico.listarColunas(nomeBanco, nomeTabela);
    }
}
