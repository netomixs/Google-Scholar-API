package controllers;

import models.Autor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.*;

public class AutorController {


    public List<Autor> creatListAutors() {
        API api=new API();
        Map<String, String> params = new HashMap<String, String>();
        params.put("engine", "google_scholar_profiles");
        params.put("mauthors", "Tecnologico_Nacional_de_Mexico_Campus_Veracruz");
        params.put("num", "10");
        String response=api.get(params);
        Gson gson = new Gson();
        Map map = gson.fromJson(response, Map.class);
         var listaProfiles = (ArrayList) map.get("profiles");
        List<Autor> autores=new ArrayList<>();
        for (int i = 0; i < listaProfiles.size(); i++) {

            Autor autor=new Autor();
            autor.fromJsonToAutor((Map) listaProfiles.get(i));
            autores.add(autor);
        }
return autores;
    }


}
