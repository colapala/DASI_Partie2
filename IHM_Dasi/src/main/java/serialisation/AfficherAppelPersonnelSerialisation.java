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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import fr.insalyon.dasi.td.jpa.modele.Medium;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cjourdan
 */
public class AfficherAppelPersonnelSerialisation extends Serialisation {
    @Override 
      
    public void serialiser(HttpServletRequest request, HttpServletResponse response)throws IOException{
        JsonObject container=new JsonObject();
         Map<Medium,Integer> top=(Map<Medium,Integer>)request.getAttribute("map");
        
        JsonArray jsonListe = new JsonArray();
        for(Map.Entry<Medium, Integer> entry : top.entrySet()) {
                 Medium cle = entry.getKey();
                 Integer valeur = entry.getValue();
                 // traitements
           
            JsonObject jsonMedium=new JsonObject();
            jsonMedium.addProperty("medium",cle.getNom());
            jsonMedium.addProperty("nbAppel",valeur);
            
        jsonListe.add(jsonMedium);
        }
        container.add("listeAppel",jsonListe); 
            
        PrintWriter out=this.getWriterWithJsonHeader(response);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson (container);
        out.println(json);
        System.out.println(json);
    }
    
}
