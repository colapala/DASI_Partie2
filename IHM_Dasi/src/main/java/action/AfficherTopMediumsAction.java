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
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author cjourdan
 */
public class AfficherTopMediumsAction extends Action {
    
     @Override
     
    public boolean executer(HttpServletRequest request){
           boolean execute=true; 
           System.out.println("action top medium en cours");
           try{
           HttpSession session=request.getSession(true);
           Employe emp=(Employe) session.getAttribute("utilisateur");
           Service service= new Service();
           
           Medium medium1=null;
           Medium medium2=null;
           Medium medium3=null;
           
           Map<Medium,Integer> top=service.getClassementMediumPourEmploye(emp);
           for (Map.Entry mapentry : top.entrySet()) {
               medium1=(Medium) mapentry.getKey();
            }

           if(medium1!=null){
                request.setAttribute("medium1", medium1.getNom());
           } else {
               request.setAttribute("medium1", "pas d'autre médium");
           }
           if(medium2!=null){
                request.setAttribute("medium2", medium2.getNom());
           } else {
               request.setAttribute("medium2", "pas d'autre médium");
           }
           if(medium3!=null){
                request.setAttribute("medium3", medium3.getNom());
           } else {
               request.setAttribute("medium3", "pas d'autre médium");
           }
           
           }catch (Exception e){
               execute=false;
           } 
           return execute;
    }
    
}
