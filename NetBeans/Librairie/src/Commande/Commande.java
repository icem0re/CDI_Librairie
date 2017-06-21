package Commande;

import Exception.IdSQLException;
import SqlManager.SqlManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

public class Commande {

    private int numCommande;
    private int idAdresseFacturation;
    private int idAdresseLivraison;
    private int idClient;
    private LocalDate dateCommande;
    private LocalDate datePaiementCommande;
    private LocalDate datePreparationCommande;
    private LocalDate dateExpeditionCommande;
    private LocalDate dateAccuseReceptionCommande;
    private LocalDate dateAnnulationCommande;
    private String statutCommande;
    private String ipCommande;  //TODO : setter & getter
    private String nomLivreur;
    private ArrayList<LigneDeCommande> LignesDeCommande;

    public Commande() {
        setNumCommande(0);
        this.datePaiementCommande=null;
        this.datePreparationCommande=null;
        this.dateExpeditionCommande=null;
        this.dateAccuseReceptionCommande=null;
        this.dateAnnulationCommande=null;
    }

    // Recupere depuis sql à partir d'une methode SELECT
    public Commande(int numCommande) throws IdSQLException, SQLException {
        this();
        setNumCommande(numCommande);
        getCommandeFromSQL();
    }

    // donnee fournit depuis un utilisateur 
    public Commande(int numCommande, int idClient, LocalDate dateCommande) {
        this();
        setNumCommande(numCommande);
        setIdClient(idClient);
        setDateCommande(dateCommande);
    }

    public void setNumCommande(int numCommande) {
        this.numCommande = numCommande;
    }

    public void setIdAdresseFacturation(int idAdresseFacturation) {
        this.idAdresseFacturation = idAdresseFacturation;
    }

    public void setIdAdresseLivraison(int idAdresseLivraison) {
        this.idAdresseLivraison = idAdresseLivraison;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public void setDatePaiementCommande(LocalDate datePaiementCommande) {
        this.datePaiementCommande = datePaiementCommande;
    }

    public void setDatePreparationCommande(LocalDate datePreparationCommande) {
        this.datePreparationCommande = datePreparationCommande;
    }

    public void setDateExpeditionCommande(LocalDate dateExpeditionCommande) {
        this.dateExpeditionCommande = dateExpeditionCommande;
    }

    public void setDateAccuseReceptionCommande(LocalDate dateAccuseReceptionCommande) {
        this.dateAccuseReceptionCommande = dateAccuseReceptionCommande;
    }

    public void setDateAnnulationCommande(LocalDate dateAnnulationCommande) {
        this.dateAnnulationCommande = dateAnnulationCommande;
    }

    public void setStatutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
    }

    public void setLignesDeCommande(ArrayList<LigneDeCommande> LignesDeCommande) {
        this.LignesDeCommande = LignesDeCommande;
    }

    public void setNomLivreur(String nomLivreur) {
        this.nomLivreur = nomLivreur;
    }

    public void setIpCommande(String ipCommande) {
        this.ipCommande = ipCommande;
    }

    public String getIpCommande() {
        return ipCommande;
    }

    public String getNomLivreur() {
        return nomLivreur;
    }

    public int getNumCommande() {
        return numCommande;
    }

    public int getIdAdresseFacturation() {
        return idAdresseFacturation;
    }

    public int getIdAdresseLivraison() {
        return idAdresseLivraison;
    }

