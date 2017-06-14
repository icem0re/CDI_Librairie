/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administration;


import java.sql.Date;
import java.time.LocalDate;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi415
 */
public class test {
    public static void main(String[] args) {
        
//        Vector v = null;
//        try {
//            v = Employe.getVectorAllEmploye();
//        } catch (ClassNotFoundException ex) {
//            System.err.println(ex.getMessage());
//        }
//        
//        for (Object empl : v){
//            Employe employeEnCours = (Employe) empl;
//            
//            System.out.println(employeEnCours);
//            employeEnCours.save();
//            
//        }
        
//        Employe m = (Employe) v.get(2);
//        
//        m.setLoginEmploye("newLogin");
//        m.save();
        
//        
//        Employe charlie = new Employe();
//        
//        charlie.setNomEmploye("Charlie");
//        charlie.setPrenomEmploye("Alfred");
//        try {
//            charlie.setEmailEmploye("a2f@outlook.com");
//        } catch (InvalidEmailException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        charlie.setLoginEmploye("a.charlie3");
//        try {
//            charlie.setMdpEmploye("myPaSw0Rd");
//            charlie.encodeMdpEmploye();
//        } catch (InvalidePasswordException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        charlie.setDebutPosteEmploye(LocalDate.of(2017, 02, 21));
//        try {
//            //charlie.setFinPosteEmploye(LocalDate.of(2017, 05, 03));
//            charlie.save();
//        } catch (DuplicateConstraintSQLException ex) {
//            ex.printStackTrace();
//        }
//        
        
//        
//        for (Employe empl : Employe.getArrayListAllEmploye()){
//            System.out.println(empl);
//        }
        
        
        Statut monStat = new StatutClient();
        monStat.setCode("123456");
        System.out.println(((StatutClient)monStat).getCode());
        monStat.save();
        
        
    }
            
}
