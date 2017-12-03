package br.edu.uepb.roda_a_roda.model;

import br.edu.uepb.roda_a_roda.exceptions.DAOException;
import br.edu.uepb.roda_a_roda.exceptions.ValidacaoException;
import br.edu.uepb.roda_a_roda.tools.MyDateTime;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Teste dos métodos da class UsuarioDAO
 *
 * @author Magal
 */
public class UsuarioDAOTest {

    static UsuarioDAO usuarioDAO;
    Usuario usuario;

    String data;

    public UsuarioDAOTest() {
    }

    @Before
    public void setUp() {
        data = MyDateTime.getDateTime();
        usuario = new Usuario("Edson", "1234", data);
    }

    @BeforeClass
    public static void setUpClass() {
        usuarioDAO = UsuarioDAO.getInstance();
    }

//    @Test
//    public void loginTest() {
//        try {
//            try {
//                Assert.assertEquals(usuarioDAO.login(usuario.getUsername(), "99999"), null);
//                Assert.assertEquals(usuarioDAO.login(usuario.getUsername(), usuario.getPassword()), usuario);
//            } catch (ValidacaoException ex) {
//                Logger.getLogger(UsuarioDAOTest.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (DAOException ex) {
//            System.out.println("Erro ao logar: " + ex.getMessage());
//            Assert.fail();
//        }
//    }

    @Test
    public void inserirTest() {
        try {
            int id = usuarioDAO.inserir(usuario);
            if (id < 0) {
                Assert.fail();
            }
        } catch (DAOException ex) {
            System.out.println("ERRO AO INSERIR: " + ex.getMessage());
            Assert.fail();
        }
    }
//    @Test
//    public void removerTest() {
//        try {
//            usuarioDAO.remover(usuarioDAO.get(1));
//            Assert.assertEquals(usuarioDAO.get(1), null);
//
//            usuarioDAO.remover(2);
//            Assert.assertEquals(usuarioDAO.get(2), null);
//        } catch (DAOException ex) {
//            System.out.println("ERRO AO REMOVER: " + ex.getMessage());
//            Assert.fail();
//        }
//    }
//    @Test
//    public void atualizarTest() {
//        try {
//            Usuario usuario = new Usuario("Teste" + new Random().nextInt(), "Teste", data);
//            int id = usuarioDAO.inserir(usuario);
//            //Atualizando...
//            Usuario usuarioUp = new Usuario("Teste Update" + new Random().nextInt(), "Senha Update", data);
//            usuarioUp.setId(id);
//            usuarioDAO.atualizar(usuarioUp);
//
//            Assert.assertEquals(usuarioDAO.get(id), usuarioUp);
//            System.out.println("Update: " + usuarioDAO.get(id));
//
//        } catch (DAOException ex) {
//            System.out.println("ERRO AO ATUALIZAR: " + ex.getMessage());
//            Assert.fail();
//        }
//    }
}
