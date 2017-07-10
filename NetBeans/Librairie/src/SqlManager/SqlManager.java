/*
 * Copyright (C) 2017 cdi415
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package SqlManager;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cdi415
 */
public class SqlManager  implements Serializable {
    
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String jdbcKey = "jdbc/LibrairieDB";
    
    private DataSource ds;
    
    
    /**
     * SqlManager()
     * 
     * Initialise the SqlManager and verifies presence of driver
     */
    public SqlManager() {
        try {
            testDriver();
            InitialContext ict = new InitialContext();
            ds= (DataSource) ict.lookup(SqlManager.jdbcKey);
        } catch (NamingException ex) {
            System.err.println("Error : Echec du chargement du pool de connexion ["
                    + ex.getMessage()
                    + "]");
            System.exit(1246);
        }
    }
    
    /**
     * testDriver()
     * 
     * Verify that driver is present
     * @throws ClassNotFoundException if absent
     */
    private void testDriver(){
        // Test existance driver
        try{
            // Récupération de la classe driver
            Class.forName(SqlManager.driver);
        } catch (ClassNotFoundException ex) {
//            throw new ClassNotFoundException("Error : Echec du chargement du Driver ["
//                    + ex.getMessage()
//                    + "]");
            System.err.println("Error : Echec du chargement du Driver ["
                    + ex.getMessage()
                    + "]");
            System.exit(1479);
        }
    }
    
    
    /**
     * Générates a connection to the DB
     * @return Connection
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
    
    /**
     * Encode text in SHA-256
     * <b>WARNING : if encoding fails will return a null</b>
     * @param text to encode
     * @return encoded text
     */
    public static String encodeString(String text){
        MessageDigest digest = null;
        String encoded = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            encoded = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("ERROR[encodeString] : " + ex.getMessage());
            Logger.getLogger(SqlManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encoded;
    }
    
    /**
     * Encodes text and compares to the encodedtext
     * Returns true if plain text and encoded text 
     * are identical else returns false.
     * @param text plain text
     * @param encodedtext SHA-256 encoded text
     * @return Boolean
     */
    public static Boolean compareEncodedString(String text, String encodedtext){
        return encodedtext.equals(SqlManager.encodeString(text));
    }
    
    
    
    
    
    
    
}
