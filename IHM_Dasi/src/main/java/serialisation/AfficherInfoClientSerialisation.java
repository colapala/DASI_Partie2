/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cjourdan
 */
public class AfficherInfoClientSerialisation extends Serialisation {
      @Override 
       
      public void serialiser(HttpServletRequest request, HttpServletResponse response)throws IOException{
        JsonObject container=new JsonObject();
        
        String nom= (String)request.getAttribute("nom");
        String prenom= (String)request.getAttribute("prenom");
        String signeZodiac= (String)request.getAttribute("signeZodiac");
        String signeChinois=(String)request.getAttribute("signeChinois");
        String couleur= (String)request.getAttribute("couleur");
        String animal= (String)request.getAttribute("animal");
        Date dateNaissance= (Date)request.getAttribute("dateNaissance");
        String mail= (String)request.getAttribute("mail");
        String adresse= (String)request.getAttribute("adresse");
        String tel= (String)request.getAttribute("tel");
        
        container.addProperty("nom",nom);
        container.addProperty("prenom",prenom);
        container.addProperty("signeZodiac",signeZodiac);
        container.addProperty("signeChinois",signeChinois);
        container.addProperty("couleur",couleur);
        container.addProperty("animal",animal);
        
        //transformation de la date en string
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date= sdf.format(dateNaissance);
        container.addProperty("dateNaissance",date);
        
        container.addProperty("mail",mail);
        container.addProperty("adresse",adresse);
        container.addProperty("tel",tel);
        
            
        PrintWriter out=this.getWriterWithJsonHeader(response);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson (container);
        out.println(json);
        System.out.println(json);
    }
}
