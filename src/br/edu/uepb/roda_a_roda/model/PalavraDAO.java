package br.edu.uepb.roda_a_roda.model;

import br.edu.uepb.roda_a_roda.exceptions.DAOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Fornece métodos de acesso ao banco de dodos
 *
 * @author Dougla Rafael
 */
public class PalavraDAO extends DAO<Palavra> {

    private static PalavraDAO instance;
    private List<Palavra> palavrasSorteadas;

    public PalavraDAO() {
    }

    public static PalavraDAO getInstance() {
        if (instance == null) {
            instance = new PalavraDAO();
        }
        return instance;
    }

    @Override
    public Palavra get(int id) {
        super.connection = new Conexao().getConexao();
        String sql = "SELECT id, titulo, dica FROM palavra WHERE id = ?;";
        Palavra palavra = null;

        try {
            super.statement = super.connection.prepareStatement(sql);
            super.statement.setInt(1, id);
            super.resultSet = super.statement.executeQuery();

            /**
             * Verifica se a consulta retornou o dado e popula o objeto
             */
            if (resultSet.next()) {
                palavra = new Palavra();
                palavra.setId(resultSet.getInt(1));
                palavra.setTitulo(resultSet.getString(2));
                palavra.setDica(resultSet.getString(3));
            }

            // Fecha as conexões abertas
            super.closeConnections();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return palavra;
    }

    @Override
    public List<Palavra> getLista() {
        super.connection = new Conexao().getConexao();
        List<Palavra> listaPalavras = new ArrayList<>();
        String sql = "SELECT id, titulo, dica FROM palavra;";

        try {
            super.statement = super.connection.prepareStatement(sql);
            super.resultSet = super.statement.executeQuery();

            while (resultSet.next()) {
                Palavra palavra = new Palavra(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));

                listaPalavras.add(palavra);
            }

            // Fecha as conexões abertas
            super.closeConnections();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return listaPalavras;
    }

    @Override
    public int inserir(Palavra obj) {
        int id = -1;
        super.connection = new Conexao().getConexao();
        String sql = "INSERT INTO palavra (titulo, dica) VALUES (?,?);";

        if (obj != null) {
            try {
                super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                super.statement.setString(1, obj.getTitulo());
                super.statement.setString(2, obj.getDica());
                super.statement.execute();
                super.resultSet = super.statement.getGeneratedKeys();

                if (resultSet.next()) {
                    id = super.resultSet.getInt(1);
                }

                // Fecha as conexões abertas
                super.closeConnections();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
        
        return id;
    }

    @Override
    public void remover(Palavra obj) {
        if (obj != null) {
            super.connection = new Conexao().getConexao();
            String sql = "DELETE FROM palavra WHERE id = ?;";
            try {
                super.statement = super.connection.prepareStatement(sql);
                super.statement.setInt(1, obj.getId());
                super.statement.execute();

                // Fecha as conexões abertas
                super.closeConnections();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }

    @Override
    public void remover(int id) {
        remover(new Palavra(id, null, null));
    }

    @Override
    public void atualizar(Palavra obj) {
        if (obj != null) {
            super.connection = new Conexao().getConexao();
            String sql = "UPDATE palavra SET titulo = ?, dica = ? WHERE id = ?;";
            try {
                super.statement = super.connection.prepareStatement(sql);
                super.statement.setString(1, obj.getTitulo());
                super.statement.setString(2, obj.getDica());
                super.statement.setInt(3, obj.getId());
                super.statement.execute();

                // Fecha as conexões abertas
                super.closeConnections();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }

    /**
     * Sorteia uma palavra contida no banco de dados. É garantido que palavras
     * sorteadas não sejam novamente escolhidas
     *
     * @return Palavra
     */
    public Palavra sorteaPalavra() {
        if (palavrasSorteadas == null) {
            palavrasSorteadas = new ArrayList<>();
        }
        Palavra palavra = null;

        List<Palavra> palavras = this.getLista();
        Random random = new Random();

        if (!palavras.isEmpty()) {
            do {
                palavra = palavras.get(random.nextInt(palavras.size()));
            } while (palavrasSorteadas.contains(palavra));
            // Palavra sorteada que ainda não foi
            palavrasSorteadas.add(palavra);
        }

        return palavra;
    }

    /**
     * Retorna o total de palavras cadastradas no banco de dados
     *
     * @return int
     */
    public int getTotalDePalavras() {
        super.connection = new Conexao().getConexao();
        String sql = "SELECT COUNT(id) FROM palavra;";
        int total = 0;

        try {
            super.statement = super.connection.prepareStatement(sql);
            super.resultSet = super.statement.executeQuery();

            /**
             * Verifica se a consulta retornou o dado
             */
            if (resultSet.next()) {
                total = resultSet.getInt(1);
            }

            // Fecha as conexões abertas
            super.closeConnections();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return total;
    }
}