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
        params.put("mauthors", "UNAM");
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

    public void actulizar(){
        List<Autor> lista= obtenerAutores();
        if(lista.size()==0){
            lista=creatListAutors();
            for (var i:lista
                 ) {
                i.insertar();
            }
        }else {
            for (var i:lista
                 ) {
                    i.eliminar();
            }
            lista=creatListAutors();
            for (var i:lista
                 ) {
                i.insertar();
            }
        }
    }
    public List<Autor> obtenerAutores(){
        List<Autor> autores=new ArrayList<>();
        autores=Autor.obtenerTodos();
        return  autores;
    }



}
