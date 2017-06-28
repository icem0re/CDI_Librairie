package Commande;

public class LigneDeCommande {

    int idLigneDeCommande;
    String isbnLivre;
    int quantiteLigneDeCommande;
    float prixUnitaireHTLigneDeCommande;
    float TVALigneDeCommande;
    float reductionLigneDeCommande;

    public LigneDeCommande() {
        setIdLigneDeCommande(0);
    }

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
        return  "\n idLigneDeCommande=" + idLigneDeCommande + "\n numCommande=" + "\n isbnLivre=" + isbnLivre + "\n quantiteLigneDeCommande=" + quantiteLigneDeCommande + "\n prixUnitaireHTLigneDeCommande=" + prixUnitaireHTLigneDeCommande + "\n TVALigneDeCommande=" + TVALigneDeCommande + "\n reductionLigneDeCommande=" + reductionLigneDeCommande + "\n\n";
    }
}

    
    