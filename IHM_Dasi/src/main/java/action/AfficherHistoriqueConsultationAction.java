/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Voyance;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author colap
 */
public class AfficherHistoriqueConsultationAction extends Action {
     @Override
     
    public boolean executer(HttpServletRequest request){
           boolean execute=true; 
           System.out.println("action en cours");
           
           try{
           HttpSession session=request.getSession(true);
           Client c=(Client) session.getAttribute("utilisateur");
           
           List<Voyance> listeVoyance=c.getHistorique();
           request.setAttribute("listeVoyance",listeVoyance);
           
           //revoir la mise à jour de l'historique quand on demande une voyance ! pour que celle en cours soit affichée ici ou pas ?
           
           }catch (Exception e){
               execute=false;
           } 
           return execute;
    }
    
    
}
