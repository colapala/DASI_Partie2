
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
           
           Service service= new Service();
           Client c=service.connecterClient(login,password);
           if( c!= null){
            session.setAttribute("utilisateur",c);
           }else {
             Employe e=service.connecterEmploye(login,password);
             if( e!= null){
                session.setAttribute("utilisateur",e);
             }else{
                 execute=false;
             } 
           }
           
           request.setAttribute("utilisateur", client);
           return execute;
    }
}
