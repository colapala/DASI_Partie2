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
import fr.insalyon.dasi.td.jpa.modele.Astrologue;
import fr.insalyon.dasi.td.jpa.modele.Medium;
import fr.insalyon.dasi.td.jpa.modele.Tarologue;
import fr.insalyon.dasi.td.jpa.modele.Voyance;
import fr.insalyon.dasi.td.jpa.modele.Voyant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author colap
 */
public class AfficherMediumDispoSerialisation extends Serialisation {
      @Override 
       
      public void serialiser(HttpServletRequest request, HttpServletResponse response)throws IOException{
        JsonObject container=new JsonObject();
        
        List<Medium> listeMedium= (List)request.getAttribute("listeMedium");

        JsonArray jsonListe = new JsonArray();
            
        for (Medium m :listeMedium) {
            JsonObject jsonVoyance=new JsonObject();
            jsonVoyance.addProperty("nom",m.getNom());
            jsonVoyance.addProperty("descriptif",m.getDescriptif());
            
            if(m instanceof Astrologue){
                Astrologue astrologue=(Astrologue)m;
                jsonVoyance.addProperty("talent","Astrologue");
                jsonVoyance.addProperty("formation",astrologue.getFormation());
                jsonVoyance.addProperty("promotion",astrologue.getPromotion());
            }else if(m instanceof Tarologue){
                jsonVoyance.addProperty("talent","Tarologue");
            }else{
                jsonVoyance.addProperty("talent","Voyant");
                Voyant voyant=(Voyant)m;
                jsonVoyance.addProperty("specialite",voyant.getSpecialite());
            }
            jsonListe.add(jsonVoyance);
        }
        container.add("listeMedium",jsonListe);
      
            
        PrintWriter out=this.getWriterWithJsonHeader(response);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson (container);
        out.println(json);
        System.out.println(json);
    }
}
