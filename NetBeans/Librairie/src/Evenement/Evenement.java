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
package Evenement;


import Exception.DuplicateConstraintSQLException;
import Exception.EmptyBookEventSQLException;
import Exception.IdSQLException;
import RuntimeException.InvalidNameRuntimeException;
import bookStore.Livre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author cdi415
 */
public class Evenement {
    
    private Integer id;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String lieu;
    private Float reduction;
    private String codePromo;
    private Boolean promoImmediat;
    private String commentaire;
    
    private ArrayList<Livre> mesLivres;
    
    /**
     * Creates an ArrayList<Evenement> from the DB
     * @return ArrayList of Evenement from the DB
     */
    public static ArrayList<Evenement> getArrayListAllEvenement(){
        ArrayList<Evenement> ArrayListEvenement = new ArrayList<>();
        
        String query = "SELECT idEvenement"
                + "      ,nomEvenement"
                + "      ,descriptionEvenement"
                + "      ,dateDebutEvenement"
                + "      ,dateFinEvenement"
                + "      ,lieuEvenement"
                + "      ,reductionEvenement"
                + "      ,codePromoEvenement"
                + "      ,promoImmediatEvenement"
                + "      ,commentaireEvenement"
                + " FROM Evenement";
        
        try (
                Connection cnx = new SqlManager.SqlManager().GetConnection();
                Statement stmt = cnx.createStatement();
                ResultSet rs   = stmt.executeQuery(query);
            ){
            while( rs.next()) {
                Evenement e = new Evenement(rs.getInt("idEvenement"));
                ArrayListEvenement.add(e);
            }

        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return null;
        }
        
        return ArrayListEvenement;
    }
    
    /**
     * Creates an ArrayList<Evenement> from the DB that affects the selected isbn
     * @param isbn
     * @return ArrayList of Evenement from the DB
     */
    public static ArrayList<Evenement> getLivreEvents(String isbn) throws SQLException{
        ArrayList<Evenement> ArrayListEvenement = new ArrayList<>();
        
        String query = "SELECT idEvenement"
                + " FROM Presentation "
                + " WHERE "
                + "     isbnLivre = ?";
        
        try (
                Connection cnx = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstmt = cnx.prepareStatement(query);
            ){
                pstmt.setString(1, isbn);
                ResultSet rs   = pstmt.executeQuery();
            while( rs.next()) {
                Evenement e = new Evenement(rs.getInt("idEvenement"));
                ArrayListEvenement.add(e);
            }

        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return null;
        }
        
        return ArrayListEvenement;
    }
    
    /**
     * Basic constructor
     */
    public Evenement(){
        setId(0);
        setNom("");
        setDescription("");
        setDateDebut(LocalDate.now());
        dateFin = null;
        lieu = null;
        reduction = 0f;
        codePromo = null;
        promoImmediat = false;
        commentaire = null;
        mesLivres = new ArrayList<>();
    }
    
    /**
     * Constructs an Evenment object from the DB data.
     * Uses the id to identify the data to use
     * @param id
     * @throws IdSQLException if the id is not found int the sql server
     * @throws SQLException if an error occurs with the sql Server
     */
    public Evenement(Integer id) throws IdSQLException, SQLException{
        this();
        setId(id);
        getSqlData();
    }
    
    /**
     * Constructs an Evenment object from the DB data.
     * Uses the promotional code to identify the data to use
     * @param codePromo
     * @throws IdSQLException if the promotional code is not found int the sql server
     * @throws SQLException if an error occurs with the sql Server
     */
    public Evenement(String codePromo) throws IdSQLException, SQLException{
        this();
        setCodePromo(codePromo);
        getSqlData();
    }
    
