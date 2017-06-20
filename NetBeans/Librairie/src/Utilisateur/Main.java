package Utilisateur;


import Exception.IdInconnuSQLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, Exception {
        
        
        System.out.println(Client.getSearchListClient("*b*", null, "cliact"));
        
        
//        try {
//            Adresse nouvelleAdresse = new Adresse(15);
//        } catch (IdInconnuSQLException ex){
//            System.err.println("ID Inconnue");
//        } catch (SQLException ex) {
//            System.err.println("Il ya eu une erreur : " + ex.getMessage());
//        }
//        
//        Adresse monAdresse=new Adresse();
//        monAdresse.setNomAdresse("domicile");
//        monAdresse.setDestinataireAdresse("tata");
//        monAdresse.setTypeVoie("");
//        monAdresse.setNumVoie("");
//        monAdresse.setNomVoie("du marché");
//        monAdresse.setComplement("");
//        monAdresse.setCodePostal("75000");
//        monAdresse.setVille("Paris");
//        monAdresse.setPays("");
//        
//        
//        
//        System.out.println(monAdresse);
//        
//        
//        Client Diana = new Client();
//        
//        Diana.setNomClient("poipoi");
//        Diana.setPrenomClient("jean-mich");
//        Diana.setLoginClient("pouetpouet");
//        Diana.setMdpClient("supermancraint");
//        Diana.setEmailClient("wonderwoman@gmail.com");
//        Diana.setDateNaissanceClient(LocalDate.of(2017, 02, 9));
//        Diana.setStatutClient("cliAct");
//        Diana.setDerniereConnexionClient(LocalDate.of(2017, 03, 9));
        
//        Diana.ajouterClient();
//        Diana.ajouterAdresseDomiciliation(monAdresse);
       
        
//        Diana.setNomClient("puipui");
//        Diana.modifierClient();
//        monAdresse.setCodePostal("72015");
//        Diana.modifierAdresseDomiciliation(monAdresse);
//        System.out.println(monAdresse);
        
        
//        monAdresse.setTypeVoie("rue");
//        Diana.supprimerAdresseDomiciliation(monAdresse);
//        System.out.println(monAdresse);
//        Client c01=new Client(25, "toto", "tata");
//        c01.setEmailClient("toto.tata@0.com");
        
        
        // Recupération d'un client a partir de son id
//        Client monClient = null;
//        try{
//            monClient = new Client(3);
//            System.out.println(monClient);
//            monClient.setNomClient("Bibul");
//            System.out.println(monClient);
//            monClient.modifierClient();
//            
//             
//        } catch (SQLException ex){
//            JOptionPane.showMessageDialog(null, "Oups il y a eu un problème !!!\n" 
//                        + ex.getMessage(), 
//                    "Erreur SQL", 
//                    JOptionPane.ERROR_MESSAGE);
//        }
        
         
//        
        
        // Recupération d'une adresse a partir de son id
        

//        try{
//            Client diana = new Client(23);
//            System.out.println("Client en cours :");
//            System.out.println(diana);
//            
//            System.out.println("Les adresses fact de mon client");
//            for (Adresse myAdresseFacturation : diana.getAdresseFacturation()){
//                System.out.println(myAdresseFacturation);
//            }
//            
//            Adresse myAdd = (diana.getAdresseFacturation()).get(0);
//            
//            
//            System.out.println("-------------------");
//            System.out.println(myAdd);
//            
//            System.out.println("----------MODIF---------");
//            myAdd.setCodePostal("72600");
//            System.out.println(myAdd);
//            
//            diana.modifierAdresse(myAdd);
            
            
//    try{
//            Client diana = new Client(23);
//            System.out.println("Client en cours :");
//            System.out.println(diana);
//            
//            System.out.println("Les adresses domiciliation de mon client");
//            for (Adresse myAdresseDomiciliation : diana.getAdresseDomiciliation()){
//                System.out.println(myAdresseDomiciliation);
//            }
//            
//            Adresse myAdd = (diana.getAdresseDomiciliation()).get(0);
//            
//            
//            System.out.println("-------------------");
//            System.out.println(myAdd);
//            
//            System.out.println("----------MODIF---------");
//            myAdd.setCodePostal("26356");
//            System.out.println(myAdd);
//            
//            diana.modifierAdresse(myAdd);        
            
            
//            try{
//            Client diana = new Client(23);
//            System.out.println("Client en cours :");
//            System.out.println(diana);
//            
//            System.out.println("Les adresses domiciliation de mon client");
//            for (Adresse myAdresseDomiciliation : diana.getAdresseDomiciliation()){
//                System.out.println(myAdresseDomiciliation);
//            }
//            
//            Adresse myAdd = (diana.getAdresseDomiciliation()).get(0);
//            
//            
//            System.out.println("-------------------");
//            System.out.println(myAdd);
//            
//            System.out.println("----------MODIF---------");
////            myAdd.setCodePostal("26356");
////            System.out.println(myAdd);
//            
//            diana.supprimerAdresseDomiciliation(myAdd); 
//            
//            
//        }
//        catch(SQLException ex){
//            JOptionPane.showMessageDialog(null, "oups pb\n" +ex.getMessage(),
//                    "Erreur SQL",JOptionPane.ERROR_MESSAGE);
//        }
//       
        
        
//        if(setNomClient().length==0){
//            System.out.println("Le champs" + nomClient + "est vide");    
//        }
//        if(setPrenomClient().length==0){
//            System.out.println("Le champs" + prenomClient + "est vide");    
//        }
        
        
        
//        InputStreamReader isr = new InputStreamReader(System.in);
//        BufferedReader br = new BufferedReader(isr);
//        ListeAdresse liste = new ListeAdresse();
//        
//        
//        int i = 0;
//        do {
//            System.out.println(" Voulez vous ajouter une adresse ?");
//            System.out.println("1:oui");
//            System.out.println("2:non");
//            String saisi = br.readLine();
//            int choix = 0;
//
//            try{
//                choix = Integer.valueOf(saisi);
//
//            } catch (Exception ex) {
//
//                System.out.println("\t" + ex.getMessage());
//                
//                System.err.println("\tce n'est pas un entier!");
//
//            } finally {
//
//            }
//            
//            
//            if (choix == 1) {
//                System.out.println("choisir un type d'adresse");
//                System.out.println("1 : domiciliation");
//                System.out.println("2 : facturation");
//
//                String saisi2 = br.readLine();
//                int choix2 = 0;
//                try {
//
//                    choix2 = Integer.valueOf(saisi2);
//
//                } catch (Exception ex) {
//
//                    System.out.println("\t" + ex.getMessage());
//                    
//                    System.err.println("\tce n'est pas un entier!");
//
//                } finally {
//
//                }
//                
//                if (choix2 == 1) {
//                
//                    System.out.println("Entrez un nom d'adresse");
//                    String nomAdresse = br.readLine();
//
//                    nomAdresse = nomAdresse.trim();
//
//                    if (nomAdresse.equals("")) {
//
//                        System.out.println("Erreur de donnée saisie");
//                        System.out.println("entrer un nom d'adresse");
//                        nomAdresse = br.readLine();
//
//                    }
//
//                    System.out.println("nomAdresse : " + nomAdresse);
//                    a01.setNomAdresse(nomAdresse);
//
//                    System.out.println("Entrez un destinataire d'adresse");
//                    String destinataireAdresse = br.readLine();
//
//                    destinataireAdresse = destinataireAdresse.trim();
//
//                    if (destinataireAdresse.equals("")) {
//
//                        System.out.println("Erreur de donnée saisie");
//                        System.out.println("entrer un destinataire d'adresse");
//                        destinataireAdresse = br.readLine();
//
//                    }
//
//                    System.out.println("destinataireAdresse : " + destinataireAdresse);
//                    a01.setDestinataireAdresse(destinataireAdresse);
//
//                    System.out.println("Entrez un type de voie");
//                    String typeVoie = br.readLine();
//
//                    typeVoie = typeVoie.trim();
//
//                    if (typeVoie.equals("")) {
//
//                        System.out.println("Erreur de donnée saisie");
//                        System.out.println("entrer un type de voie");
//                        typeVoie = br.readLine();
//
//                    }
//
//                    System.out.println("typeVoie : " + typeVoie);
//                    a01.setTypeVoie(typeVoie);
//
//                    System.out.println("entrer un numero de voie");
//                    String numVoie = br.readLine();
//                    numVoie = numVoie.trim();
//
//                    if (numVoie.equals("")) {
//
//                        System.out.println("Erreur de donnée saisie");
//                        System.out.println("entrer un numero de voie");
//                        numVoie = br.readLine();
//
//                    }
//
//                    System.out.println("numVoie : " + numVoie);
//                    a01.setNumVoie(numVoie);
//
//                    System.out.println("Entrez un nom de voie");
//                    String nomVoie = br.readLine();
//
//                    nomVoie = nomVoie.trim();
//
//                    if (nomVoie.equals("")) {
//
//                        System.out.println("Erreur de donnée saisie");
//                        System.out.println("entrer un nom de voie");
//                        nomVoie = br.readLine();
//
//                    }
//
//                    System.out.println("nomVoie : " + nomVoie);
//                    a01.setNomVoie(nomVoie);
//
//                    System.out.println("entrer un complement");
//                    String complement = br.readLine();
//                    complement = complement.trim();
//
//                    if (complement.equals("")) {
//
//                        System.out.println("Erreur de donnée saisie");
//                        System.out.println("entrer un complement");
//                        complement = br.readLine();
//
//                    }
//
//                    System.out.println("complement :" + complement);
//                    a01.setComplement(complement);
//
//                    System.out.println("Entrez un code postal");
//                    String codePostal = br.readLine();
//
//                    codePostal = codePostal.trim();
//
//                    if (codePostal.equals("")) {
//
//                        System.out.println("Erreur de donnée saisie");
//                        System.out.println("entrer un code postal");
//                        codePostal = br.readLine();
//
//                    }
//
//                    System.out.println("codePostal : " + codePostal);
//                    a01.setCodePostal(codePostal);
//
//                    System.out.println("Entrez une ville");
//                    String ville = br.readLine();
//
//                    ville = ville.trim();
//
//                    if (ville.equals("")) {
//
//                        System.out.println("Erreur de donnée saisie");
//                        System.out.println("entrer une ville");
//                        ville = br.readLine();
//
//                    }
//
//                    System.out.println("ville : " + ville);
//                    a01.setVille(ville);
//
//                    System.out.println("Entrez un pays");
//                    String pays = br.readLine();
//
//                    pays = pays.trim();
//
//                    if (pays.equals("")) {
//
//                        System.out.println("Erreur de donnée saisie");
//                        System.out.println("entrer un pays");
//                        pays = br.readLine();
//
//                    }
//
//                    System.out.println("pays : " + pays);
//                    a01.setPays(pays);
//
//                    if (choix2 == 2) {
//                        System.out.println("Entrez un nom d'adresse");
//                        nomAdresse = br.readLine();
//
//                        nomAdresse = nomAdresse.trim();
//
//                        if (nomAdresse.equals("")) {
//
//                            System.out.println("Erreur de donnée saisie");
//                            System.out.println("entrer un nom d'adresse");
//                            nomAdresse = br.readLine();
//
//                        }
//
//                        System.out.println("nom : " + nomAdresse);
//                        a01.setNomAdresse(nomAdresse);
//
//                        System.out.println("Entrez un destinataire d'adresse");
//                        destinataireAdresse = br.readLine();
//
//                        destinataireAdresse = destinataireAdresse.trim();
//
//                        if (destinataireAdresse.equals("")) {
//
//                            System.out.println("Erreur de donnée saisie");
//                            System.out.println("entrer un destinataire d'adresse");
//                            destinataireAdresse = br.readLine();
//
//                        }
//
//                        System.out.println("destinataireAdresse : " + destinataireAdresse);
//                        a01.setDestinataireAdresse(destinataireAdresse);
//
//                        System.out.println("Entrez un type de voie");
//                        typeVoie = br.readLine();
//
//                        typeVoie = typeVoie.trim();
//
//                        if (typeVoie.equals("")) {
//
//                            System.out.println("Erreur de donnée saisie");
//                            System.out.println("entrer un type de voie");
//                            typeVoie = br.readLine();
//
//                        }
//
//                        System.out.println("typeVoie : " + typeVoie);
//                        a01.setTypeVoie(typeVoie);
//
//                        System.out.println("entrer un numero de voie");
//                        numVoie = br.readLine();
//                        numVoie = numVoie.trim();
//
//                        if (numVoie.equals("")) {
//
//                            System.out.println("Erreur de donnée saisie");
//                            System.out.println("entrer un numero de voie");
//                            numVoie = br.readLine();
//
//                        }
//
//                        System.out.println("numVoie : " + numVoie);
//                        a01.setNumVoie(numVoie);
//
//                        System.out.println("Entrez un nom de voie");
//                        nomVoie = br.readLine();
//
//                        nomVoie = nomVoie.trim();
//
//                        if (nomVoie.equals("")) {
//
//                            System.out.println("Erreur de donnée saisie");
//                            System.out.println("entrer un nom de voie");
//                            nomVoie = br.readLine();
//
//                        }
//
//                        System.out.println("nomVoie : " + nomVoie);
//                        a01.setNomVoie(nomVoie);
//
//                        System.out.println("entrer un complement");
//                        complement = br.readLine();
//                        complement = complement.trim();
//
//                        if (complement.equals("")) {
//
//                            System.out.println("Erreur de donnée saisie");
//                            System.out.println("entrer un complement");
//                            complement = br.readLine();
//
//                        }
//
//                        System.out.println("complement :" + complement);
//                        a01.setComplement(complement);
//
//                        System.out.println("Entrez un code postal");
//                        codePostal = br.readLine();
//
//                        codePostal = codePostal.trim();
//
//                        if (codePostal.equals("")) {
//
//                            System.out.println("Erreur de donnée saisie");
//                            System.out.println("entrer un code postal");
//                            codePostal = br.readLine();
//
//                        }
//
//                        System.out.println("codePostal : " + codePostal);
//                        a01.setCodePostal(codePostal);
//
//                        System.out.println("Entrez une ville");
//                        ville = br.readLine();
//
//                        ville = ville.trim();
//
//                        if (ville.equals("")) {
//
//                            System.out.println("Erreur de donnée saisie");
//                            System.out.println("entrer une ville");
//                            ville = br.readLine();
//
//                        }
//
//                        System.out.println("ville : " + ville);
//                        a01.setVille(ville);
//
//                        System.out.println("Entrez un pays");
//                        pays = br.readLine();
//
//                        pays = pays.trim();
//
//                        if (pays.equals("")) {
//
//                            System.out.println("Erreur de donnée saisie");
//                            System.out.println("entrer un pays");
//                            pays = br.readLine();
//
//                        }
//
//                        System.out.println("pays : " + pays);
//                        a01.setPays(pays);
//
//                    }
//                    
//                }
//            }
//            
//       }while (i == liste.taille());
//        
//        
//        br.close();
//        isr.close();
//        
        }
}