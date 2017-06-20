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

import RuntimeException.*;
import Exception.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Vector;
import java.util.regex.Pattern;

/**
 *
 * @author cdi415
 */
public class Employe {
    
    
    protected static final Pattern emailPattern = Pattern.compile(
        "^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$"
    );
    
    protected static final Integer MDPMINSIZE = 6;
    
    private Integer idEmploye;
    private String nomEmploye;
    private String prenomEmploye;
    private String loginEmploye;
    private String mdpEmploye;
    private String emailEmploye;
    private LocalDate debutPosteEmploye;
    private LocalDate finPosteEmploye;
    
    
    /**
     * getAllEmploye()
     * 
     * Creates an ArrayList that list all employe from database.
     * In case of error returns a null
     * @return an ArrayList<Employe>
     */
    public static ArrayList<Employe> getArrayListAllEmploye(){
        ArrayList<Employe> ArrayListEmploye = new ArrayList<>();
        
        String query = "SELECT idEmploye ,"
                        + "nomEmploye ,"
                        + "prenomEmploye ,"
                        + "loginEmploye ,"
                        + "mdpEmploye ,"
                        + "emailEmploye ,"
                        + "debutPosteEmploye ,"
                        + "finPosteEmploye "
                    + "FROM Employe";
        
        try (
                Connection cnx = new SqlManager.SqlManager().GetConnection();
                Statement stmt = cnx.createStatement();
                ResultSet rs   = stmt.executeQuery(query);
            ){
            while( rs.next()) {
                Employe e = new Employe(rs.getInt("idEmploye"));
                e.setNomEmploye(rs.getString("nomEmploye"));
                e.setPrenomEmploye(rs.getString("prenomEmploye"));
                e.setLoginEmploye(rs.getString("loginEmploye"));
                e.setMdpEmploye(rs.getString("mdpEmploye"));
                e.setEmailEmploye(rs.getString("emailEmploye"));
                e.setDebutPosteEmploye(rs.getDate("debutPosteEmploye").toLocalDate());
                if (rs.getDate("finPosteEmploye") != null){
                    e.setFinPosteEmploye(rs.getDate("finPosteEmploye").toLocalDate());
                }
                ArrayListEmploye.add(e);
            }

        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return null;
        } catch (InvalidEmailException ex) {
            System.err.println("Oops:getAllEmploye:" + ex.getMessage());
            return null;
        } catch (InvalidPasswordException ex) {
            System.err.println("Oops:getAllEmploye:" + ex.getMessage());
        }
        
        return ArrayListEmploye;
    }
    
    /**
     * Simple constructor of class
     * initialise idEmploye at 0
     */
    public Employe(){
        setIdEmploye(0);
        finPosteEmploye = null;
    }
    
    /**
     * Constructor of class
     * allows to define the idEmploye
     * @param idEmploye 
     */
    public Employe(Integer idEmploye){
        this();
        setIdEmploye(idEmploye);
    }
    
    /**
     * Récupère l'id de l'employé
     * @return Integer idEmploye
     */
    public Integer getIdEmploye() {
        return idEmploye;
    }
    
    /**
     * sets the id of the employe 
     * @param idEmploye 
     */
    private void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    
    /**
     * Recupère le nom de l'employé
     * @return String nomEmploye
     */
    public String getNomEmploye() {
        return nomEmploye;
    }

