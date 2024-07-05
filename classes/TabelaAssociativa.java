package classes;

import java.util.ArrayList;
import java.util.List;

public class TabelaAssociativa extends Tabela {
    public TabelaAssociativa(String nome) {
        super(nome);
    }

    public void adicionarRelacao(String nomeColuna, String tabelaReferencia, String colunaReferencia) {
        ChaveFK chaveFK = new ChaveFK(nomeColuna, tabelaReferencia, colunaReferencia);
        adicionarChaveEstrangeira(chaveFK);
    }
}