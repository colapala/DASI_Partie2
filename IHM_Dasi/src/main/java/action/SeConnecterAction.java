package action;


import action.Action;
import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Employe;
import fr.insalyon.dasi.td.jpa.service.Service;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cjourdan
 */
public class SeConnecterAction extends Action{
    @Override
    public boolean executer(HttpServletRequest request){
           boolean execute=true; 
           HttpSession session=request.getSession(true);
           String login=request.getParameter("login");
           String password=request.getParameter("password");
           
           System.out.println("action en cours d'execution");
           
           Service service= new Service();
           try{
           Client c=service.connecterClient(login,password);
           System.out.println("Client : "+c.getNom());
            session.setAttribute("utilisateur",c);
            request.setAttribute("utilisateur", "client");
            
           }catch( Exception ex){
               try{
                Employe e=service.connecterEmploye(login,password);
                System.out.println("Employe !!!!!");
                session.setAttribute("utilisateur",e);
                request.setAttribute("utilisateur", "employe");
                
                }catch(Exception ex2){
                 execute=false;
                } 
           }
           
           return execute;
    }
}
