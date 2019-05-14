/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Employe;
import fr.insalyon.dasi.td.jpa.modele.Medium;
import fr.insalyon.dasi.td.jpa.modele.Voyance;
import fr.insalyon.dasi.td.jpa.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author colap
 */
public class DemanderVoyanceAction extends Action {
     @Override
     
    public boolean executer(HttpServletRequest request){
           boolean execute=true; 
           System.out.println("action demander voyance en cours");
           try{
           HttpSession session=request.getSession(true);
           Client c=(Client) session.getAttribute("utilisateur");
           String nom=request.getParameter("nom");
           System.out.println(nom);
           
           Service service=new Service();
           Medium m=service.findMediumParNom(nom);
           service.demanderVoyance(c, m);
           
           request.setAttribute("resultat","reussie");
           
           }catch (Exception e){
               execute=false;
           } 
           return execute;
    }
    
    
}
