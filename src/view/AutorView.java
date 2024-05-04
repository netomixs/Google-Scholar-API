package view;

import models.Autor;

import java.util.List;

public class AutorView {

    public  void imprimir(Autor autor){
        System.out.println("ID: "+autor.getId());
        System.out.println("Nombre: "+autor.getName());

        System.out.println("Correo: "+autor.getEmail());

        System.out.println("Afiliacion: "+autor.getAffiliations());
        System.out.println("Citaciones: "+autor.getCitas());

    }
    public void imprimirLista(List<Autor> lista){
        for (Autor autor: lista
             ) {
            imprimir(autor);

        }
    }
}
