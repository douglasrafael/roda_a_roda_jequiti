package br.edu.uepb.roda_a_roda.model;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Teste da classe Conexao
 * 
 * @author Douglas Rafael
 */
public class ConexaoTest {

    Connection con;

    public ConexaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        
    }

    @Test
    public void getConexao() {
        try {
            con = new Conexao().getConexao();
            Assert.assertTrue(con.isValid(10));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
