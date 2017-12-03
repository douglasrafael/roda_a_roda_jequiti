package br.edu.uepb.roda_a_roda.model;

import br.edu.uepb.roda_a_roda.exceptions.DAOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Fornece métodos de acesso ao banco de dodos
 *
 * @author Edson
 */
public class JogoSalvoDAO extends DAO<JogoSalvo> {

    private static JogoSalvoDAO instance;

    public JogoSalvoDAO() {
    }

    public static JogoSalvoDAO getInstance() {
        if (instance == null) {
            instance = new JogoSalvoDAO();
        }
        return instance;
    }

    @Override
    public JogoSalvo get(int id) {
        super.connection = new Conexao().getConexao();
        String sql = "SELECT id, estado_palavra, letras_escolhidas, index_roda, pontuacao_npc, jogo_id FROM jogo_salvo WHERE id = ?;";

        JogoSalvo jogoSalvo = null;

        try {
            super.statement = super.connection.prepareStatement(sql);
            super.statement.setInt(1, id);
            super.resultSet = super.statement.executeQuery();

            /**
             * Verifica se a consulta retornou o dado e popula o objeto
             */
            if (resultSet.next()) {
                jogoSalvo = new JogoSalvo();

                jogoSalvo.setId(super.resultSet.getInt(1));
                jogoSalvo.setEstadoPalavra(super.resultSet.getString(2));
                jogoSalvo.setLetrasEscolhidas(super.resultSet.getString(3));
                jogoSalvo.setIndexRoda(super.resultSet.getInt(4));
                jogoSalvo.setPontuacaoNpc(super.resultSet.getInt(5));

                // Pega o Jogo
                Jogo jogo = JogoDAO.getInstance().get(super.resultSet.getInt(6));

                // Seta o objeto jogo ao jogoSalvo
                jogoSalvo.setJogo(jogo);
            }

            // Fecha as conexões abertas
            super.closeConnections();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return jogoSalvo;
    }

    @Override
    public int inserir(JogoSalvo obj) {
        int id = -1;
        super.connection = new Conexao().getConexao();
        String sql = "INSERT INTO jogo_salvo (estado_palavra, letras_escolhidas, "
                + "index_roda, pontuacao_npc, jogo_id) VALUES (?,?,?,?,?);";

        if (obj != null) {
            try {
                super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                super.statement.setString(1, obj.getEstadoPalavra());
                super.statement.setString(2, obj.getLetrasEscolhidas());
                super.statement.setInt(3, obj.getIndexRoda());
                super.statement.setInt(4, obj.getPontuacaoNpc());
                super.statement.setInt(5, obj.getJogo().getId());
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
    public void remover(JogoSalvo obj) {
        if (obj != null) {
            super.connection = new Conexao().getConexao();
            String sql = "DELETE FROM jogo_salvo WHERE id = ?;";
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
        remover(new JogoSalvo(id, null, null, -1, -1, null));
    }

    @Override
    public void atualizar(JogoSalvo obj) {
        if (obj != null) {
            super.connection = new Conexao().getConexao();
            String sql = "UPDATE jogo_salvo SET estado_palavra = ?, letras_escolhidas = ?, "
                    + "index_roda = ?, pontuacao_npc = ?, jogo_id = ? WHERE id = ?;";
            try {
                super.statement = super.connection.prepareStatement(sql);
                super.statement.setString(1, obj.getEstadoPalavra());
                super.statement.setString(2, obj.getLetrasEscolhidas());
                super.statement.setInt(3, obj.getIndexRoda());
                super.statement.setInt(4, obj.getPontuacaoNpc());
                super.statement.setInt(5, obj.getJogo().getId());
                super.statement.setInt(6, obj.getId());
                super.statement.execute();

                // Fecha as conexões abertas
                super.closeConnections();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }

    @Override
    public List<JogoSalvo> getLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