    public int getIdClient() {
        return idClient;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public LocalDate getDatePaiementCommande() {
        return datePaiementCommande;
    }

    public LocalDate getDatePreparationCommande() {
        return datePreparationCommande;
    }

    public LocalDate getDateExpeditionCommande() {
        return dateExpeditionCommande;
    }

    public LocalDate getDateAccuseReceptionCommande() {
        return dateAccuseReceptionCommande;
    }

    public LocalDate getDateAnnulationCommande() {
        return dateAnnulationCommande;
    }

    public String getStatutCommande() {
        return statutCommande;
    }

    public ArrayList<LigneDeCommande> getLignesDeCommande() {
        return LignesDeCommande;
    }

    @Override
    public String toString() {
        return  "numCommande=" + numCommande + "\n idAdresseFacturation=" + idAdresseFacturation + "\n idAdresseLivraison=" + idAdresseLivraison + "\n idClient=" + idClient + "\n dateCommande=" + dateCommande + "\n datePaiementCommande=" + datePaiementCommande + "\n datePreparationCommande=" + datePreparationCommande + "\n dateExpeditionCommande=" + dateExpeditionCommande + "_\n dateAccuseReceptionCommande=" + dateAccuseReceptionCommande + "\n dateAnnulationCommande=" + dateAnnulationCommande + ",\n statutCommande=" + statutCommande + ",\n lignes de commandes="+ LignesDeCommande+"\n-----------------------------------\n";
        // return "NumCommande = " + numCommande;
    }

// requete SQL insertion de la commande dans la DB 
    public void insererCommande() {

        String req = "INSERT INTO Commande( "
                + "numCommande, "
                + "idAdresseFacturation, "
                + "idAdresseLivraison, "
                + "idClient, "
                + "dateCommande, "
                + "datePaiementCommande, "
                + "datePreparationCommande, "
                + "dateExpeditionCommande, "
                + "dateAccuseReceptionCommande, "
                + "dateAnnulationCommande, "
                + "statutCommande, "
                + "ipCommande, "
                + "nomLivreur, "
                + ") "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (
                Connection cnt = new SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);) {

            int i = 1;
            pstm.setInt(i++, getNumCommande());
            pstm.setInt(i++, getIdAdresseFacturation());
            pstm.setInt(i++, getIdAdresseLivraison());
            pstm.setInt(i++, getIdClient());
            pstm.setDate(i++, (java.sql.Date.valueOf(getDateCommande())));
            if (getDatePaiementCommande()==null){
                pstm.setNull(i++, java.sql.Types.DATE);
            } else {
                pstm.setDate(i++, (java.sql.Date.valueOf(getDatePaiementCommande())));
            }
            
            pstm.setDate(i++, (java.sql.Date.valueOf(getDatePreparationCommande())));
            pstm.setDate(i++, (java.sql.Date.valueOf(getDateExpeditionCommande())));
            pstm.setDate(i++, (java.sql.Date.valueOf(getDateAccuseReceptionCommande())));
            pstm.setDate(i++, (java.sql.Date.valueOf(getDateAnnulationCommande())));
            pstm.setString(i++, getStatutCommande());
            pstm.setString(i++, getIpCommande());
            pstm.setString(i++, getNomLivreur());

            if (pstm.executeUpdate() > 0) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                setNumCommande(rs.getInt(1));
            }

        } catch (SQLException ex) {
            System.err.println("3) erreur sql : " + ex.getMessage());
        }
    }
    
