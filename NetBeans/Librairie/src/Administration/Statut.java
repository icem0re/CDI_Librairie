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
package Administration;

import RuntimeException.InvalidCodeNameRuntimeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author cdi415
 */
public abstract class Statut {
    
    private String code;
    private String info;
    
    /**
     * Default constructeur
     */
    public Statut(){}
    
    /**
     * Constructs a client statut object
     * @param code
     * @param info 
     */
    public Statut(String code, String info) {
        setCode(code);
        setInfo(info);
    }

    /**
     * Retreives the statut code
     * @return String
     */
    public String getCode() {
        return code;
    }
    
    /**
     * Sets the code of the statut
     * @param code 
     * @param info 
     */
    public void setCode(String code) {
        if (!Pattern.compile("[a-zA-Z0-9]{4,30}").matcher(code).matches()){
            String errMsg = "";
            if (code.length()<4){
                errMsg = "Code trop court (minimum 6 caractères)";
            }else if (code.length()>30){
                errMsg = "Code trop long (maximun 30 caractères)";
            }else {
                errMsg = "Code avec caractères non autorisé (AlphaNumérique uniquement)";
            }
            throw new InvalidCodeNameRuntimeException(errMsg);
        }
        this.code = code.toUpperCase().trim();
    }

    /**
     * Retreives the statut info
     * @return String
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets the status info
     * @param info 
     */
    public void setInfo(String info) {
        this.info = info;
    }
    
    /**
     * Injects the statut in the DB
     * self-managed INSERT or UPDATE statement.
     */
    public void save() throws SQLException{
        
        if (getCode().equals("")) {
            throw new InvalidCodeNameRuntimeException("Code vide");
        }
        
        String req = "";
        
        try {
            if (exists()) {
                req = "UPDATE Statut SET info=? WHERE code=?";
            } else {
                req = "INSERT INTO Statut (info, code) VALUES (?, ?)";
            }
        } catch (SQLException ex) {
            throw ex;
        }

        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);
            ) {

            // Créer une requete
            pstm.setString(1, getInfo());
            pstm.setString(2, getCode());

            pstm.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
        }
         
    }
    
    /**
     * Deletes the statut from the DB
     * @throws SQLException 
     */
    public void delete() throws SQLException{
        
        if (getCode().equals("")){
            throw new InvalidCodeNameRuntimeException("Code vide");
        }
        
        String req = "DELETE FROM Statut WHERE code = ?";
        
        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);
            ){

            // Créer une requete
            pstm.setString(1, getCode());
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    /**
     * Tests if the statut code exists in the DB
     * @return true if found else false
     * @throws java.sql.SQLException 
     */
    public Boolean exists() throws SQLException{
        
        String req = "SELECT COUNT(code) AS counter FROM Statut WHERE code=?";
        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);
            ){

            // Créer une requete
            pstm.setString(1, getCode());
            
            pstm.executeQuery();
                
            ResultSet rs = pstm.getResultSet();
            rs.next();
            if (rs.getInt("counter") > 0){
                return true;
            }
            
        } catch (SQLException ex) {
            throw ex;
        } 
        return false;
    }

    @Override
    public String toString() {
        return code;
    }
}
