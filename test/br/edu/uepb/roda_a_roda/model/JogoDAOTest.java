package br.edu.uepb.roda_a_roda.model;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.uepb.roda_a_roda.exceptions.DAOException;

/**
 * @author geovanniovinhas
 *
 * Sep 4, 2016
 */
public class JogoDAOTest {

    static JogoDAO jogoDAO;
    Jogo jogo;

    public JogoDAOTest() {
    }

    @Before
    public void setUp() {
        jogo = new Jogo(1, 234, "2016-09-04 22:42:49", "2016-09-04 22:42:49", true, 30,
                new Usuario(1, "Edson", "123", "2016-09-04 22:40:10"), new Palavra(1, "uepb", "unioew"));
    }

    @BeforeClass
    public static void setUpClass() {
        jogoDAO = JogoDAO.getInstance();
    }

    @Test
    public void getTest() {
        try {
            Assert.assertEquals(jogoDAO.get(1), jogo);
            Assert.assertEquals(jogoDAO.get(2000), null);
        } catch (DAOException ex) {
            Assert.fail();
            System.out.println("ERRO AO SELECIONAR: " + ex.getMessage());
        }

    }

    @Test
    public void getLista() {
        try {
            List<Jogo> listaDeJogos = jogoDAO.getLista();

            if (listaDeJogos.size() <= 0) {
                Assert.fail();
            }
            // System.out.println(Arrays.toString(listaDeJogos.toArray()));
        } catch (DAOException ex) {
            Assert.fail();
            System.out.println("ERRO AO SELECIONAR: " + ex.getMessage());
        }

    }

//    @Test
//    public void inserirTest() {
//        try {
//            int id = jogoDAO.inserir(jogo);
//            if (id < 0) {
//                Assert.fail();
//            }
//        } catch (DAOException ex) {
//            System.out.println("ERRO AO INSERIR: " + ex.getMessage());
//            Assert.fail();
//        }
//    }
//    @Test
//    public void removerTest() {
//        try {
//            jogoDAO.remover(jogoDAO.get(2));
//            Assert.assertEquals(jogoDAO.get(2), null);
//
//            jogoDAO.remover(3);
//            Assert.assertEquals(jogoDAO.get(3), null);
//        } catch (DAOException ex) {
//            System.out.println("ERRO AO REMOVER: " + ex.getMessage());
//            Assert.fail();
//        }
//    }
    
//    @Test
//    public void atualizarTest() {
//        try {
//            Usuario usuario = new Usuario(1, "Edson", "123", "12:23:4");
//            Palavra palavra = new Palavra(1, "uepb", "Teste");
//            jogo.setUsuario(usuario);
//            jogo.setPalavra(palavra);
//            int id = jogoDAO.inserir(jogo);
//
//            // Atualizando...
//            Jogo jogoNovo = new Jogo(1215156, "2016-09-05 22:42:49", "2016-09-06 22:42:49", true, 30, usuario, palavra);
//            jogoNovo.setId(id);
//            jogoDAO.atualizar(jogoNovo);
//
//            Assert.assertEquals(jogoDAO.get(id), jogoNovo);
//            System.out.println("Update: " + jogoDAO.get(id));
//        } catch (DAOException ex) {
//            System.out.println("ERRO AO ATUALIZAR: " + ex.getMessage());
//            Assert.fail();
//        }
//    }

    @Test
    public void rankTest() {
        try {
            List<Jogo> listaDeJogos = jogoDAO.getRank();

            if (listaDeJogos.size() <= 0) {
                Assert.fail();
            }
            System.out.println(Arrays.toString(listaDeJogos.toArray()));
            System.out.println(listaDeJogos.size());
        } catch (DAOException ex) {
            System.out.println("ERRO AO SELECIONAR: " + ex.getMessage());
            Assert.fail();
        }
    }
}
