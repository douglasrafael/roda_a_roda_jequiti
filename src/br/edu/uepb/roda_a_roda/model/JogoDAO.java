package br.edu.uepb.roda_a_roda.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.uepb.roda_a_roda.exceptions.DAOException;
import java.sql.Statement;

/**
 * @author geovanniovinhas
 *
 * Sep 4, 2016
 */
public class JogoDAO extends DAO<Jogo> {

    private static JogoDAO instance;
    private List<Jogo> listaDeJogos;

    public JogoDAO() {
    }

    public static JogoDAO getInstance() {
        if (instance == null) {
            instance = new JogoDAO();
        }
        return instance;
    }

    @Override
    public Jogo get(int id) {
        super.connection = new Conexao().getConexao();

        String sql = "SELECT jogo.id, jogo.pontuacao, jogo.data_inicio, jogo.data_fim, jogo.estado, jogo.tempo_jogado, "
                + "usuario.id, usuario.nome_usuario,usuario.senha, usuario.data_cadastro, "
                + "palavra.id, palavra.titulo, palavra.dica " + "FROM jogo "
                + "INNER JOIN usuario ON usuario.id = jogo.usuario_id "
                + "INNER JOIN palavra ON palavra.id = jogo.palavra_id " + "WHERE jogo.id = ?";

        Jogo jogo = null;

        try {
            super.statement = super.connection.prepareStatement(sql);
            super.statement.setInt(1, id);
            super.resultSet = super.statement.executeQuery();

            if (resultSet.next()) {
                jogo = new Jogo();
                Palavra palavra = new Palavra();
                Usuario usuario = new Usuario();

                jogo.setId(resultSet.getInt(1));
                jogo.setPontuacao(resultSet.getInt(2));
                jogo.setDataInicio(resultSet.getString(3));
                jogo.setDataFim(resultSet.getString(4));
                jogo.setEstado(resultSet.getBoolean(5));
                jogo.setTempoJogado(resultSet.getInt(6));

                usuario.setId(resultSet.getInt(7));
                usuario.setUsername(resultSet.getString(8));
                usuario.setPassword(resultSet.getString(9));
                usuario.setDataCadastro(resultSet.getString(10));

                palavra.setId(resultSet.getInt(11));
                palavra.setTitulo(resultSet.getString(12));
                palavra.setDica(resultSet.getString(13));

                jogo.setUsuario(usuario);
                jogo.setPalavra(palavra);
            }

            super.closeConnections();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return jogo;
    }

    @Override
    public List<Jogo> getLista() {
        super.connection = new Conexao().getConexao();

        List<Jogo> listaJogos = new ArrayList<>();
        String sql = "SELECT jogo.id, jogo.pontuacao, jogo.data_inicio, jogo.data_fim, jogo.estado, jogo.tempo_jogado, usuario.id, usuario.nome_usuario,usuario.senha, usuario.data_cadastro, palavra.id, palavra.titulo, palavra.dica "
                + "FROM jogo " + "INNER JOIN usuario ON usuario.id = jogo.usuario_id "
                + "INNER JOIN palavra ON palavra.id = jogo.palavra_id ;";

        try {
            super.statement = super.connection.prepareStatement(sql);
            super.resultSet = super.statement.executeQuery();

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                Palavra palavra = new Palavra();

                usuario.setId(resultSet.getInt(7));
                usuario.setUsername(resultSet.getString(8));
                usuario.setPassword(resultSet.getString(9));
                usuario.setDataCadastro(resultSet.getString(10));

                palavra.setId(resultSet.getInt(11));
                palavra.setTitulo(resultSet.getString(12));
                palavra.setDica(resultSet.getString(13));

                Jogo jogo = new Jogo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getBoolean(5), resultSet.getInt(6), usuario, palavra);

                listaJogos.add(jogo);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return listaJogos;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.uepb.roda_a_roda.model.DAO#inserir(java.lang.Object)
     */
    @Override
    public int inserir(Jogo obj) {
        int id = -1;
        super.connection = new Conexao().getConexao();

        String sql = "INSERT INTO jogo (pontuacao, data_inicio, data_fim, estado, tempo_jogado,"
                + " usuario_id, palavra_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        if (obj != null) {
            try {
                super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                super.statement.setInt(1, obj.getPontuacao());
                super.statement.setString(2, obj.getDataInicio());
                super.statement.setString(3, obj.getDataFim());
                super.statement.setBoolean(4, obj.isEstado());
                super.statement.setInt(5, obj.getTempoJogado());
                super.statement.setInt(6, obj.getUsuario().getId());
                super.statement.setInt(7, obj.getPalavra().getId());
                super.statement.execute();
                super.resultSet = super.statement.getGeneratedKeys();

                if (resultSet.next()) {
                    id = super.resultSet.getInt(1);
                }
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }

        }
        return id;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.uepb.roda_a_roda.model.DAO#remover(java.lang.Object)
     */
    @Override
    public void remover(Jogo obj) {
        if (obj != null) {
            super.connection = new Conexao().getConexao();
            String sql = "DELETE FROM jogo WHERE id = ?;";

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

    /*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.uepb.roda_a_roda.model.DAO#remover(int)
     */
    @Override
    public void remover(int id) {
        Jogo jogo = new Jogo();
        jogo.setId(id);
        remover(jogo);
    }

    /*
	 * (non-Javadoc)
	 * @see br.edu.uepb.roda_a_roda.model.DAO#atualizar(java.lang.Object)
     */
    @Override
    public void atualizar(Jogo obj) {
        if (obj != null) {
            super.connection = new Conexao().getConexao();
            String sql = "UPDATE jogo SET pontuacao = ?, data_inicio = ?, data_fim = ?, estado = ?, "
                    + "tempo_jogado = ?, usuario_id = ?, palavra_id = ?" + " WHERE id = ?;";
            try {
                super.statement = super.connection.prepareStatement(sql);
                super.statement.setInt(1, obj.getPontuacao());
                super.statement.setString(2, obj.getDataInicio());
                super.statement.setString(3, obj.getDataFim());
                super.statement.setBoolean(4, obj.isEstado());
                super.statement.setInt(5, obj.getTempoJogado());
                super.statement.setInt(6, obj.getUsuario().getId());
                super.statement.setInt(7, obj.getPalavra().getId());
                super.statement.setInt(8, obj.getId());

                super.statement.execute();

                // Fecha as conexões abertas
                super.closeConnections();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }

    }

    /*
	 * pegar os cincos maiores pontuacoes dos jogos registrados
     */
    public List<Jogo> getRank() {

        super.connection = new Conexao().getConexao();
        List<Jogo> listaJogos = new ArrayList<>();

        String sql = "SELECT jogo.id, jogo.pontuacao, jogo.data_inicio, jogo.data_fim, jogo.estado, jogo.tempo_jogado, usuario.id, usuario.nome_usuario,usuario.senha, usuario.data_cadastro, palavra.id, palavra.titulo, palavra.dica "
                + "FROM jogo " + "INNER JOIN usuario ON usuario.id = jogo.usuario_id "
                + "INNER JOIN palavra ON palavra.id = jogo.palavra_id ORDER BY pontuacao DESC LIMIT 5;";

        try {
            super.statement = super.connection.prepareStatement(sql);
            super.resultSet = super.statement.executeQuery();

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                Palavra palavra = new Palavra();

                usuario.setId(resultSet.getInt(7));
                usuario.setUsername(resultSet.getString(8));
                usuario.setPassword(resultSet.getString(9));
                usuario.setDataCadastro(resultSet.getString(10));

                palavra.setId(resultSet.getInt(11));
                palavra.setTitulo(resultSet.getString(12));
                palavra.setDica(resultSet.getString(13));

                Jogo jogo = new Jogo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getBoolean(5), resultSet.getInt(6), usuario, palavra);

                listaJogos.add(jogo);
            }

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return listaJogos;
    }
}
