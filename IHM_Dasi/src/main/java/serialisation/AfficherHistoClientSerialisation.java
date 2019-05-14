/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.td.jpa.modele.Voyance;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author colap
 */
public class AfficherHistoClientSerialisation extends Serialisation {
      @Override 
       
      public void serialiser(HttpServletRequest request, HttpServletResponse response)throws IOException{
        JsonObject container=new JsonObject();
        
        List<Voyance> listeVoyance= (List)request.getAttribute("listeVoyance");

        JsonArray jsonListe = new JsonArray();
            
        for (Voyance v :listeVoyance) {
            JsonObject jsonVoyance=new JsonObject();
            
            //transformation de la date en string
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date= sdf.format(v.getDateVoyance());

            jsonVoyance.addProperty("date",date);
            jsonVoyance.addProperty("medium",v.getMedium().getNom());
            jsonVoyance.addProperty("status",v.getStatus());
            jsonListe.add(jsonVoyance);
        }
        container.add("listeVoyance",jsonListe);
      
            
        PrintWriter out=this.getWriterWithJsonHeader(response);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson (container);
        out.println(json);
        System.out.println(json);
    }
}
