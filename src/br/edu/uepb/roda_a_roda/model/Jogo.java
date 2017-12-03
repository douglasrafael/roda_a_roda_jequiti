package br.edu.uepb.roda_a_roda.model;

/**
 * @author geovanniovinhas
 *
 * Sep 2, 2016
 */
public class Jogo {

    private int id;
    private int pontuacao;
    private String dataInicio;
    private String dataFim;
    private boolean estado;
    private int tempoJogado;
    private Usuario usuario;
    private Palavra palavra;

    public Jogo() {
    }

    public Jogo(int id, int pontuacao, String dataInicio, String dataFim, boolean estado, int tempoJogado, Usuario usuario, Palavra palavra) {
        this.id = id;
        this.pontuacao = pontuacao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.estado = estado;
        this.tempoJogado = tempoJogado;
        this.usuario = usuario;
        this.palavra = palavra;
    }

    public Jogo(int pontuacao, String dataInicio, String dataFim, boolean estado, int tempoJogado, Usuario usuario, Palavra palavra) {
        this.pontuacao = pontuacao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.estado = estado;
        this.tempoJogado = tempoJogado;
        this.usuario = usuario;
        this.palavra = palavra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getTempoJogado() {
        return tempoJogado;
    }

    public void setTempoJogado(int tempoJogado) {
        this.tempoJogado = tempoJogado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Palavra getPalavra() {
        return palavra;
    }

    public void setPalavra(Palavra palavra) {
        this.palavra = palavra;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(pontuacao);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Jogo)) {
            return false;
        }
        Jogo outro = (Jogo) obj;
        return outro.id == id;
    }

    @Override
    public String toString() {
        return "Jogo{" + "id=" + id + ", pontuacao=" + pontuacao + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", estado=" + estado + ", tempoJogado=" + tempoJogado + ", usuario=" + usuario + ", palavra=" + palavra + '}';
    }
}
