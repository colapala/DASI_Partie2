/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Employe;
import fr.insalyon.dasi.td.jpa.service.Service;
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
           String type=(String)request.getParameter("type");
           System.out.println(type);
           
           Service service= new Service();
           Client c=service.getClientEnAttente(emp);
           
           if(c!=null){
                List<String> predictions=null;
                switch(type){
                    case "Mauvais":
                        predictions=service.getPredictions(c,0);
                        break;
                    case "Moyen":
                        predictions=service.getPredictions(c,1);
                        break;
                    case "Bon":
                        predictions=service.getPredictions(c,2);
                        break;
                    case "Parfait":
                        predictions=service.getPredictions(c,3);
                        break;

                }  
                request.setAttribute("amour",predictions.get(0));
                request.setAttribute("sante",predictions.get(1));
                request.setAttribute("travail",predictions.get(2));
           }
           
           }catch (Exception e){
               execute=false;
           } 
           return execute;
    }
    
    
}
