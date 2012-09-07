package net.masterthought.freebase;

import com.freebase.api.Freebase;
import com.freebase.json.JSON;
import org.json.simple.parser.ParseException;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.IOException;

import static com.freebase.json.JSON.a;
import static com.freebase.json.JSON.o;

public class FreebaseUtil {

    public static final String searchUrl = "http://api.freebase.com/api/service/search?query=";
    public static Freebase freebase = Freebase.getFreebase();

    public static JSON search(String searchString) throws IOException, ParseException {
        Representation representation = new ClientResource(searchUrl + searchString).get();
        return JSON.parse(representation.getText());
    }

    public static JSON get(String type, String name){
        JSON query = o(
              "id", null,
              "type", type,
              "name", name,
              "directed_by", a(o(
                "id", null,
                "name", null
              ))
            );
        return freebase.mqlread(query).get("result");
    }

    public static JSON get(JSON query){
       try{
           return freebase.mqlread(query).get("result");
       } catch(com.freebase.api.FreebaseException fe){
           System.out.println(query);
       }
       return null;
    }

}
