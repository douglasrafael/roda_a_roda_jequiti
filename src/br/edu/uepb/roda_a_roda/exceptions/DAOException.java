package br.edu.uepb.roda_a_roda.exceptions;

/**
 * Exceção do tipo banco de dados
 * 
 * @author Douglas Rafael
 */
public class DAOException extends RuntimeException {

    public DAOException(String message) {
        super(message);
    }
}
