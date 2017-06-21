
package Commande;

public class LigneDeCommande {  
   
    int idLigneDeCommande;
    String isbnLivre;
    int quantiteLigneDeCommande;
    float prixUnitaireHTLigneDeCommande;
    float TVALigneDeCommande;
    float reductionLigneDeCommande;
    
    public LigneDeCommande(){}

    public int getIdLigneDeCommande() {
        return idLigneDeCommande;
    }

    public String getIsbnLivre() {
        return isbnLivre;
    }

    public int getQuantiteLigneDeCommande() {
        return quantiteLigneDeCommande;
    }

    public float getPrixUnitaireHTLigneDeCommande() {
        return prixUnitaireHTLigneDeCommande;
    }

    public float getTVALigneDeCommande() {
        return TVALigneDeCommande;
    }

    public float getReductionLigneDeCommande() {
        return reductionLigneDeCommande;
    }
    
    public void setIdLigneDeCommande(int idLigneDeCommande) {
        this.idLigneDeCommande = idLigneDeCommande;
    }

    public void setIsbnLivre(String isbnLivre) {
        this.isbnLivre = isbnLivre;
    }

    public void setQuantiteLigneDeCommande(int quantiteLigneDeCommande) {
        this.quantiteLigneDeCommande = quantiteLigneDeCommande;
    }

    public void setPrixUnitaireHTLigneDeCommande(float prixUnitaireHTLigneDeCommande) {
        this.prixUnitaireHTLigneDeCommande = prixUnitaireHTLigneDeCommande;
    }

    public void setTVALigneDeCommande(float TVALigneDeCommande) {
        this.TVALigneDeCommande = TVALigneDeCommande;
    }

    public void setReductionLigneDeCommande(float reductionLigneDeCommande) {
        this.reductionLigneDeCommande = reductionLigneDeCommande;
    }

    @Override
    public String toString() {
        return "LigneDeCommande{" + "idLigneDeCommande=" + idLigneDeCommande + ", numCommande=" + ", isbnLivre=" + isbnLivre + ", quantiteLigneDeCommande=" + quantiteLigneDeCommande + ", prixUnitaireHTLigneDeCommande=" + prixUnitaireHTLigneDeCommande + ", TVALigneDeCommande=" + TVALigneDeCommande + ", reductionLigneDeCommande=" + reductionLigneDeCommande + '}';
    }



}