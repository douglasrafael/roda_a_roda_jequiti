package br.edu.uepb.roda_a_roda.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import br.edu.uepb.roda_a_roda.exceptions.DAOException;

/**
 * Representa uma instância de uma conexão com o banco de dados
 *
 * @author Douglas Rafael
 */
public class Conexao {

    private final String DB_PATH = "roda-a-roda.db";

    /**
     * Retorna uma instância da conexão com o banco de dados
     *
     * @return Connection
     */
    public Connection getConexao() {
        Connection connection = null;
        try {
            if (connection == null) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            }
        } catch (ClassNotFoundException ex) {
            throw new DAOException("Erro ao conectar O SQLite: " + ex.getMessage());
        } catch (SQLException e) {
            throw new DAOException("Erro ao conectar com o BD: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Fecha conexão com o banco de dados
     *
     * @param connection
     */
    public void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
