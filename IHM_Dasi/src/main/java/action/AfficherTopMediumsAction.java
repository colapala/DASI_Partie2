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
import java.util.ArrayList;
 
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
public class AfficherTopMediumsAction extends Action {
    
     @Override
     
    public boolean executer(HttpServletRequest request){
           boolean execute=true; 
           System.out.println("action top medium en cours");
           try{
           HttpSession session=request.getSession(true);
           Employe emp=(Employe) session.getAttribute("utilisateur");
           Service service= new Service();
           
           Map<Medium,Integer> top=service.getClassementMediumPourEmploye(emp);
           
           ArrayList<Medium> Mediums=new ArrayList();

           for(Map.Entry<Medium, Integer> entry : top.entrySet()) {
                 Mediums.add(entry.getKey());
           }
            
          String nbMedium; 
          for (int i=0;i<3;i++){
             nbMedium =("medium"+(i+1)).toString();
              if(i<Mediums.size()){
                Medium medium = Mediums.get(i);
                System.out.println(nbMedium);
                request.setAttribute(nbMedium, medium.getNom());
              }else{
                 request.setAttribute(nbMedium, "pas d'autre médium"); 
              }
          }
           
           }catch (Exception e){
               execute=false;
           } 
           return execute;
    }
    
}