    /**
     * Returns the id
     * @return Integer id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the Event id
     * @param id 
     */
    private void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }


    /**
     * Sets the Event Name
     * @param nom 
     */
    public void setNom(String nom) {
        nom = nom.trim();
        if (nom.equals("") && nom.length() > 50){
            throw new InvalidNameRuntimeException("Nom d'évenement non valide");
        }
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets the Event Description 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * Sets the Event start date
     * @param dateDebut 
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * Sets the Event end date
     * @param dateFin 
     */
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getLieu() {
        return lieu;
    }

    /**
     * Sets the Event place
     * @param lieu 
     */
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Float getReduction() {
        return reduction;
    }

    /**
     * Sets the Event reduction offer
     * @param reduction 
     */
    public void setReduction(Float reduction) {
        this.reduction = reduction;
    }

    public String getCodePromo() {
        return codePromo;
    }

    public void setCodePromo(String codePromo) {
        if (codePromo == null){
            this.codePromo = null;
        } else {
            this.codePromo = codePromo.toUpperCase();
        }
    }

    public Boolean getPromoImmediat() {
        return promoImmediat;
    }

    public void setPromoImmediat(Boolean promoImmediat) {
        this.promoImmediat = promoImmediat;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    public ArrayList<Livre> getLivres(){
        return mesLivres;
    }
    
    /**
     * Ajouter un livre dans l'événement
     * @param livre 
     * @throws Exception if book already in event
     */
    public void addLivre(Livre livre) throws Exception{
        // verifier que le livre n'as pas déja été ajouté
        if (containsISBN(livre.getIsbnLivre())){
            throw new Exception("Livre [" + livre.getIsbnLivre() + "] déjà ajouté");
        }
        mesLivres.add(livre);
    }
    
    /**
     * Supprimer un livre de l'événement
     * @param livre 
     */
    public void delLivre(Livre livre){
        mesLivres.remove(livre);
    }
    
    /**
     * Supprimer tous les livres de l'événement
     */
    public void clearLivre(){
        mesLivres.clear();
    }
    
    // Iterates over all transactions until a transaction is found that has the
    // same name as specified in search
    public boolean containsISBN(final String isbn) {
        for (final Livre livre : mesLivres) {
            if (livre.getIsbnLivre().equals(isbn)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Retreives all information on the Event from the DB from a specifique id
     * @throws IdSQLException
     * @throws SQLException 
     */
    private void getSqlData() throws IdSQLException, SQLException{
        
        Boolean searchWithID = false;
        
        String req = "SELECT idEvenement"
                + "      ,nomEvenement"
                + "      ,descriptionEvenement"
                + "      ,dateDebutEvenement"
                + "      ,dateFinEvenement"
                + "      ,lieuEvenement"
                + "      ,reductionEvenement"
                + "      ,codePromoEvenement"
                + "      ,promoImmediatEvenement"
                + "      ,commentaireEvenement"
                + " FROM Evenement"
                + " WHERE ";
        
        if (!getId().equals(0)){
            req = req + "     idEvenement = ?";
            searchWithID = true;
        } else if (getCodePromo() != null) {
            req = req + "     codePromoEvenement = ?";
            searchWithID = false;
        } else {
            throw new SQLException("ID Evenement inconnue");
        }
        
        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);
            ){

            // Créer une requete
            if (searchWithID){
                pstm.setInt(1, getId());
            } else {
                pstm.setString(1, codePromo);
            }
            
            ResultSet rs = pstm.executeQuery();
            
            // Verif if has result
            if(!rs.next()){
                if (searchWithID) {
                    throw new IdSQLException("ID Evenement inconnue");
                } else {
                    throw new IdSQLException("Code Promo [" + getCodePromo() + "] inconnue");
                }
            }

            setId(rs.getInt("idEvenement"));
            setNom(rs.getString("nomEvenement"));
            setDescription(rs.getString("descriptionEvenement"));
            setDateDebut((rs.getDate("dateDebutEvenement")).toLocalDate());
            if (rs.getDate("dateFinEvenement") != null){
                setDateFin((rs.getDate("dateFinEvenement")).toLocalDate());
            }
            if (rs.getString("lieuEvenement") != null){
                setLieu(rs.getString("lieuEvenement"));
            }
            rs.getFloat("reductionEvenement");
            if (!rs.wasNull()){
                setReduction(rs.getFloat("reductionEvenement"));
            }
            if (rs.getString("codePromoEvenement") != null){
                setCodePromo(rs.getString("codePromoEvenement"));
            }
            rs.getBoolean("promoImmediatEvenement");
            if (!rs.wasNull()){
                setPromoImmediat(rs.getBoolean("promoImmediatEvenement"));
            }
            if (rs.getString("commentaireEvenement") != null){
                setCommentaire(rs.getString("commentaireEvenement"));
            }
            
            getSqlLivres();
            
        } catch (SQLException ex) {
//            System.err.println("Error : SQL Error ["
//                    + ex.getMessage()
//                    + "]");
//            ex.printStackTrace();
            throw ex;
        }
    }
    
    private void getSqlLivres() throws SQLException {
        String req = "SELECT "
                + "     isbnLivre"
                + " FROM "
                + "     Presentation"
                + " WHERE "
                + "     idEvenement = ?";
        
        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);
            ){

            // Créer une requete
            pstm.setInt(1, getId());
            
            pstm.executeQuery();
                
            ResultSet rs = pstm.getResultSet();
            while (rs.next()){
                mesLivres.add(new Livre(rs.getString("isbnLivre")));
            }
            
        } catch (SQLException ex) {
            System.err.println("Error : SQL Error ["
                    + ex.getMessage()
                    + "]");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Error : ["
                    + ex.getMessage()
                    + "]");
            ex.printStackTrace();
        } 
    }

    /**
     * Savegarde les informations de l'employé dans la BDD
     * @throws DuplicateConstraintSQLException
     * @throws SQLException 
     */
    public void save() throws DuplicateConstraintSQLException, SQLException{
        String req = null;
        if ((this.getId()).equals(0)){
            // doesn't exists so we insert into
            req = "INSERT INTO Evenement("
                    + "      nomEvenement"
                    + "      ,descriptionEvenement"
                    + "      ,dateDebutEvenement"
                    + "      ,dateFinEvenement"
                    + "      ,lieuEvenement"
                    + "      ,reductionEvenement"
                    + "      ,codePromoEvenement"
                    + "      ,promoImmediatEvenement"
                    + "      ,commentaireEvenement"
                    + "     )"
                    + " VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            // existe so we update
            req = "UPDATE Evenement SET "
                    + "      nomEvenement = ?"
                    + "      ,descriptionEvenement = ?"
                    + "      ,dateDebutEvenement = ?"
                    + "      ,dateFinEvenement = ?"
                    + "      ,lieuEvenement = ?"
                    + "      ,reductionEvenement = ?"
                    + "      ,codePromoEvenement = ?"
                    + "      ,promoImmediatEvenement = ?"
                    + "      ,commentaireEvenement = ?"
                    + " WHERE "
                        + "idEvenement=?";
        }
        
        //System.out.println(req);
        
        
        if (codePromoExists()){
            throw new DuplicateConstraintSQLException("Duplicate Code Promo ["
                    + getCodePromo() +"]");
        }
        
        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ){

            // Créer une requete
            int i=1;
            pstm.setString(i++, getNom());
            pstm.setString(i++, getDescription());
            pstm.setDate(i++, java.sql.Date.valueOf(getDateDebut()));
            if (getDateFin() != null){
                pstm.setDate(i++, java.sql.Date.valueOf(getDateFin()));
            } else {
                pstm.setNull(i++, java.sql.Types.DATE);
            }
            if (getLieu()!= null){
                pstm.setString(i++, getLieu());
            } else {
                pstm.setNull(i++, java.sql.Types.VARCHAR);
            }
            if (getReduction()!= null){
                pstm.setFloat(i++, getReduction());
            } else {
                pstm.setNull(i++, java.sql.Types.FLOAT);
            }
            if (getCodePromo()!= null){
                pstm.setString(i++, getCodePromo());
            } else {
                pstm.setNull(i++, java.sql.Types.VARCHAR);
            }
            if (getPromoImmediat()!= null){
                pstm.setByte(i++, (byte)(getPromoImmediat() ? 1 : 0));
            } else {
                pstm.setNull(i++, java.sql.Types.BIT);
            }
            if (getCommentaire()!= null){
                pstm.setString(i++, getCommentaire());
            } else {
                pstm.setNull(i++, java.sql.Types.VARCHAR);
            }
            
            
            if ((this.getId()).equals(0)){ // INSERT
                // Exécuter une requête
                pstm.executeUpdate();
                // Get new Id
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                setId(rs.getInt(1));
                //System.out.println(this);
            } else { // UPDATE
                pstm.setInt(i++, getId());
                // Exécuter une requête
                pstm.executeUpdate();
            }
                        
        } catch (SQLException ex) {
            
            if (ex.getErrorCode()==2627){ // Violation de la contrainte UNIQUE KEY
                throw new DuplicateConstraintSQLException(ex);
            }
            throw new SQLException("SQL Error [" + ex.getMessage() + "]");
            
        }
    }
    
    public void saveBookList() throws DuplicateConstraintSQLException, SQLException {

        if (mesLivres.isEmpty()) {
            throw new EmptyBookEventSQLException("Merci de renseigner au moins un livre");
        }

        if ((this.getId()).equals(0)) {
            throw new IdSQLException("ID Evenement inconnue");
        }

        // Suppression des anciennes relations
        String req = null;
        req = "DELETE FROM Presentation WHERE idEvenement = ?";
        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {
            
            pstm.setInt(1, getId());
            pstm.executeUpdate();

        } catch (SQLException ex) {

            if (ex.getErrorCode() == 2627) { // Violation de la contrainte UNIQUE KEY
                throw new DuplicateConstraintSQLException(ex);
            }
            throw new SQLException("SQL Error [" + ex.getMessage() + "]");

        }

        // Ajout des nouvelles relations
        req = "INSERT INTO Presentation("
                + "      isbnLivre"
                + "      ,idEvenement"
                + "     )"
                + " VALUES ";
        for (Livre liv : mesLivres) {
            req = req + " (?,?), ";
        }
        req = req.substring(0, req.length() - 2);
            //System.out.println(req);

        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {

            // Créer une requete
            int i = 1;
            for (Livre liv : mesLivres) {
                pstm.setString(i++, liv.getIsbnLivre());
                pstm.setInt(i++, getId());
            }

            pstm.executeUpdate();

        } catch (SQLException ex) {

            if (ex.getErrorCode() == 2627) { // Violation de la contrainte UNIQUE KEY
                throw new DuplicateConstraintSQLException(ex);
            }
            throw new SQLException("SQL Error [" + ex.getMessage() + "]");

        }
    }

    /**
     * Search in BDD if Event promotionnal code exists
     * Excludes itself for search.
     * @return Boolean
     */
    public Boolean codePromoExists(){
        
        String req = "SELECT COUNT(idEvenement) as counter "
                    + "FROM Evenement "
                + " WHERE codePromoEvenement = ? "
                + " AND idEvenement != ?";
        
        try (
                Connection cnt = new SqlManager.SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);
            ){

            // Créer une requete
            pstm.setString(1, getCodePromo());
            pstm.setInt(2, getId());
            
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
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", lieu=" + lieu + ", reduction=" + reduction + ", codePromo=" + codePromo + ", promoImmediat=" + promoImmediat + ", commentaire=" + commentaire + '}';
    }
    
    
}
