/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookStore;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateStringConverter;

/**
 *
 * @author cdi407
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
//        Livre livre1 = new Livre();
        Editeur ed1 = new Editeur("Gallimard");
        ed1.setStatutEditeur("EdiPause");
//        Auteur au1 = new Auteur();
//        Thematique tm1 = new Thematique();
        try {
            ////        try {
//            livre1.setIsbnLivre( "012345678X"); livre1.setNomTVA("TVA5%");
//            livre1.setEditeur(new Editeur("Gallimard"));
//            livre1.setTitreLivre("poipoi");
//            livre1.setSousTitreLivre("");
//            livre1.setDateParutionLivre(LocalDate.of(1994, 03, 01));
//            livre1.setResumeLivre("L'homme a tout exploré : le monde de l'espace, le monde sous-marin, le monde souterrain ; pourtant il lui manque la connaissance d'un monde : le continent des morts.Voilà la prochaine frontière.");
//            livre1.setExtraitLivre("Michael Pinson et son ami Raoul Razorbak, deux jeunes chercheurs sans complexes, veulent relever ce défi et, utilisant les techniques de médecine mais aussi d''astronautique les plus modernes, partent à la découverte du paradis.");
//            livre1.setPrixHTLivre(7.30f);
//            livre1.setPoidLivre(380);
//            livre1.setAffichageLivre(true);
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur de Newbie haha !", JOptionPane.ERROR_MESSAGE);
//        }
//            Editeur.AffichageEditeur();
//            ed1.deleteStatutEditeur("Le livre de poche");
            
//        au1.CreerAuteur();
//        livre1.CreerLivre();
//        tm1.CreerSousThematique();
//        livre1.AffichageLivre();
        // Livre.AffichageLivre();
//        Editeur.AffichageEditeur();
//        Auteur.AffichageAuteur();
//        Thematique.AffichageThematique();
//        Thematique.AffichageSousThematique();
        
//        System.out.println("--------------");
//        System.out.println("--------------");
//        System.out.println("--------------");
//        ed1.AffichageEditeur();
//        System.out.println("--------------");
//        System.out.println("--------------");
//        System.out.println("--------------");
//        au1.AffichageAuteur();
//        System.out.println("--------------");
//        System.out.println("--------------");
//        System.out.println("--------------");
//        tm1.AffichageThematique();
//        System.out.println("--------------");
//        System.out.println("--------------");
//        System.out.println("--------------");
//        tm1.AffichageSousThematique();
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//          ArrayList<Livre> tata = Livre.AffichageLivre();
//          System.out.println(tata.get(2));
//          
//          tata.get(2).setNomTVA("TVA5%");
//          tata.get(2).setEditeur(ed1);
//          tata.get(2).setTitreLivre("Lolilool");
//          tata.get(2).setSousTitreLivre("hahahlololololililoliloliloqljkhkq");
//          tata.get(2).setPoidLivre(25400);
//          tata.get(2).setDateParutionLivre(java.time.LocalDate.now());
//          tata.get(2).setResumeLivre("pas de resumé TG !");
//          tata.get(2).setExtraitLivre("Le vide est ma vie");
//          tata.get(2).setImageLivre("");
//          tata.get(2).setPrixHTLivre(15.6f);
//          tata.get(2).setAffichageLivre(true);
//          tata.get(2).setIsbnLivre(tata.get(2).getIsbnLivre());
//          tata.get(2).UpdateLivre();
//                    
//          System.out.println(tata.get(2));
//          System.out.println("---------Arraylist---------");
//          for(int i = 0; i < tata.size(); i++){
//              System.out.println(tata.get(i).toString());
//          }
//        
//          
//        ArrayList<Editeur> toto = Editeur.AffichageEditeur();
//        
//        System.out.println(toto.get(2));
//        toto.get(2).setStatutEditeur("MERDE");
//        toto.get(2).setLogoEditeur("MERDE et bien plus");
//        System.out.println(toto.get(2));
//        toto.get(2).UpdateEditeur();
//        System.out.println(toto.get(2));
        
        
    }
    
    
    
    
    
}
