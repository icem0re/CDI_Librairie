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
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author cdi415
 */
public class StatutClient extends Statut{
    
    public static final String CODE_PREFIX = "CLI";
    
    /**
     * Querries the DB and constructs an arrayList of status 
     * @return ArrayList<Statut>
     * @throws SQLException 
     */
    public static ArrayList<Statut> getStatut() throws SQLException{
        ArrayList<Statut> listStatus = new ArrayList<>();
        
        String query = "SELECT code ,"
                        + "info "
                    + " FROM Statut "
                + " WHERE code LIKE '" + CODE_PREFIX + "%'"
                + " ORDER BY code ASC";
        
        try (
                Connection cnx = new SqlManager.SqlManager().GetConnection();
                Statement stmt = cnx.createStatement();
                ResultSet rs   = stmt.executeQuery(query);
            ){
            while(rs.next()) {
                Statut myStatut = new StatutClient(rs.getString("code"),rs.getString("info"));
                listStatus.add(myStatut);
            }

        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            throw ex;
        }
        
        return listStatus;
    }

    /**
     * Default constructeur
     */
    public StatutClient() {
    }

    /**
     * Constructs a client statut object
     * @param code
     * @param info 
     */
    public StatutClient(String code, String info) {
        super(code, info);
    }

    /**
     * Sets the code of the statut
     * @param code 
     */
    @Override
    public void setCode(String code) {
        if (!Pattern.compile("^" + StatutClient.CODE_PREFIX).matcher(code).find()){
            code = StatutClient.CODE_PREFIX + code;
        }
        
        super.setCode(code);
    }
    
    /**
     * Test if the statut code is used.
     * @return a Boolean
     * @throws SQLException 
     */
    public Boolean isUsed() throws SQLException{
        
        String req = "SELECT COUNT(statutClient) AS counter "
                + "FROM Client WHERE statutClient=?";
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
            throw ex;
        } 
        
        return false;
    }
    
}
