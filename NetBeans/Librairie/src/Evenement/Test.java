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

import bookStore.Livre;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdi415
 */
public class Test {
    
    public static void main(String[] args) {
       
            
        try {
            int id = 5;
//            Evenement eve = new Evenement(id);
            Evenement eve = new Evenement(1);

            
            System.out.println(eve);
//            
//            for (Livre liv : eve.getLivres()){
//                System.out.println("---------------------------------");
//                System.out.println(liv);
//            }
            
            eve.saveBookList();
            
//            
//            eve.save();
//            
//            for (Evenement eve1 : Evenement.getArrayListAllEvenement()){
//                System.out.println(eve1);
//            }
//            
//            
//            for (Evenement eve2 : Evenement.livreHasEvents("2253111465")){
//                System.out.println(eve2);
//            }
//            
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        
        
    }
    
}
