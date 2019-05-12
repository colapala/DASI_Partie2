/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Voyance;
import fr.insalyon.dasi.td.jpa.modele.Employe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author colap
 */
public class AfficherPredictionsAction extends Action {
     @Override
     
    public boolean executer(HttpServletRequest request){
           boolean execute=true; 
           System.out.println("action afficher prediction en cours");
           try{
           HttpSession session=request.getSession(true);
           Employe emp=(Employe) session.getAttribute("utilisateur");
           Client c=service.getClientEnAttente(emp);
           Service service= new Service();
		   
		   int niveau=0;
		   List<String>  predictions = ​getPredictions​(c, niveau);
		  
		   request.setAttribute("amour",prediction.get(0));
		   request.setAttribute("sante",prediction.get(1));
		   request.setAttribute("travail",prediction.get(2));

           
           }catch (Exception e){
               execute=false;
           } 
           return execute;
    }
    
    
}
