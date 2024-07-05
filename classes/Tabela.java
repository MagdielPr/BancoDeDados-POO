package classes;

import java.util.ArrayList;
import java.util.List;

public class Tabela {
    private String nome;
    private List<Coluna> colunas;
    private List<ChavePK> chavePrimaria;
    private List<ChaveFK> chavesEstrangeiras;

    public Tabela(String nome) {
        this.nome = nome;
        this.colunas = new ArrayList<>();
        this.chavePrimaria = new ArrayList<>();
        this.chavesEstrangeiras = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Coluna> getColunas() {
        return colunas;
    }

    public void adicionarColuna(Coluna coluna) {
        this.colunas.add(coluna);
        if (coluna.isPrimaria()) {
            this.chavePrimaria.add(new ChavePK(coluna.getNome(), coluna.getTipo()));
        }
    }

    public void removerColuna(Coluna coluna) {
        this.colunas.remove(coluna);
        this.chavePrimaria.removeIf(pk -> pk.getNome().equals(coluna.getNome()));
    }

    public List<ChavePK> getChavePrimaria() {
        return chavePrimaria;
    }

    public void setChavePrimaria(List<ChavePK> chavePrimaria) {
        this.chavePrimaria = chavePrimaria;
        for (ChavePK pk : chavePrimaria) {
            pk.setPrimaria(true);
        }
    }

    public List<ChaveFK> getChavesEstrangeiras() {
        return chavesEstrangeiras;
    }

    public void adicionarChaveEstrangeira(ChaveFK chaveEstrangeira) {
        this.chavesEstrangeiras.add(chaveEstrangeira);
    }

    public void removerChaveEstrangeira(ChaveFK chaveEstrangeira) {
        this.chavesEstrangeiras.remove(chaveEstrangeira);
    }
}