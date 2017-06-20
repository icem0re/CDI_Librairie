package Utilisateur;

import SqlManager.SqlManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import Exception.*;
import RuntimeException.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    
     protected static final Pattern emailPattern = Pattern.compile(
        "^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$"
    );
    
    protected static final Integer MDPMINSIZE = 6;

    private int idClient;
    private String nomClient;
    private String prenomClient;
    private String loginClient;
    private String mdpClient;
    private String emailClient;
    private LocalDate dateNaissanceClient;
    private String statutClient;
    private LocalDate derniereConnexionClient;

    private ArrayList<Adresse> adresseDomiciliation;
    private ArrayList<Adresse> adresseFacturation;

    public Client() {
        adresseDomiciliation = new ArrayList();
        adresseFacturation = new ArrayList();
    }

    /**
     * 
     * @param idClient
     * @throws SQLException 
     */
    public Client(int idClient) throws SQLException{
        this();
        setIdClient(idClient);
        select();
        getAdressesFacturation();
        getAdressesDomiciliation();
    }
    
    public Client(int idClient, String nomClient, String prenomClient) {
        this();
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
    }
    
    public String getStatutClient() {
        return statutClient;
    }

    public void setStatutClient(String statutClient) {
         
        this.statutClient = statutClient;
    }
    
    public void removeStatutClient(String statutClient) {
       setStatutClient("");
    }

    public LocalDate getDerniereConnexionClient() {
        return derniereConnexionClient;
    }

    public void setDerniereConnexionClient(LocalDate derniereConnexionClient) {
        this.derniereConnexionClient = derniereConnexionClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) throws Exception {
        if (nomClient.equals("")){
            throw new Exception("Nom Vide");
        }
        if(nomClient.length() > 50){
            throw new Exception("erreur : le nom ne peut pas dépasser 50 caractères");
        }
        if(nomClient.matches("[0-9]*")){
            throw new Exception("Nom non valide");
        }
        else{
            this.nomClient = nomClient.trim().toUpperCase();
        }
    }

    public ArrayList<Adresse> getAdresseDomiciliation() {
        return adresseDomiciliation;
    }

    public void setAdresseDomiciliation(ArrayList<Adresse> adresseDomiciliation) {
        this.adresseDomiciliation = adresseDomiciliation;
    }

    public ArrayList<Adresse> getAdresseFacturation() {
        return adresseFacturation;
    }

    public void setAdresseFacturation(ArrayList<Adresse> adresseFacturation) {
        this.adresseFacturation = adresseFacturation;
    }

    
    
    public String getPrenomClient() {
        return prenomClient;
    }

    /**
     * Définit le prénom de l'employe
     * Le prénom est par défaut trimmer et 
     * converti en minuscule avec la première
     * lettre en majuscule
     * @param prenomClient 
     */
    public void setPrenomClient(String prenomClient) throws Exception {
         if (prenomClient.equals("")){
            throw new Exception("Prenom Vide");
        }
        if(prenomClient.length() > 50){
            throw new Exception("erreur : le prenom ne peut pas dépasser 50 caractères");
        }
        if(prenomClient.matches("[0-9]*")){
            throw new Exception("Prenom non valide");
        }
        else{
        prenomClient = prenomClient.trim();
        this.prenomClient = prenomClient.substring(0, 1).toUpperCase() +
                                prenomClient.substring(1).toLowerCase();
    }
    }
    public String getLoginClient() {
        return loginClient;
    }

    public void setLoginClient(String loginClient) throws Exception {
         if (loginClient.equals("")){
            throw new Exception("login Vide");
        }
         if(loginClient.length() > 50){
            throw new Exception("erreur : le login ne peut pas dépasser 50 caractères");
        }
         
        this.loginClient = loginClient.trim();
    }

    public String getMdpClient() {
        return mdpClient;
    }

    public void setMdpClient(String mdpClient) throws  InvalidePasswordException {
        if (mdpClient.length() < Client.MDPMINSIZE){
            throw new InvalidePasswordException("Mot de passe trop court [min = "+ 
                     Client.MDPMINSIZE + "]");
        }
        this.mdpClient = mdpClient;
    }
    
    /**
     * Encode le mot de passe.
     * Le mot de passe ne peut être inférieur à {@code MDPMINSIZE}
     * @throws InvalidePasswordException 
     */
    public void encodeMdpClient() throws InvalidePasswordException{
        if (mdpClient.length() < Client.MDPMINSIZE){
            throw new InvalidePasswordException("Mot de passe trop court [min = "+ 
                     Client.MDPMINSIZE + "]");
        }
        this.mdpClient = SqlManager.encodeString(this.mdpClient);
    }
    
    public String getEmailClient() {
        return emailClient.trim();
    }

    public void setEmailClient(String emailClient) throws Exception {
         if (emailClient.equals("")){
            throw new Exception("email Vide");
        }
        if (!emailPattern.matcher(emailClient).matches()) {
            throw new InvalidEmailException("Adresse e-mail [" + emailClient + "] est invalide");
        }
        this.emailClient = emailClient.trim();
    }

    public LocalDate getDateNaissanceClient() {
        return dateNaissanceClient;
    }

    public void setDateNaissanceClient(LocalDate dateNaissanceClient) throws Exception {
         if (dateNaissanceClient.equals("")){
            throw new Exception("date de naissance Vide");
        }
        this.dateNaissanceClient = dateNaissanceClient;
    }

    public void select() throws SQLException {
        SqlManager sql3 = new SqlManager();

        String req = "SELECT "
                        + "nomClient, "
                        + "prenomClient, "
                        + "loginClient, "
                        + "mdpClient, "
                        + "emailClient, "
                        + "dateNaissanceClient, "
                        + "statutClient, "
                        + "derniereConnexionClient "
                       
                    + " FROM "
                        + "Client "
                    + " WHERE "
                        + " idClient = ?";

        //System.out.println("req = "+req);
        try (
                Connection cnt = sql3.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {

            // Définir l'idAdresse de ma requete
            pstm.setInt(1, idClient);
            
            // Executer ma requete sur le serveur MySQL
            // Puis récupérer le résultat de la requete dans rs
            ResultSet rs = pstm.executeQuery();

            // debut de recherche sur 1 SEUL resultat [rs.next]
            if (rs.next()) {
                setNomClient(rs.getString("nomClient"));
                setPrenomClient(rs.getString("prenomClient"));
                setLoginClient(rs.getString("loginClient"));
                setMdpClient(rs.getString("mdpClient"));
                setEmailClient(rs.getString("emailClient"));
                setDateNaissanceClient((rs.getDate("dateNaissanceClient")).toLocalDate());
                setStatutClient(rs.getString("StatutClient"));
                setDerniereConnexionClient((rs.getDate("derniereConnexionClient")).toLocalDate());
                
            } else {
                // Aucun résultat de retourner
                // ERROR
                throw new IdInconnuSQLException("Id client non connu");
            }

        } catch (IdInconnuSQLException ex) {
            throw ex;
        } catch (SQLException ex) {
            System.err.println("erreur sql : " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            System.err.println("erreur sql : " + ex.getMessage());
        }

    }
    
   
    public void ajouterClient()throws Exception{
        
        SqlManager sql2 = new SqlManager();

        String req = "INSERT into Client("
                + "nomClient, "
                + "prenomClient, "
                + "loginClient, "
                + "mdpClient, "
                + "emailClient, "
                + "dateNaissanceClient, "
                + "statutClient, "
                + "derniereConnexionClient"
                + ") "
                + "values(?,?,?,?,?,?,?,?)";
        //System.out.println("req = "+req);

        try (
                Connection cnt = sql2.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {
            pstm.setString(1, getNomClient());
            pstm.setString(2, getPrenomClient());
            pstm.setString(3, getLoginClient());
            pstm.setString(4, getMdpClient());
            pstm.setString(5, getEmailClient());
            pstm.setDate(6, java.sql.Date.valueOf(getDateNaissanceClient()));
            pstm.setString(7, getStatutClient());
            pstm.setDate(8, java.sql.Date.valueOf(getDerniereConnexionClient()));
           

            pstm.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("erreur : " + ex.getMessage());
        }
    }
    
public static ArrayList<Client> getSearchListClient(String nomClient, String prenomClient, String statut) {
        ArrayList<Client> ArrayListClient = new ArrayList<>();
        
        Boolean isValid = false;
        
        String query = "SELECT  idClient"
                    + " FROM "
                        + "Client "
                + " WHERE ";
        
        if (nomClient != null){
            query = query +  " nomClient LIKE ? AND";
            nomClient = nomClient.replaceAll("\\*", "%");
            isValid = true;
        }
        if (prenomClient != null){
            query = query +  " prenomClient LIKE ? AND";
            prenomClient = prenomClient.replaceAll("\\*", "%");
            isValid = true;
        }
        if (statut != null){
            query = query +  " statutClient = ? AND";
            isValid = true;
        }
        
        query = query.substring(0, query.length()-3);
        
        System.out.println(query);
        
        if (!isValid){
            return new ArrayList<>();
        }
        
        try (
                Connection cnx = new SqlManager().GetConnection();
                PreparedStatement stmt = cnx.prepareStatement(query);
            ){
            
            int i = 1;
            if (nomClient != null){
                stmt.setString(i++, nomClient);
            }
            if (prenomClient != null){
                stmt.setString(i++, prenomClient);
            }
            if (statut != null){
                stmt.setString(i++, statut);
            }
                
                
                ResultSet rs   = stmt.executeQuery();
                
            while( rs.next()) {
                Client c = new Client(rs.getInt("idClient"));
                ArrayListClient.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            ex.printStackTrace();
            return new ArrayList<Client>();
        } catch (Exception ex) {
            System.err.println("Oops:SQL:" + ex.getMessage());
            return new ArrayList<Client>();
         }
        
        return ArrayListClient;
    }
    
    
    public static ArrayList<Client> getArrayListAllClient() throws Exception{
        ArrayList<Client> ArrayListClient = new ArrayList<>();
        
        String query = "SELECT  idClient,"
                        + "nomClient, "
                        + "prenomClient, "
                        + "loginClient, "
                        + "mdpClient, "
                        + "emailClient, "
                        + "dateNaissanceClient, "
                        + "statutClient, "
                        + "dateDerniereConnexionClient, "
                       
                    + " FROM "
                        + "Client "
                   ;
        
        try (
                Connection cnx = new SqlManager().GetConnection();
                Statement stmt = cnx.createStatement();
                ResultSet rs   = stmt.executeQuery(query);
            ){
            while( rs.next()) {
                Client c = new Client();   
                c.setIdClient(rs.getInt("idClient"));
                c.setNomClient(rs.getString("nomClient"));    
                c.setPrenomClient(rs.getString("prenomClient"));
                c.setLoginClient(rs.getString("loginClient"));
                c.setMdpClient(rs.getString("mdpClient"));
                c.setEmailClient(rs.getString("emailClient"));
                c.setDateNaissanceClient(rs.getDate("dateNaissanceClient").toLocalDate());
                c.setStatutClient(rs.getString("statutClient"));
                c.setDerniereConnexionClient(rs.getDate ("dateNaissanceClient").toLocalDate() );
                
                ArrayListClient.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
            return null;
        }
        
        return ArrayListClient;
    }
    
    public void modifierClient() throws Exception {
        
        
        // requete SQL sur table domiciliation
        SqlManager sql2 = new SqlManager();

        String req = "UPDATE Client SET "
                + "nomClient=?, "
                + "prenomClient=?, "
                + "loginClient=?, "
                + "mdpClient=?, "
                + "emailClient=?, "
                + "dateNaissanceClient=?, "
                + "statutClient=?, "
                + "derniereConnexionClient=? " 
                + " WHERE "
                + "idClient=?";
        //System.out.println("req = "+req);

        try (
                Connection cnt = sql2.GetConnection();
                CallableStatement cstm = cnt.prepareCall(req);) {
            cstm.setString(1, getNomClient());
            cstm.setString(2, getPrenomClient());
            cstm.setString(3, getLoginClient());
            cstm.setString(4, getMdpClient());
            cstm.setString(5, getEmailClient());
            cstm.setDate(6, java.sql.Date.valueOf(getDateNaissanceClient()));
            cstm.setString(7, getStatutClient());
            cstm.setDate(8, java.sql.Date.valueOf(getDerniereConnexionClient()));
            cstm.setInt(9, getIdClient());
            
            cstm.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("erreur : " + ex.getMessage());
        }
    }
    
    /**
     * 
     * @param nouvelleAdresse
     * @throws ClassNotFoundException 
     */

    public void ajouterAdresseDomiciliation(Adresse nouvelleAdresse) throws ClassNotFoundException {
        
        this.ajouterAdresse(nouvelleAdresse);
        // requete SQL sur table domiciliation
        SqlManager sql2 = new SqlManager();

        String req = "INSERT into Domiciliation("
                + "idAdresse, "
                + "idClient "
                + ") "
                + "values(?,?)";
        //System.out.println("req = "+req);

        try (
                Connection cnt = sql2.GetConnection();
                CallableStatement cstm = cnt.prepareCall(req);) {
            
            
            
            cstm.setInt(1, nouvelleAdresse.getIdAdresse());
            cstm.setInt(2, this.getIdClient());
           
            // Si jamais le SQL plante alors je ne rentre pas l'info dans l'arraylist
            this.adresseDomiciliation.add(nouvelleAdresse);

        } catch (SQLException ex) {
            System.out.println("erreur : " + ex.getMessage());
        }
        
        
    }
    

    public void ajouterAdresseFacturation(Adresse nouvelleAdresse) throws ClassNotFoundException {
        
        this.ajouterAdresse(nouvelleAdresse);
        // requete SQL sur table facturation
      SqlManager sql2 = new SqlManager();

        String req = "INSERT into Domiciliation("
                + "idAdresse, "
                + "idClient "
                + ") "
                + "values(?,?)";
        //System.out.println("req = "+req);

        try (
                Connection cnt = sql2.GetConnection();
                CallableStatement cstm = cnt.prepareCall(req);
                ) {

            cstm.setInt(1, nouvelleAdresse.getIdAdresse());
            cstm.setInt(2, this.getIdClient());

            this.adresseFacturation.add(nouvelleAdresse);

        } catch (SQLException ex) {
            System.out.println("erreur : " + ex.getMessage());
        }
    }
    
    

    private void ajouterAdresse(Adresse nouvelleAdresse) {
        
        SqlManager sql2 = new SqlManager();

        String req = "INSERT into Adresse("
                + "nomAdresse, "
                + "destinataireAdresse, "
                + "typeVoie, "
                + "numVoie, "
                + "nomVoie, "
                + "complement, "
                + "codePostal, "
                + "ville, "
                + "pays "
                + ") "
                + "values(?,?,?,?,?,?,?,?,?)";
        //System.out.println("req = "+req);

        try (
                Connection cnt = sql2.GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {
            int i =1;
            pstm.setString(i++,nouvelleAdresse.getNomAdresse());
            pstm.setString(i++, nouvelleAdresse.getDestinataireAdresse());
            pstm.setString(i++, nouvelleAdresse.getTypeVoie());
            pstm.setString(i++, nouvelleAdresse.getNumVoie());
            pstm.setString(i++, nouvelleAdresse.getNomVoie());
            pstm.setString(i++, nouvelleAdresse.getComplement());
            pstm.setString(i++, nouvelleAdresse.getCodePostal());
            pstm.setString(i++, nouvelleAdresse.getVille());
            pstm.setString(i++, nouvelleAdresse.getPays());

            
             pstm.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("erreur : " + ex.getMessage());
        }
    }
    
    public void modifierAdresseDomiciliation(Adresse nouvelleAdresse) throws ClassNotFoundException, Exception {
        
        
        // requete SQL sur table adresse
        SqlManager sql2 = new SqlManager();

        String req = "UPDATE Domiciliation SET "
                + " idAdresse=?,"
                + " idClient=? "                       
                + " WHERE "
                + " idAdresse=? ";
        //System.out.println("req = "+req);

        try (
                Connection cnt = sql2.GetConnection();
                CallableStatement cstm = cnt.prepareCall(req);) {
            cstm.setInt(1, nouvelleAdresse.getIdAdresse());
            cstm.setInt(2, this.getIdClient());
            cstm.setInt(3, nouvelleAdresse.getIdAdresse());
            
            
            cstm.executeUpdate();
            

        } catch (SQLException ex) {
            System.out.println("erreur : " + ex.getMessage());
        }
    }
    
    public void modifierAdresseFacturation(Adresse nouvelleAdresse) throws ClassNotFoundException, Exception {
        
        
        // requete SQL sur table adresse
        SqlManager sql2 = new SqlManager();

        String req = "UPDATE Facturation SET "
                + " idAdresse =?,"
                + "idClient=? "              
                + " WHERE "
                + " idAdresse=?";
        //System.out.println("req = "+req);

        try (
                Connection cnt = sql2.GetConnection();
                CallableStatement cstm = cnt.prepareCall(req);
            ) {
            cstm.setInt(1, nouvelleAdresse.getIdAdresse());
            cstm.setInt(2, this.getIdClient());
            cstm.setInt(3, nouvelleAdresse.getIdAdresse());
            
            cstm.executeUpdate();
            

        } catch (SQLException ex) {
            System.out.println("erreur : " + ex.getMessage());
        }
    }
    
    
    public void modifierAdresse(Adresse nouvelleAdresse) {
        
        
        // requete SQL sur table adresse
        SqlManager sql2 = new SqlManager();

        String req = "UPDATE Adresse SET "
                + " nomAdresse =?,"
                + " destinataireAdresse=?, "
                + " typeVoie=?,"
                + " numVoie=?,"
                + " nomVoie=?,"
                + " complement=?,"
                + " codePostal=?,"
                + " ville=?,"
                + " pays=?"  
                + " WHERE "
                + " idAdresse=?";
        //System.out.println("req = "+req);

        try (
                Connection cnt = sql2.GetConnection();
                CallableStatement cstm = cnt.prepareCall(req);
            ) {
            cstm.setString(1, nouvelleAdresse.getNomAdresse());
            cstm.setString(2, nouvelleAdresse.getDestinataireAdresse());
            cstm.setString(3, nouvelleAdresse.getTypeVoie());
            cstm.setString(4, nouvelleAdresse.getNumVoie());
            cstm.setString(5, nouvelleAdresse.getNomVoie());
            cstm.setString(6, nouvelleAdresse.getComplement());
            cstm.setString(7, nouvelleAdresse.getCodePostal());
            cstm.setString(8, nouvelleAdresse.getVille());
            cstm.setString(9, nouvelleAdresse.getPays());
            cstm.setInt(10, nouvelleAdresse.getIdAdresse());
            
            cstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur : " + ex.getMessage());
        }
    }
    
    public void supprimerAdresseDomiciliation(Adresse nouvelleAdresse) {

        
        // requete SQL sur table domiciliation
        SqlManager sql2 = new SqlManager();

        String req = "DELETE FROM Domiciliation" 
                + " WHERE"
                + " idAdresse=? AND idClient = ?";
        //System.out.println("req = "+req);

        try (
                Connection cnt = sql2.GetConnection();
                CallableStatement cstm = cnt.prepareCall(req);) {
            
            cstm.setInt(1, nouvelleAdresse.getIdAdresse());
            cstm.setInt(2, this.getIdClient());
            
            
           cstm.executeUpdate();
           
           this.adresseDomiciliation.remove(nouvelleAdresse);

        } catch (SQLException ex) {
            System.out.println("erreur : " + ex.getMessage());
        }
    

    }
    
    public void supprimerAdresseFacturation(Adresse nouvelleAdresse) {

        
        
        // requete SQL sur table facturation
        SqlManager sql2 = new SqlManager();

        String req = "DELETE FROM Facturation"
                + " WHERE "
                + " idAdresse=? AND idClient = ?";
        //System.out.println("req = "+req);

        try (
                Connection cnt = sql2.GetConnection();
                CallableStatement cstm = cnt.prepareCall(req);) {
            
            
            
            cstm.setInt(1, nouvelleAdresse.getIdAdresse());
            cstm.setInt(2, this.getIdClient());
            cstm.setInt(3, nouvelleAdresse.getIdAdresse());
            
            cstm.executeUpdate();
            
            this.adresseFacturation.remove(nouvelleAdresse);

        } catch (SQLException ex) {
            System.out.println("erreur : " + ex.getMessage());
        }
    

    }
    public Boolean loginExists(){
        
        String req = "SELECT COUNT(id) as counter "
                    + "FROM Client "
                + " WHERE emailClient = ? "
                + " AND idClient != ?";
        
        try (
                Connection cnt = new SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ){

            // Créer une requete
            pstm.setString(1, getEmailClient());
            pstm.setInt(2, getIdClient());
            
            pstm.executeUpdate();
                // Get new Id
                ResultSet rs = pstm.getGeneratedKeys();
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
    
    
    public void getAdressesFacturation(){
        // requete SQL sur table adresse
        SqlManager sql2 = new SqlManager();

        String req = "SELECT idAdresse"
                + " FROM Facturation "          
                + " WHERE "
                + " idClient=?";
        //System.out.println("req = "+req);

        try (
                Connection cnt = sql2.GetConnection();
                CallableStatement cstm = cnt.prepareCall(req);
            ) {
            
            cstm.setInt(1, this.getIdClient());
            
            ResultSet rs = cstm.executeQuery();
            
            while (rs.next()){
                Adresse sqlAdresse = new Adresse(rs.getInt("idAdresse"));
                this.adresseFacturation.add(sqlAdresse);
            }
            

        } catch (SQLException ex) {
            System.out.println("erreur : " + ex.getMessage());
        }
    }
    
    public void getAdressesDomiciliation(){
        // requete SQL sur table adresse
        SqlManager sql2 = new SqlManager();

        String req = "SELECT idAdresse"
                + " FROM Domiciliation "          
                + " WHERE "
                + " idClient=?";
        //System.out.println("req = "+req);

        try (
                Connection cnt = sql2.GetConnection();
                CallableStatement cstm = cnt.prepareCall(req);
            ) {
            
            cstm.setInt(1, this.getIdClient());
            
            ResultSet rs = cstm.executeQuery();
            
            while (rs.next()){
                Adresse sqlAdresse = new Adresse(rs.getInt("idAdresse"));
                this.adresseDomiciliation.add(sqlAdresse);
            }
            

        } catch (SQLException ex) {
            System.out.println("erreur : " + ex.getMessage());
        }
    }
    
    /*
     exemple de modification d'une adresse :
     adresseDomiciliation.get(2).setPays("Finland");
     */
    @Override
    public String toString() {
//        String info = getIdClient() + " " + getNomClient() + " " + getPrenomClient() + " "
//                + getLoginClient() + " " + getMdpClient() + " " + getEmailClient() + " " + getDateNaissanceClient() 
//                + " " +getStatutClient()+" "+ getDerniereConnexionClient();
//        return info;
        return getNomClient() + " " + getPrenomClient();
    }
}
