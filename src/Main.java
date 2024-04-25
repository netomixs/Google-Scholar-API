import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.*;
import controllers.AutorController;
import models.Autor;
import view.AutorView;

public class Main {
    public static void main(String[] args) {
        AutorController c=new AutorController();
        AutorView v=new AutorView();
        ;
v.imprimirLista(c.creatListAutors());

    }
}