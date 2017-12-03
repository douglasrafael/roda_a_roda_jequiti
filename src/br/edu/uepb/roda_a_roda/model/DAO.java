package br.edu.uepb.roda_a_roda.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Representa um DAO
 *
 * @author Douglas Rafael
 * @param <T> Tipo do objeto
 */
public abstract class DAO<T> {

    public Connection connection;
    public PreparedStatement statement;
    public ResultSet resultSet;

    /**
     * Fecha todas conexões que foram aberats na consulta
     *
     * @throws SQLException
     */
    public void closeConnections() throws SQLException {
//        if (connection != null) {
//            connection.close();
//        }
        if (statement != null) {
            statement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

    /**
     * Seleciona o obejeto de acordo com seu id. Caso não seja encontrado é
     * retornado null
     *
     * @param id O id do objeto no banco de dados
     * @return T O objeto
     */
    public abstract T get(int id);

    /**
     * Retorna uma lista com todos objetos cadastrados no banco de dados
     *
     * @return List<T> Lista de objetos
     */
    public abstract List<T> getLista();

    /**
     * Insere um novo objeto no banco de dados. Se a operação for realizada com
     * sucesso é retornado o id
     *
     * @param obj O objeto a ser inserido
     * @return int
     */
    public abstract int inserir(T obj);

    /**
     * Remove o objeto do banco de dados. Se a operação for realizada com
     * sucesso é retornado true, caso contrário false
     *
     * @param obj O objeto a ser removido
     */
    public abstract void remover(T obj);

    /**
     * Remove o objeto do banco de dados. Se a operação for realizada com
     * sucesso é retornado true, caso contrário false
     *
     * @param id O id do objeto a ser removido
     */
    public abstract void remover(int id);

    /**
     * Atualiza os dados do objeto no banco de dados. Se a operação for
     * realizada com sucesso é retornado true, caso contrário false
     *
     * @param obj O objeto com os dados sa serem atualizados
     */
    public abstract void atualizar(T obj);
}
