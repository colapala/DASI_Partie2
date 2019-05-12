/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import action.Action;
import action.AfficherHistoClientAction;
import action.AfficherInfoClientAction;
import action.AfficherMediumDispoAction;
import action.AfficherProchainClientAction;
import action.AfficherTopMediumsAction;
import action.AfficherTopEmployeAction;
import action.AfficherAppelEntrepriseAction;
import action.AfficherAppelPersonnelAction;
import action.AfficherPredictionsAction;
import action.AfficherDetailClientActuelAction;
import action.SInscrireAction;
import action.AfficherHistoriqueConsultationAction;
import action.SeConnecterAction;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import serialisation.AfficherHistoClientSerialisation;
import serialisation.AfficherInfoClientSerialisation;
import serialisation.AfficherMediumDispoSerialisation;
import serialisation.AfficherProchainClientSerialisation;
import serialisation.AfficherTopMediumsSerialisation;
import serialisation.SInscrireSerialisation;
import serialisation.SeConnecterSerialisation;
import serialisation.AfficherDetailClientActuelSerialisation;                        
import serialisation.AfficherTopEmployeSerialisation;
import serialisation.AfficherHistoriqueConsultationSerialisation;
import serialisation.AfficherAppelEntrepriseSerialisation;
import serialisation.AfficherAppelPersonnelSerialisation;
import serialisation.Serialisation;
import serialisation.AfficherPredictionsSerialisation;

