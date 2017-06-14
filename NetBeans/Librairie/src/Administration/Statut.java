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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author cdi415
 */
public abstract class Statut {
    
    private String code;
    private String info;
    
    public Statut(){
        
    }
    
    public Statut(String code){
        setCode(code);
    }
    
    public Statut(String code, String info) {
        setCode(code);
        setInfo(info);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code.toUpperCase();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    public void save(){
        
        String req = "";
        if (exists()){
            req = "UPDATE Statut SET info=? WHERE code=?";
        } else {
            req = "INSERT INTO Statut (info, code) VALUES (?, ?)";
        }
        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);
            ){

            // Créer une requete
            pstm.setString(1, getInfo());
            pstm.setString(2, getCode());
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println("Error : SQL Error ["
                    + ex.getMessage()
                    + "]");
            ex.printStackTrace();
        } 
    }
    
    public Boolean exists(){
        
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
            System.err.println("Error : SQL Error ["
                    + ex.getMessage()
                    + "]");
            ex.printStackTrace();
            return true;
        } 
        return false;
    }

    @Override
    public String toString() {
        return "Statut{" + "code=" + code + ", info=" + info + '}';
    }
}