    /**
     * Définit le nom de l'employe
     * Le nom est par défaut trimer et converti en majuscile
     * @param nomEmploye 
     */
    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye.trim().toUpperCase();
    }

    /**
     * Recupère le prénom de l'employé
     * @return String prenomEmploye
     */
    public String getPrenomEmploye() {
        return prenomEmploye;
    }

    /**
     * Définit le prénom de l'employe
     * Le prénom est par défaut trimmer et 
     * converti en minuscule avec la première
     * lettre en majuscule
     * @param prenomEmploye 
     */
    public void setPrenomEmploye(String prenomEmploye) {
        prenomEmploye = prenomEmploye.trim();
        this.prenomEmploye = prenomEmploye.substring(0, 1).toUpperCase() +
                                prenomEmploye.substring(1).toLowerCase();
    }

    /**
     * Recupère le login de l'employé
     * @return String loginEmploye
     */
    public String getLoginEmploye() {
        return loginEmploye;
    }

    /**
     * Définit le login de l'employé
     * Par defaut le login est trimmer
     * @param loginEmploye 
     */
    public void setLoginEmploye(String loginEmploye) {
        this.loginEmploye = loginEmploye.trim();
    }

    /**
     * Recupère le mot de passe de l'employé
     * @return String mdpEmploye 
     */
    public String getMdpEmploye() {
        return mdpEmploye;
    }

    /**
     * Définit le mot de passe de l'employé
     * Le mot de passe ne peut être inférieure à {@code MDPMINSIZE}
     * @param mdpEmploye
     * @throws InvalidPasswordException 
     */
    public void setMdpEmploye(String mdpEmploye) throws InvalidPasswordException {
        if (mdpEmploye.length() < Employe.MDPMINSIZE){
            throw new InvalidPasswordException("Mot de passe trop court [min = "+ 
                     Employe.MDPMINSIZE + "]");
        }
        this.mdpEmploye = mdpEmploye;
    }
    
    /**
     * Encode le mot de passe.
     * Le mot de passe ne peut être inférieure à {@code MDPMINSIZE}
     * @throws InvalidPasswordException 
     */
    public void encodeMdpEmploye() throws InvalidPasswordException{
        if (mdpEmploye.length() < Employe.MDPMINSIZE){
            throw new InvalidPasswordException("Mot de passe trop court [min = "+ 
                     Employe.MDPMINSIZE + "]");
        }
        this.mdpEmploye = SqlManager.SqlManager.encodeString(this.mdpEmploye);
    }

    /**
     * Récupére l'adresse email de l'employé
     * @return String emailEmploye
     */
    public String getEmailEmploye() {
        return emailEmploye;
    }

    /**
     * Définit l'adresse email de l'employé.
     * Vérifie que l'adresse email est valide structurellement
     * @param emailEmploye
     * @throws InvalidEmailException 
     */
    public void setEmailEmploye(String emailEmploye) throws InvalidEmailException {
        emailEmploye = emailEmploye.trim();
        if (!emailPattern.matcher(emailEmploye).matches()) {
            throw new InvalidEmailException("Adresse e-mail [" + emailEmploye + "] est invalide");
        }
        this.emailEmploye = emailEmploye;
    }

    /**
     * Récupére la date de debut de poste de l'employé
     * @return LocalDate debutPosteEmploye
     */
    public LocalDate getDebutPosteEmploye() {
        return debutPosteEmploye;
    }
    
    /**
     * Définit la date de debut de poste de l'employé
     * @param debutPosteEmploye 
     */
    public void setDebutPosteEmploye(LocalDate debutPosteEmploye) {
        this.debutPosteEmploye = debutPosteEmploye;
    }

    /**
     * Récupére la date de fin de poste de l'employé
     * @return LocalDate finPosteEmploye
     */
    public LocalDate getFinPosteEmploye() {
        return finPosteEmploye;
    }

    /**
     * Définit la date de fin de poste de l'employé
     * @param finPosteEmploye 
     */
    public void setFinPosteEmploye(LocalDate finPosteEmploye) {
        this.finPosteEmploye = finPosteEmploye;
    }
    
    /**
     * Recharche les paramètres de l'empoyé 
     * à partir de la BDD.
     * 
     * Si l'id n'existe pas dans la BDD une SQLException est levé
     * @throws IdSQLException
     * @throws SQLException 
     */
    public void reload() throws IdSQLException, SQLException{
        
        if ((this.getIdEmploye()).equals(0)){
            throw new IdSQLException("ID Employe inconnue");
        }
        
        String req = "SELECT idEmploye ,"
                        + "nomEmploye ,"
                        + "prenomEmploye ,"
                        + "loginEmploye ,"
                        + "mdpEmploye ,"
                        + "emailEmploye ,"
                        + "debutPosteEmploye ,"
                        + "finPosteEmploye "
                    + " FROM Employe "
                    + " WHERE idEmploye=?";
        
        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);
            ){

            // Créer une requete
            pstm.setInt(1, getIdEmploye());
            
            ResultSet rs = pstm.executeQuery();
                
                if(!rs.next()){
                    throw new SQLException("ID Employe inconnue");
                }
                
                setNomEmploye(rs.getString("nomEmploye"));
                setPrenomEmploye(rs.getString("prenomEmploye"));
                setLoginEmploye(rs.getString("loginEmploye"));
                setMdpEmploye(rs.getString("mdpEmploye"));
                setEmailEmploye(rs.getString("emailEmploye"));
                setDebutPosteEmploye((rs.getDate("debutPosteEmploye")).toLocalDate());
                if (rs.getDate("finPosteEmploye") != null){
                    setFinPosteEmploye((rs.getDate("finPosteEmploye")).toLocalDate());
                }
            
        } catch (SQLException ex) {
//            System.err.println("Error : SQL Error ["
//                    + ex.getMessage()
//                    + "]");
//            ex.printStackTrace();
            throw ex;
        } catch (InvalidPasswordException ex) {
            System.err.println(ex.getMessage());
        } catch (InvalidEmailException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Savegarde les informations de l'employé dans la BDD
     * @throws DuplicateConstraintSQLException
     * @throws SQLException 
     */
    public void save() throws DuplicateConstraintSQLException, SQLException{
        String req = null;
        if ((this.getIdEmploye()).equals(0)){
            // doesn't exists so we insert into
            req = "INSERT INTO Employe(nomEmploye ,"
                        + "prenomEmploye ,"
                        + "loginEmploye ,"
                        + "mdpEmploye ,"
                        + "emailEmploye ,"
                        + "debutPosteEmploye ,"
                        + "finPosteEmploye)"
                    + " VALUES "
                        + "(?, ?, ?, ?, ?, ?, ?)";
        } else {
            // existe so we update
            req = "UPDATE Employe SET nomEmploye=? ,"
                        + "prenomEmploye=? ,"
                        + "loginEmploye=? ,"
                        + "mdpEmploye=? ,"
                        + "emailEmploye=? ,"
                        + "debutPosteEmploye=? ,"
                        + "finPosteEmploye=? "
                    + " WHERE "
                        + "idEmploye=?";
        }
        
        //System.out.println(req);
        
        // vérification de l'unicité des champs à contrainte
        if (loginExists()){throw new DuplicateConstraintSQLException("Duplicate Login");}
        if (emailExists()){throw new DuplicateConstraintSQLException("Duplicate Email");}
        
        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ){

            // Créer une requete
            pstm.setString(1, getNomEmploye());
            pstm.setString(2, getPrenomEmploye());
            pstm.setString(3, getLoginEmploye());
            pstm.setString(4, getMdpEmploye());
            pstm.setString(5, getEmailEmploye());
            pstm.setDate(6, java.sql.Date.valueOf(getDebutPosteEmploye()));
            //pstm.setObject(6, getDebutPosteEmploye(), java.sql.Types.DATE);
            if (finPosteEmploye==null){
                pstm.setNull(7, java.sql.Types.DATE);
            } else {
                pstm.setDate(7, java.sql.Date.valueOf(getFinPosteEmploye()));
            }
            
            if ((this.getIdEmploye()).equals(0)){ // INSERT
                // Exécuter une requête
                pstm.executeUpdate();
                // Get new Id
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                setIdEmploye(rs.getInt(1));
                //System.out.println(this);
            } else { // UPDATE
                pstm.setInt(8, getIdEmploye());
                // Exécuter une requête
                pstm.executeUpdate();
            }
            
        } catch (SQLException ex) {
//            System.err.println("Error : SQL Error ["
//                    + ex.getMessage()
//                    + "]");
//            System.out.println("CodeError : "+ex.getErrorCode());
//            System.out.println("SQLState" + ex.getSQLState());
//            ex.printStackTrace();
            
            if (ex.getErrorCode()==2627){ // Violation de la contrainte UNIQUE KEY
                throw new DuplicateConstraintSQLException(ex);
            }
            throw new SQLException("SQL Error [" + ex.getMessage() + "]");
            
        }
    }
    
    /**
     * Search in BDD if employe login exists
     * Excludes itself for search.
     * @return Boolean
     */
    public Boolean loginExists(){
        
        String req = "SELECT COUNT(id) as counter "
                    + "FROM Employe "
                + " WHERE loginEmploye = ? "
                + " AND idEmploye != ?";
        
        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);
            ){

            // Créer une requete
            pstm.setString(1, getLoginEmploye());
            pstm.setInt(2, getIdEmploye());
            
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
    
    /**
     * Search in BDD if employe email exists
     * Excludes itself for search.
     * @return Boolean
     */
    public Boolean emailExists(){
        
        String req = "SELECT COUNT(id) as counter "
                    + "FROM Employe "
                + " WHERE emailEmploye = ? "
                + " AND idEmploye != ?";
        
        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);
            ){

            // Créer une requete
            pstm.setString(1, getEmailEmploye());
            pstm.setInt(2, getIdEmploye());
            
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
        return "Employe{" + "idEmploye=" + idEmploye + ", nomEmploye=" + nomEmploye + ", prenomEmploye=" + prenomEmploye + '}';
    }
    
    
    
}
