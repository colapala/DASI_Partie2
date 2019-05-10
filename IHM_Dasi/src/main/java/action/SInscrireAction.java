/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Employe;
import fr.insalyon.dasi.td.jpa.service.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cjourdan
 */
public class SInscrireAction extends Action {
      @Override
    public boolean executer(HttpServletRequest request){
           boolean execute=true; 
           HttpSession session=request.getSession(true);
           String nom=request.getParameter("nom");
           String prenom=request.getParameter("prenom");
           String civilite=request.getParameter("civilite");
           String date=request.getParameter("date");
           String adresse=request.getParameter("adresse");
           String mail=request.getParameter("mail");
           String tel=request.getParameter("tel");
           String password=request.getParameter("password");
           String confirm=request.getParameter("confirm");
           
           System.out.println("action en cours d'execution");
           
           try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateNaissance = sdf.parse(date);
            
            Service service= new Service();
            Client c=new Client(nom,prenom,civilite,dateNaissance,adresse,tel, mail,password);
            c=service.inscrireClient(c);
            session.setAttribute("utilisateur",c);
            request.setAttribute("reussie", true);
           
           }catch (Exception e){
               execute=false;
           }
           return execute;
    }
}
