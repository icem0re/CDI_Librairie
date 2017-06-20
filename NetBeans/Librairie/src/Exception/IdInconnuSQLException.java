/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

import java.sql.SQLException;

/**
 *
 * @author cdi411
 */
public class IdInconnuSQLException extends SQLException{

    public IdInconnuSQLException(String reason) {
        super(reason);
    }

    public IdInconnuSQLException() {
        super();
    }

    public IdInconnuSQLException(Throwable cause) {
        super(cause);
    }
    
}
