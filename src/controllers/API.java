package controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class API {
    private String URL ="https://serpapi.com/search?";
    private String API_KEY="aaf05056c96a086eef990e0b9b89e9a7eb45826ed14330d72006ef27a408aa33&q=biology";

    //Ernesto yo te quiero mucho eres mi mejor amigo :)
    public String get(Map<String,String> params){
        try{
            HttpClient client = HttpClient.newHttpClient();
            String paramsStr="";
            for (Map.Entry<String,String> item:params.entrySet()) {
                paramsStr+= item.getKey()+"="+item.getValue()+"&";
            }
            String uri=URL
                    +paramsStr+
                    "api_key=aaf05056c96a086eef990e0b9b89e9a7eb45826ed14330d72006ef27a408aa33";
            System.out.println(uri);
            HttpRequest request = HttpRequest.newBuilder() .uri(URI.create(uri))
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            return  response.body();
        }catch (Exception e){
                return  e.toString();

        }

    }

}
