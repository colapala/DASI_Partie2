/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Voyance;
import fr.insalyon.dasi.td.jpa.modele.Employe;
import fr.insalyon.dasi.td.jpa.service.Service;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cjourdan
 */
public class AfficherProchainClientAction extends Action{
     @Override
     
    public boolean executer(HttpServletRequest request){
           boolean execute=true; 
           System.out.println("action prochain client en cours");
           try{
           HttpSession session=request.getSession(true);
           Employe emp=(Employe) session.getAttribute("utilisateur");
           Service service= new Service();
           Client c = service.getClientEnAttente(emp);
           Voyance v=getVoyanceEnAttente(c,emp);
           
           if(c!=null){
                request.setAttribute("civilite", c.getCivilite());
                request.setAttribute("nom", c.getNom());
                request.setAttribute("prenom", c.getPrenom());
                request.setAttribute("medium", v.getMedium().getNom());
           } else {
               request.setAttribute("civilite", "pas de client pour le moment");
               request.setAttribute("nom", "pas de client pour le moment");
               request.setAttribute("prenom", "pas de client pour le moment");
               request.setAttribute("medium", "pas de client pour le moment");
           }
           
           }catch (Exception e){
               execute=false;
           } 
           return execute;
    }
    
}
