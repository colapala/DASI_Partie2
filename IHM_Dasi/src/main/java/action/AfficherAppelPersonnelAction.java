/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Employe;
 import fr.insalyon.dasi.td.jpa.modele.Medium;
import fr.insalyon.dasi.td.jpa.service.Service;
 
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Arrays;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author cjourdan
 */
public class AfficherAppelPersonnelAction extends Action {
    
     @Override
     
   public boolean executer(HttpServletRequest request){
           boolean execute=true; 
           System.out.println("action top appels personnel en cours");
           try{
           HttpSession session=request.getSession(true);
           Employe emp=(Employe) session.getAttribute("utilisateur");
           Service service= new Service();
           
           Map<Medium,Integer> top=service.getClassementMediumPourEmploye(emp);
		
           request.setAttribute("map", top);

           }catch (Exception e){
               execute=false;
           } 
           return execute;
    }
    
}
