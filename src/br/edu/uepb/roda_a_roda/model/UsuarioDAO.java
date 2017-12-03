package br.edu.uepb.roda_a_roda.model;

import br.edu.uepb.roda_a_roda.exceptions.DAOException;
import br.edu.uepb.roda_a_roda.exceptions.ValidacaoException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Fornece métodos de acesso ao banco de dodos
 *
 * @author Jerffeson
 */
public class UsuarioDAO extends DAO<Usuario> {

    private static UsuarioDAO instance;

    public UsuarioDAO() {
    }

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }

        return instance;
    }

    /**
     * Autentica um usuário no jogo. Se usuário existir de acordo com username e
     * password o Usuario é retornado, caso contrário é retornado null
     *
     * @param username
     * @param password
     * @return
     * @throws br.edu.uepb.roda_a_roda.exceptions.ValidacaoException
     */
    public Usuario login(String username, String password) throws ValidacaoException {
        super.connection = new Conexao().getConexao();
        String sql = "SELECT id, nome_usuario, senha, data_cadastro FROM usuario WHERE nome_usuario = ?;";

        Usuario usuario = null;

        try {
            super.statement = super.connection.prepareStatement(sql);
            super.statement.setString(1, username);
            super.resultSet = super.statement.executeQuery();

            if (resultSet.next()) {
                // Compara a senha da tabela com a senha fornecida no metodo
                if (resultSet.getString(3).equals(password)) {
                    usuario = new Usuario();
                    usuario.setId(resultSet.getInt(1));
                    usuario.setUsername(resultSet.getString(2));
                    usuario.setPassword(resultSet.getString(3));
                    usuario.setDataCadastro(resultSet.getString(4));
                } else {
                    throw new ValidacaoException("Senha inválida!");
                }
            } else {
                throw new ValidacaoException("Username incorreto!");
            }

            // Fecha as conexões abertas
            super.closeConnections();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return usuario;
    }

    @Override
    public Usuario get(int id) {
        super.connection = new Conexao().getConexao();
        String sql = "SELECT id, nome_usuario, senha, data_cadastro FROM usuario WHERE id = ?;";

        Usuario usuario = null;

        try {
            super.statement = super.connection.prepareStatement(sql);
            super.statement.setInt(1, id);
            super.resultSet = super.statement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setId(resultSet.getInt(1));
                usuario.setUsername(resultSet.getString(2));
                usuario.setPassword(resultSet.getString(3));
                usuario.setDataCadastro(resultSet.getString(4));
            }

            // Fecha as conexções abertas
            super.closeConnections();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return usuario;
    }

    @Override
    public List<Usuario> getLista() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int inserir(Usuario obj) {
        int id = -1;
        super.connection = new Conexao().getConexao();
        String sql = "INSERT INTO usuario (nome_usuario, senha, data_cadastro) VALUES (?,?,?);";

        if (obj != null) {
            try {
                super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                super.statement.setString(1, obj.getUsername());
                super.statement.setString(2, obj.getPassword());
                super.statement.setString(3, obj.getDataCadastro());
                super.statement.execute();
                super.resultSet = super.statement.getGeneratedKeys();

                if (resultSet.next()) {
                    id = super.resultSet.getInt(1);
                }

                // Fecha as conexções abertas
                super.closeConnections();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }

        return id;
    }

    @Override
    public void remover(Usuario obj) {
        if (obj != null) {
            super.connection = new Conexao().getConexao();
            String sql = "DELETE FROM usuario WHERE id = ?;";
            try {
                super.statement = super.connection.prepareStatement(sql);
                super.statement.setInt(1, obj.getId());
                super.statement.execute();

                // Fecha as conexções abertas
                super.closeConnections();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }

        }
    }

    @Override
    public void remover(int id) {
        remover(new Usuario(id, null, null, null));
    }

    @Override
    public void atualizar(Usuario obj) {
        if (obj != null) {
            super.connection = new Conexao().getConexao();
            String sql = "UPDATE usuario SET nome_usuario = ?, senha = ? WHERE id = ?;";
            try {
                super.statement = super.connection.prepareStatement(sql);
                super.statement.setString(1, obj.getUsername());
                super.statement.setString(2, obj.getPassword());
                super.statement.setInt(3, obj.getId());
                super.statement.execute();

                // Fecha as conexões abertas
                super.closeConnections();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }

}
