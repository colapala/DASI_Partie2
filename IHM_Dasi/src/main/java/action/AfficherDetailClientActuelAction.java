/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Voyance;
import fr.insalyon.dasi.td.jpa.modele.Employe;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author colap
 */
public class AfficherDetailClientActuelAction extends Action {
     @Override
    public boolean executer(HttpServletRequest request){
           boolean execute=true; 
           System.out.println("action détail client actuel en cours");
           
           try{
			   
           HttpSession session=request.getSession(true);
           Employe emp=(Employe) session.getAttribute("utilisateur");
           Service service= new Service();
		   Client c=service.getClientEnAttente(emp);
		   
           request.setAttribute("civilite", c.getCivilite());
           request.setAttribute("nom", c.getNom());
           request.setAttribute("prenom", c.getPrenom());
           request.setAttribute("signeZodiac", c.getSigneZodiac());
           request.setAttribute("signeChinois", c.getSigneChinois());
           request.setAttribute("couleur", c.getCouleur());
           request.setAttribute("animal", c.getAnimal());
           request.setAttribute("dateNaissance", c.getDateNaissance());
           request.setAttribute("mail", c.getMail());
           request.setAttribute("adresse", c.getAdresse());
           request.setAttribute("tel", c.getTel());
           
           }catch (Exception e){
               execute=false;
           } 
           return execute;
    }
    
    
}
