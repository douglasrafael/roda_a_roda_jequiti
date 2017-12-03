package br.edu.uepb.roda_a_roda.controller;

import br.edu.uepb.roda_a_roda.exceptions.ValidacaoException;
import br.edu.uepb.roda_a_roda.model.Jogo;
import br.edu.uepb.roda_a_roda.model.JogoDAO;
import br.edu.uepb.roda_a_roda.model.JogoSalvoDAO;
import br.edu.uepb.roda_a_roda.model.PalavraDAO;
import br.edu.uepb.roda_a_roda.model.Usuario;
import br.edu.uepb.roda_a_roda.model.UsuarioDAO;
import java.util.List;

/**
 * Fornece instância dos métodos dos DAOS
 *
 * @author Douglas Rafael
 */
public class Gerenciador {

    private UsuarioDAO usuarioDAO;
    private JogoDAO jogoDAO;
    private PalavraDAO palavraDAO;
    private JogoSalvoDAO jogoSalvoDAO;

    public Gerenciador() {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAO();
        }
        if (jogoDAO == null) {
            jogoDAO = new JogoDAO();
        }
        if (jogoSalvoDAO == null) {
            jogoSalvoDAO = new JogoSalvoDAO();
        }
        if (palavraDAO == null) {
            palavraDAO = new PalavraDAO();
        }
    }

    public Usuario autenticaUsuario(String username, String password) throws ValidacaoException {
        return usuarioDAO.login(username, password);
    }

    public Usuario getUsuario(int id) {
        return usuarioDAO.get(id);
    }

    public int inserirUsuario(Usuario usuario) {
        return usuarioDAO.inserir(usuario);
    }
    
    /*
    *    jogoDAO
    */
    public List<Jogo> getRank(){
        return jogoDAO.getRank();
    }
}
