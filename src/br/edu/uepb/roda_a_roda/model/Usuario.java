package br.edu.uepb.roda_a_roda.model;

/**
 * Representa um usuário do jogo
 *
 * @author Douglas Rafael
 */
public class Usuario {

    private int id;
    private String username;
    private String password;
    //jerffeson: estava sem o atributo cadastro
    private String dataCadastro;

    public Usuario() {
    }
    
    //jerffeson: adicionei o parametro dataCadastro no costrutor
    public Usuario(int id, String username, String password, String dataCadastro) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.dataCadastro = dataCadastro;
    }
    
    //jerffeson: adicionei o parametro dataCadastro no costrutor
    public Usuario(String username, String password, String dataCadastro) {
        this.username = username;
        this.password = password;
        this.dataCadastro = dataCadastro;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    // jerffeson: estava sem o metodo getUsername
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public int hashCode() {
        return 31 * this.username.length() + this.id;
    }

    @Override
    public boolean equals(Object user) {
        if (!(user instanceof Usuario)) {
            return false;
        }
        Usuario outro = (Usuario) user;
        return this.username.equals(outro.username) && this.password.equals(outro.password);
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", username=" + username + ", password=" + password + '}';
    }
}