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
public class AfficherTopMediumsSerialisation extends Serialisation {
    @Override 
      
    public void serialiser(HttpServletRequest request, HttpServletResponse response)throws IOException{
        JsonObject container=new JsonObject();
        
        String medium1= (String)request.getAttribute("medium1");
        String medium2= (String)request.getAttribute("medium2");
        String medium3= (String)request.getAttribute("medium3");

        container.addProperty("medium1",medium1);
        container.addProperty("medium2",medium2);
        container.addProperty("medium3",medium3);
            
        PrintWriter out=this.getWriterWithJsonHeader(response);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson (container);
        out.println(json);
        System.out.println(json);
    }
    
}
