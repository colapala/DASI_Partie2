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
import java.util.Date;
import fr.insalyon.dasi.td.jpa.modele.Medium;
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
        
        Medium[] listeMediumPerso= (Medium[])request.getAttribute("listeMediumPerso");
        int[] listeAppelPerso= (int[])request.getAttribute("listeAppelPerso");

        container.addProperty("listeMediumPerso",listeMediumPerso);
        container.addProperty("listeAppelPerso",listeAppelPerso);
            
        PrintWriter out=this.getWriterWithJsonHeader(response);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson (container);
        out.println(json);
        System.out.println(json);
    }
    
}
