package br.edu.uepb.roda_a_roda.model;

import br.edu.uepb.roda_a_roda.exceptions.DAOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Teste de unidade para a class PalavraDAO
 * 
 * @author Douglas Rafael
 */
public class PalavraDAOTest {

    static PalavraDAO palavraDAO;
    Palavra palavra;

    public PalavraDAOTest() {
    }

    @Before
    public void setUp() {

        System.out.println(new Date().getTime());
        palavra = new Palavra("Autoridade", "Liderança");
    }

    @BeforeClass
    public static void setUpClass() {
        palavraDAO = PalavraDAO.getInstance();
    }

    @Test
    public void inserirTest() {
        try {
            int id = palavraDAO.inserir(palavra);
            if (id < 0) {
                Assert.fail();
            }
        } catch (DAOException ex) {
            System.out.println("ERRO AO INSERIR: " + ex.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void listarTest() {
        try {
            List<Palavra> listaDePalavras = palavraDAO.getLista();

            if (listaDePalavras.size() <= 0) {
                Assert.fail();
            }
            System.out.println(Arrays.toString(listaDePalavras.toArray()));
        } catch (DAOException ex) {
            System.out.println("ERRO AO INSERIR: " + ex.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void removerTest() {
        try {
            palavraDAO.remover(palavraDAO.get(1));
            Assert.assertEquals(palavraDAO.get(1), null);

            palavraDAO.remover(2);
            Assert.assertEquals(palavraDAO.get(2), null);
        } catch (DAOException ex) {
            System.out.println("ERRO AO REMOVER: " + ex.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void atualizarTest() {
        try {
            Palavra palavra = new Palavra("Teste" + new Random().nextInt(), "Teste");
            int id = palavraDAO.inserir(palavra);

            // Atualizando...
            Palavra palavraUp = new Palavra("Teste Updadte" + new Random().nextInt(), "Dica Update");
            palavraUp.setId(id);
            palavraDAO.atualizar(palavraUp);

            Assert.assertEquals(palavraDAO.get(id), palavraUp);
            System.out.println("Update: " + palavraDAO.get(id));
        } catch (DAOException ex) {
            System.out.println("ERRO AO ATUALIZAR: " + ex.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void sorteaPalavraTest() {
        try {
            int i = 0;
            List<Palavra> palavrasSort = new ArrayList<>();
            while (i < palavraDAO.getTotalDePalavras()) {
                Palavra palavra = palavraDAO.sorteaPalavra();
                if (palavra != null) {
                    if (palavrasSort.contains(palavra)) {
                        Assert.fail(); // Teeste falhou. Palavras se repetiram
                    }

                    palavrasSort.add(palavra);
                }
                i++;
            }
            // Total de palavras sorteadas deve ser igual ao total cadastrada
            Assert.assertEquals(palavrasSort.size(), palavraDAO.getTotalDePalavras());

            System.out.println("SORT: " + Arrays.toString(palavrasSort.toArray()));
        } catch (DAOException ex) {
            System.out.println("ERRO AO SORTEAR: " + ex.getMessage());
        }
    }

//    @Test
//    public void getTest() {
//        Palavra p;
//        try {
//            p = palavraDAO.get(6549); // nao existe
//            Assert.assertEquals(p, null);
//
//            p = palavraDAO.get(1);
//            Assert.assertEquals(p, palavra);
//        } catch (DAOException ex) {
//            System.out.println("ERRO NA CONSULTA: " + ex.getMessage());
//        }
//    }
}