/**
 *
 * @author cjourdan
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           response.setCharacterEncoding("UTF-8");
           String todo=request.getParameter("todo");
           
           switch (todo){
                   case "connecter":
                   {
                    Action action=new SeConnecterAction();
                    Serialisation serialisation=new SeConnecterSerialisation();

                    boolean actionStatus=action.executer(request);
                    System.out.println("action réussie: "+actionStatus);
                    if (actionStatus){
                        serialisation.serialiser(request, response);
                        System.out.println("serialisation en cours");
                    }else{
                        response.sendError(400,"connexion échouée");
                    }
                    break;
                   }
                    
                   case "inscrire":
                      {
                       Action action=new SInscrireAction();
                        Serialisation serialisation=new SInscrireSerialisation();

                        boolean actionStatus=action.executer(request);
                        System.out.println("action réussie: "+actionStatus);
                        if (actionStatus){
                            serialisation.serialiser(request, response);
                            System.out.println("serialisation en cours");
                        }else{
                            response.sendError(401,"inscription échouée");
                        }
                        break;
                      }
                    
                   case "afficher_info_client":
                       {
                        System.out.println("afficher info ...");
                        Action action=new AfficherInfoClientAction();
                        Serialisation serialisation=new AfficherInfoClientSerialisation();

                        boolean actionStatus=action.executer(request);
                        System.out.println("action réussie: "+actionStatus);
                        if (actionStatus){
                            serialisation.serialiser(request, response);
                            System.out.println("serialisation en cours");
                        }else{
                            response.sendError(402,"erreur survenue");
                        }
                        break;
                      }
                       
                       case "afficher_historique":
                       {
                        System.out.println("afficher historique ...");
                        Action action=new AfficherHistoClientAction();
                        Serialisation serialisation=new AfficherHistoClientSerialisation();

                        boolean actionStatus=action.executer(request);
                        System.out.println("action réussie: "+actionStatus);
                        if (actionStatus){
                            serialisation.serialiser(request, response);
                            System.out.println("serialisation en cours");
                        }else{
                            response.sendError(403,"erreur survenue");
                        }
                        break;
                      }
                       
                     case "afficher_mediums_disponibles":
                       {
                        System.out.println("afficher medium disponibles ...");
                        Action action=new AfficherMediumDispoAction();
                        Serialisation serialisation=new AfficherMediumDispoSerialisation();

                        boolean actionStatus=action.executer(request);
                        System.out.println("action réussie: "+actionStatus);
                        if (actionStatus){
                            serialisation.serialiser(request, response);
                            System.out.println("serialisation en cours");
                        }else{
                            response.sendError(404,"erreur survenue");
                        }
                        break;
                      }
                       
                       case "afficher_prochain_client":
                       {
                        System.out.println("afficher le prochain client ...");
                        Action action=new AfficherProchainClientAction();
                        Serialisation serialisation=new AfficherProchainClientSerialisation();

                        boolean actionStatus=action.executer(request);
                        System.out.println("action réussie: "+actionStatus);
                        if (actionStatus){
                            serialisation.serialiser(request, response);
                            System.out.println("serialisation en cours");
                        }else{
                            response.sendError(405,"erreur survenue");
                        }
                        break;
                      }
                       
                       case "afficher_top_medium":
                       {
                        System.out.println("afficher le top 3 des médiums ...");
                        Action action=new AfficherTopMediumsAction();
                        Serialisation serialisation=new AfficherTopMediumsSerialisation();

                        boolean actionStatus=action.executer(request);
                        System.out.println("action réussie: "+actionStatus);
                        if (actionStatus){
                            serialisation.serialiser(request, response);
                            System.out.println("serialisation en cours");
                        }else{
                            response.sendError(406,"erreur survenue");
                        }
                        break;
                      }
                      
                      case "afficher_top_employes":
                       {
                        System.out.println("afficher le top 3 des employes ...");
                        Action action=new AfficherTopEmployeAction();
                        Serialisation serialisation=new AfficherTopEmployeSerialisation();

                        boolean actionStatus=action.executer(request);
                        System.out.println("action réussie: "+actionStatus);
                        if (actionStatus){
                            serialisation.serialiser(request, response);
                            System.out.println("serialisation en cours");
                        }else{
                            response.sendError(407,"erreur survenue");
                        }
                        break;
                      }
                      
                      case "afficher_appel_entreprise":
                       {
                        System.out.println("afficher les stats d'appel entreprise ...");
                        Action action=new AfficherAppelEntrepriseAction();
                        Serialisation serialisation=new AfficherAppelEntrepriseSerialisation();

                        boolean actionStatus=action.executer(request);
                        System.out.println("action réussie: "+actionStatus);
                        if (actionStatus){
                            serialisation.serialiser(request, response);
                            System.out.println("serialisation en cours");
                        }else{
                            response.sendError(408,"erreur survenue");
                        }
                        break;
                      }
                      
                      case "afficher_appel_personnel":
                       {
                        System.out.println("afficher les stats d'appel personnel ...");
                        Action action=new AfficherAppelPersonnelAction();
                        Serialisation serialisation=new AfficherAppelPersonnelSerialisation();

                        boolean actionStatus=action.executer(request);
                        System.out.println("action réussie: "+actionStatus);
                        if (actionStatus){
                            serialisation.serialiser(request, response);
                            System.out.println("serialisation en cours");
                        }else{
                            response.sendError(409,"erreur survenue");
                        }
                        break;
                      }
                      
                      case "afficher_info_client_actuel":
                       {
                        System.out.println("afficher info du client actuel ...");
                        Action action=new AfficherDetailClientActuelAction();
                        Serialisation serialisation=new AfficherDetailClientActuelSerialisation();

                        boolean actionStatus=action.executer(request);
                        System.out.println("action réussie: "+actionStatus);
                        if (actionStatus){
                            serialisation.serialiser(request, response);
                            System.out.println("serialisation en cours");
                        }else{
                            response.sendError(410,"erreur survenue");
                        }
                        break;
                      }
                      
                      case "afficher_historique_client_actuel":
                       {
                        System.out.println("afficher historique du client actuel ...");
                        Action action=new AfficherHistoriqueConsultationAction();
                        Serialisation serialisation=new AfficherHistoriqueConsultationSerialisation();

                        boolean actionStatus=action.executer(request);
                        System.out.println("action réussie: "+actionStatus);
                        if (actionStatus){
                            serialisation.serialiser(request, response);
                            System.out.println("serialisation en cours");
                        }else{
                            response.sendError(411,"erreur survenue");
                        }
                        break;
                      }
                      
                      case "afficher_predictions":
                       {
                        System.out.println("afficherles prédictions ...");
                        Action action=new AfficherPredictionsAction();
                        Serialisation serialisation=new AfficherPredictionsSerialisation();

                        boolean actionStatus=action.executer(request);
                        System.out.println("action réussie: "+actionStatus);
                        if (actionStatus){
                            serialisation.serialiser(request, response);
                            System.out.println("serialisation en cours");
                        }else{
                            response.sendError(411,"erreur survenue");
                        }
                        break;
                      }
           }

     
    }
            
           


// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    

}
