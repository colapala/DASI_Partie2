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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cjourdan
 */
public class AfficherProchainClientSerialisation extends Serialisation{
    
    @Override 
      
      public void serialiser(HttpServletRequest request, HttpServletResponse response)throws IOException{
        JsonObject container=new JsonObject();
        
        String nom= (String)request.getAttribute("nom");
        String prenom= (String)request.getAttribute("prenom");
        String civilite= (String)request.getAttribute("civilite");

        container.addProperty("nom",nom);
        container.addProperty("prenom",prenom);
        container.addProperty("civilite",civilite);
            
        PrintWriter out=this.getWriterWithJsonHeader(response);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson (container);
        out.println(json);
        System.out.println(json);
    }
}
