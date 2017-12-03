package br.edu.uepb.roda_a_roda.model;

import java.util.Objects;

/**
 * Representa uma palavra
 *
 * @author Douglas Rafael
 */
public class Palavra {

    private int id;
    private String titulo;
    private String dica;

    public Palavra() {
    }
    
    public Palavra(int id, String titulo, String dica) {
        this.id = id;
        this.titulo = titulo;
        this.dica = dica;
    }

    public Palavra(String titulo, String dica) {
        this.titulo = titulo;
        this.dica = dica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    @Override
    public int hashCode() {
        return 31 * this.titulo.length() + this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Palavra)) {
            return false;
        }
        Palavra outra = (Palavra) obj;

        return titulo.equals(outra.titulo) && dica.equals(outra.dica);
    }

    @Override
    public String toString() {
        return "Palavra{" + "id=" + id + ", titulo=" + titulo + ", dica=" + dica + '}';
    }
}
