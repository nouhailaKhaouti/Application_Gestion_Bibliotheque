package org.example.Controller;

import org.example.Model.Collection;
import org.example.Model.Livre;
import org.example.Service.CollectionService;
import org.example.Service.LivreService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LivreController {
    LivreService livreService=new LivreService();
    CollectionService collectionService=new CollectionService();


    public String save()throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the Isbn of the livre");
        String isbn= scanner.next();
        Collection collectionN=collectionService.findByIsbn(isbn);
        if(collectionN==null){
            System.out.print("Enter Title: ");
            String title = scanner.next();
            System.out.print("Enter Author: ");
            String author = scanner.next();
            System.out.print("Enter Total Number of Books: ");
            int totalBooks = scanner.nextInt();
            System.out.println("Provided ISBN: " + isbn);
            Collection collection=new Collection(isbn,title,totalBooks,author);
            collectionN=collection;
            collectionService.save(collectionN);
        }
        System.out.print("Enter the numero Inventair");
        String numero=scanner.next();
        Livre livre=new Livre(numero,collectionN);

        return livreService.save(livre);
    }

    public String Delete()throws SQLException{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the Numero d' inventair of the livre");
        String numero= scanner.next();
        return livreService.delete(numero);
    }

    public void findByNI()throws SQLException{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the Numero d'inventair of the livre");
        String numero= scanner.next();
        Livre livre=livreService.findByNI(numero);
        System.out.print("***********************");
        System.out.print("Book:");
        System.out.print("Title:"+livre.getCollection().getTitle());
        System.out.print("Auteur:"+livre.getCollection().getAuteur());
        System.out.print("Book disponible:"+livre.getCollection().getTotale());
        System.out.print("Isbn:"+livre.getCollection().getIsbn());
        System.out.print("Stat:"+livre.getStatus().getLabel());
    }

    public void findAll()throws SQLException{
        List<Livre> livres = livreService.findAll();

        System.out.println("************************************************************************************************");
        System.out.printf("| %-15s | %-20s | %-30s | %-20s | %-20s | %-15s | %-10s |%n",
                "Book", "Num Inventair", "Title", "Auteur", "Book disponible", "Isbn", "Stat");
        System.out.println("************************************************************************************************");

        for (Livre livre : livres) {
            System.out.printf("| %-15s | %-20s | %-30s | %-20s | %-20d | %-15s | %-10s |%n",
                    " ",
                    livre.getNumeroInventair(),
                    livre.getCollection().getTitle(),
                    livre.getCollection().getAuteur(),
                    livre.getCollection().getTotale(),
                    livre.getCollection().getIsbn(),
                    livre.getStatus().getLabel());
        }

        System.out.println("************************************************************************************************");
    }

    public void statistique()throws SQLException{
       System.out.print( livreService.Statistiques()+"\n");
    }

}