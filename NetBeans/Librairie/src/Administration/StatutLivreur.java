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

import RuntimeException.InvalideCodeNameRuntimeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author cdi415
 */
public class StatutLivreur extends Statut{
    
    public static final String CODE_PREFIX = "LIV";
    
    public static ArrayList<Statut> getStatut(){
        ArrayList<Statut> listStatus = new ArrayList<>();
        
        String query = "SELECT code ,"
                        + "info "
                    + " FROM Statut "
                + " WHERE code LIKE '" + CODE_PREFIX + "%'";
        
        try (
                Connection cnx = new SqlManager.SqlManager().GetConnection();
                Statement stmt = cnx.createStatement();
                ResultSet rs   = stmt.executeQuery(query);
            ){
            while(rs.next()) {
                Statut myStatut = new StatutLivreur(rs.getString("code"),rs.getString("info"));
                listStatus.add(myStatut);
            }

        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return null;
        }
        
        return listStatus;
    }

    public StatutLivreur() {
    }

    public StatutLivreur(String code) {
        super(code);
    }

    public StatutLivreur(String code, String info) {
        super(code, info);
    }
    
    @Override
    public void setCode(String code) {
        if (!Pattern.compile("^" + StatutLivreur.CODE_PREFIX).matcher(code).matches()){
            code = StatutLivreur.CODE_PREFIX + code;
        }
        if (!Pattern.compile("[a-zA-Z0-9]{4,30}").matcher(code).matches()){
            throw new InvalideCodeNameRuntimeException("Format de code incohérent");
        }
        super.setCode(code);
    }
    
    public Boolean isUsed(){
        
        String req = "SELECT COUNT(statutLivreur) AS counter "
                + "FROM Client WHERE statutLivreur=?";
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
            return null;
        } 
        
        return false;
    }
    
    
}
