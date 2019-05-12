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
public class AfficherTopEmployeAction extends Action {
    
     @Override
     
    public boolean executer(HttpServletRequest request){
           boolean execute=true; 
           System.out.println("action top employe en cours");
           try{
           HttpSession session=request.getSession(true);
           Service service= new Service();
           
           Employe emp1=null;
           Employe emp2=null;
           Employe emp3=null;
           
           Map<Medium,Integer> top=service.getClassementEmploye();
           
           emp1 = top.keySet().toArray()[0];
           emp2 = top.keySet().toArray()[1];
           emp3 = top.keySet().toArray()[2];

           if(emp1!=null){
                request.setAttribute("employe1", emp1.getNom());
           } else {
               request.setAttribute("employe1", "pas d'autre employe");
           }
           if(emp2!=null){
                request.setAttribute("employe1", emp2.getNom());
           } else {
               request.setAttribute("employe1", "pas d'autre employe");
           }
           if(emp3!=null){
                request.setAttribute("meemploye1dium3", emp3.getNom());
           } else {
               request.setAttribute("employe1", "pas d'autre employe");
           }
           
           }catch (Exception e){
               execute=false;
           } 
           return execute;
    }
    
}
