/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

import java.sql.SQLException;

/**
 *
 * @author cdi415
 */
public class DuplicateConstraintSQLException extends SQLException{

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public DuplicateConstraintSQLException() {
        super();
    }
    
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public DuplicateConstraintSQLException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * java.security.PrivilegedActionException}).
     *
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since  1.4
     */
    public DuplicateConstraintSQLException(Throwable cause) {
        super(cause);
    }
    
    /**
     * Constructs a new exception with the specified cause, a detail
     * message and the SQL state.
     * This constructor is useful for exceptions that needs
     * to preserve the SQL state
     * @param ex The SQLException wich caused the exception to occurre
     */
    public DuplicateConstraintSQLException(SQLException ex){
        super(ex.getMessage(), ex.getSQLState(), 0, ex.getCause());
    }
    
}