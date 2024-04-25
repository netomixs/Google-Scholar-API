package models;

import com.google.gson.Gson;
import controllers.API;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Autor {
        private  String Id;
    private String Name;
    private String Affiliations;
    private String Email;

    public Autor(String id, String name, String affiliations, String email) {
        Id = id;
        Name = name;
        Affiliations = affiliations;
        Email = email;
    }
    public  Autor(){

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAffiliations() {
        return Affiliations;
    }

    public void setAffiliations(String affiliations) {
        Affiliations = affiliations;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void fromJsonToAutor(Map map){
    this.Name=(String) map.get("name");
    this.Id= (String) map.get("author_id");
    this.Affiliations=(String) map.get("affiliations");
    this.Email=(String) map.get("email");
    }
}