// retourne une commande en prenant un numCommande en paramètre d'entrée
    /**
     * 
     * @throws IdSQLException
     * @throws SQLException 
     */
    private void getCommandeFromSQL() throws IdSQLException, SQLException {
        
        String req = "SELECT "
                + "idAdresseFacturation"
                + "      ,idAdresseLivraison"
                + "      ,idClient"
                + "      ,dateCommande"
                + "      ,datePaiementCommande"
                + "      ,datePreparationCommande"
                + "      ,dateExpeditionCommande"
                + "      ,dateAccuseReceptionCommande"
                + "      ,dateAnnulationCommande"
                + "      ,statutCommande"
                + "      ,ipCommande"
                + "      ,nomLivreur"
                + " FROM Commande "
                + " WHERE numCommande = ?";

        try (
                Connection cnt = new SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {

            pstm.setInt(1, numCommande);

            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()) {
                
                setIdAdresseFacturation(rs.getInt("idAdresseFacturation"));
                setIdAdresseLivraison(rs.getInt("idAdresseLivraison"));
                setIdClient(rs.getInt("idClient"));
                setDateCommande(rs.getDate("dateCommande").toLocalDate());
                if (rs.getDate("datePaiementCommande") != null) {
                    setDatePaiementCommande(rs.getDate("datePaiementCommande").toLocalDate());
                }
                if (rs.getDate("datePreparationCommande") != null) {
                    setDatePreparationCommande(rs.getDate("datePreparationCommande").toLocalDate());
                }
                if (rs.getDate("dateExpeditionCommande") != null) {
                    setDateExpeditionCommande(rs.getDate("dateExpeditionCommande").toLocalDate());
                }
                if (rs.getDate("dateAccuseReceptionCommande") != null) {
                    setDateAccuseReceptionCommande(rs.getDate("dateAccuseReceptionCommande").toLocalDate());
                }
                if (rs.getDate("dateAnnulationCommande") != null) {
                    setDateAnnulationCommande(rs.getDate("dateAnnulationCommande").toLocalDate());
                }
                if (rs.getString("statutCommande") != null) {
                    setStatutCommande(rs.getString("statutCommande"));
                }
                if (rs.getString("ipCommande") != null) {
                    setIpCommande(rs.getString("ipCommande"));
                }
                if (rs.getString("nomLivreur") != null) {
                    setNomLivreur(rs.getString("nomLivreur"));
                }

            } else {
                throw new IdSQLException("Numéro de commande inconnu");
            }
            
        } catch (IdSQLException ex){
            throw ex;
        }
        catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     *
     * @param idClient
     * @return ArrayList<Commande>
     */
    // retourne toutes les commandes d'un client ( idclient )
    public static ArrayList<Commande> getTouteCommande(Integer idClient) throws SQLException {

        ArrayList<Commande> carnetCommande = new ArrayList();

        String req = "SELECT "
                + "numCommande"
                + " FROM Commande "
                + " WHERE idClient = ?";

        try (
                Connection cnt = new SqlManager().GetConnection();
                PreparedStatement pstm = cnt.prepareStatement(req);) {

            pstm.setInt(1, idClient);

            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();

            while (rs.next()) {
                Commande maCommande = new Commande(rs.getInt("numCommande"));
                carnetCommande.add(maCommande);
            }
        } catch (SQLException ex) {
            System.err.println("2) erreur sql : " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }

        return carnetCommande;
    }

    public void ajouterLdcCommande(LigneDeCommande elemCommande) {
        this.LignesDeCommande.add(elemCommande);
    }

//insertion SQL de la ligne de commande en fonction du num commande
    public void insererLdcCommande() {
        /**
         * TODO verif numcommande existe dans la DB,
         *
         * POUR CHAQUE LIGNE DE MA COMMANDE
         */
        for (LigneDeCommande L : LignesDeCommande) {
            String req = "INSERT INTO LigneDeCommande( "
                    + "numCommande, "
                    + "isbnLivre, "
                    + "quantiteLigneDeCommande, "
                    + "PrixUnitaireHTLigneDeCommande, "
                    + "TVALigneDeCommande, "
                    + "reductionLigneDeCommande"
                    + ")"
                    + "VALUES(?,?,?,?,?,?)";
            try (
                    Connection cnt = new SqlManager().GetConnection();
                    PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);) {

                int i = 1;
                pstm.setString(i++, L.getIsbnLivre());
                pstm.setInt(i++, L.getQuantiteLigneDeCommande());
                pstm.setFloat(i++, L.getPrixUnitaireHTLigneDeCommande());
                pstm.setFloat(i++, L.getTVALigneDeCommande());
                pstm.setFloat(i++, L.getReductionLigneDeCommande());

                int j = pstm.executeUpdate();
                System.out.println("nombre de lignes affectées : " + j);
            } catch (SQLException ex) {
                System.err.println("3) erreur sql : " + ex.getMessage());
            }
        }
    }
    
    public Vector getVectorCommande(){
        Vector v = new Vector();
        v.add(this);
        v.add(this.numCommande);
        v.add(this.idAdresseFacturation);
        v.add(this.idAdresseLivraison);
        v.add(this.idClient);
        v.add(this.dateCommande);
        v.add(this.datePaiementCommande);
        v.add(this.datePreparationCommande);
        v.add(this.dateExpeditionCommande);
        v.add(this.dateAccuseReceptionCommande);
        v.add(this.dateAnnulationCommande);
        v.add(this.statutCommande);
        v.add(this.ipCommande);
        v.add(this.LignesDeCommande);
        return v;
    }

}
