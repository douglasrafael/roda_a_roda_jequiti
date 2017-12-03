package br.edu.uepb.roda_a_roda.model;

import br.edu.uepb.roda_a_roda.exceptions.DAOException;
import br.edu.uepb.roda_a_roda.tools.MyDateTime;
import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Teste de unidade para a class JogoSalvoDAO
 *
 * @author Edson
 */
public class JogoSalvoDAOTest {

    static JogoSalvoDAO jogoSalvoDao;
    JogoSalvo jogoSalvo;
    Jogo jogo;

    public JogoSalvoDAOTest() {
    }

    @Before
    public void setUp() {
        jogo = new Jogo();
        jogo.setId(1);
        jogoSalvo = new JogoSalvo("_ A _ _ A", "A,M,P", 6, 500, jogo);
    }

    @BeforeClass
    public static void setUpClass() {
        jogoSalvoDao = JogoSalvoDAO.getInstance();
    }

    @Test
    public void getTest() {
        try {
            jogoSalvo.setId(1);
            Assert.assertEquals(jogoSalvoDao.get(1), jogoSalvo);         
            Assert.assertEquals(jogoSalvoDao.get(1000), null);         
            
            System.out.println(jogoSalvoDao.get(1));
        } catch (DAOException ex) {
            System.out.println("ERRO AO SELECIONAR: " + ex.getMessage());
            Assert.fail();
        }
    }
//    @Test
//    public void inserirTest() {
//        try {
//            int id = jogoSalvoDao.inserir(jogoSalvo);
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
//            jogoSalvoDao.remover(jogoSalvoDao.get(1));
//            Assert.assertEquals(jogoSalvoDao.get(1), null);
//
//            jogoSalvoDao.remover(2);
//            Assert.assertEquals(jogoSalvoDao.get(2), null);
//        } catch (DAOException ex) {
//            System.out.println("ERRO AO REMOVER: " + ex.getMessage());
//            Assert.fail();
//        }
//    }

    @Test
    public void atualizarTest() {
        try {
            // Preparando o objeto
            Palavra palavra = new Palavra(1, "Paralepipedo", "Pedra");
            Usuario usuario = new Usuario(1, "edson", "123", MyDateTime.getDateTime());
            jogo.setUsuario(usuario);
            jogo.setPalavra(palavra);
            jogoSalvo.setJogo(jogo);

            // Insere o objeto a ser atualizado e pega o id dele
            int id = jogoSalvoDao.inserir(jogoSalvo);

            // Atualizando...
            JogoSalvo jogoSalvoUp = new JogoSalvo("JogoSalvoAtualizado" + new Random().nextInt(), "A,N", 0, 100, jogo);
            jogoSalvoUp.setId(id);
            jogoSalvoDao.atualizar(jogoSalvoUp);

            System.out.println(jogoSalvoDao.get(13));
            Assert.assertEquals(jogoSalvoDao.get(id), jogoSalvoUp);
        } catch (DAOException ex) {
            System.out.println("ERRO AO ATUALIZAR: " + ex.getMessage());
            Assert.fail();
        }
    }
}
