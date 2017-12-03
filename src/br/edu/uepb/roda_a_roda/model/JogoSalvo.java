package br.edu.uepb.roda_a_roda.model;

/**
 * Representa um usuário do JogoSalvo
 *
 * @author Edson
 */
public class JogoSalvo {

    private int id;
    private String estadoPalavra;
    private String letrasEscolhidas;
    private int indexRoda;
    private int pontuacaoNpc;
    private Jogo jogo;

    public JogoSalvo() {
    }

    /**
     * Construtor para um jogo salvo existente
     *
     * @param id
     * @param estadoPalavra
     * @param letrasEscolhidas
     * @param indexRoda
     * @param pontuacao
     * @param jogo
     */
    public JogoSalvo(int id, String estadoPalavra, String letrasEscolhidas, int indexRoda, int pontuacao, Jogo jogo) {
        this.id = id;
        this.estadoPalavra = estadoPalavra;
        this.letrasEscolhidas = letrasEscolhidas;
        this.indexRoda = indexRoda;
        this.pontuacaoNpc = pontuacao;
        this.jogo = jogo;
    }

    /**
     * Construtor para um novo jogo salvo
     *
     * @param estadoPalavra
     * @param letrasEscolhidas
     * @param indexRoda
     * @param pontuacao
     * @param jogo
     */
    public JogoSalvo(String estadoPalavra, String letrasEscolhidas, int indexRoda, int pontuacao, Jogo jogo) {
        this.estadoPalavra = estadoPalavra;
        this.letrasEscolhidas = letrasEscolhidas;
        this.indexRoda = indexRoda;
        this.pontuacaoNpc = pontuacao;
        this.jogo = jogo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstadoPalavra() {
        return estadoPalavra;
    }

    public void setEstadoPalavra(String estadoPalavra) {
        this.estadoPalavra = estadoPalavra;
    }

    public String getLetrasEscolhidas() {
        return letrasEscolhidas;
    }

    public void setLetrasEscolhidas(String letrasEscolhidas) {
        this.letrasEscolhidas = letrasEscolhidas;
    }

    public int getIndexRoda() {
        return indexRoda;
    }

    public void setIndexRoda(int indexRoda) {
        this.indexRoda = indexRoda;
    }

    public int getPontuacaoNpc() {
        return pontuacaoNpc;
    }

    public void setPontuacaoNpc(int pontuacaoNpc) {
        this.pontuacaoNpc = pontuacaoNpc;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof JogoSalvo)) {
            return false;
        }
        JogoSalvo outra = (JogoSalvo) obj;

        return outra.id == id;
    }

    @Override
    public String toString() {
        return "JogoSalvo{" + "id=" + id + ", estadoPalavra=" + estadoPalavra
                + ", letrasEscolhidas=" + letrasEscolhidas + ", indexRoda="
                + indexRoda + ", pontuacaoNpc=" + pontuacaoNpc + ", jogo=" + jogo + '}';
    }

}
