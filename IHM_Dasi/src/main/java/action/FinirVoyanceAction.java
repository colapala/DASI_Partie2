/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Employe;
import fr.insalyon.dasi.td.jpa.modele.Voyance;
import fr.insalyon.dasi.td.jpa.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author colap
 */
public class FinirVoyanceAction extends Action {
     @Override
     
    public boolean executer(HttpServletRequest request){
           boolean execute=true; 
           System.out.println("action fin voyance en cours");
           try{
           HttpSession session=request.getSession(true);
           Employe emp=(Employe) session.getAttribute("utilisateur");
           String commentaire=request.getParameter("commentaire");
           
           Service service=new Service();
           Client c=service.getClientEnAttente(emp);
           Voyance v=service.getVoyanceEnAttente(c,emp);
           v.setDescription(commentaire);
           service.finirVoyance(v);
           
           request.setAttribute("resultat","reussie");
           
           }catch (Exception e){
               execute=false;
           } 
           return execute;
    }
    
    
}
