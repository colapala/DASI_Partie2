/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import fr.insalyon.dasi.td.jpa.modele.Client;
import fr.insalyon.dasi.td.jpa.modele.Medium;
import fr.insalyon.dasi.td.jpa.modele.Voyance;
import fr.insalyon.dasi.td.jpa.service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author colap
 */
public class AfficherMediumDispoAction extends Action {
     @Override
    public boolean executer(HttpServletRequest request){
           boolean execute=true; 
           System.out.println("action en cours");
           String type=request.getParameter("type");
           
           try{
           Service service=new Service();
           
           switch (type){
               case "Tous":
               {
                    List<Medium> listeMedium=service.getTousMediums();
                    request.setAttribute("listeMedium",listeMedium);
                    break;
               }
               case "Voyant":
               {
                    List<Medium> listeMedium=service.getTousVoyants();
                    request.setAttribute("listeMedium",listeMedium);
                    break;
               }
               case "Astrologue":
               {
                    List<Medium> listeMedium=service.getTousAstrologues();
                    request.setAttribute("listeMedium",listeMedium);
                    break;
               }
               case "Tarologue":
               {
                    List<Medium> listeMedium=service.getTousTarologues();
                    request.setAttribute("listeMedium",listeMedium);
                    break;
               }
           }
           
           }catch (Exception e){
               execute=false;
           } 
           return execute;
    }
    
    
}