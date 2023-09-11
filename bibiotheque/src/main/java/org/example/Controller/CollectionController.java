package org.example.Controller;


import org.example.Model.Collection;
import org.example.Service.CollectionService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CollectionController {
     CollectionService collectionService=new CollectionService();
    public void Update() throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter ISBN: ");
        String isbn = scanner.next();
        System.out.print("Enter Title: ");
        String title = scanner.next();
        System.out.print("Enter Author: ");
        String author = scanner.next();
        System.out.println("Provided ISBN: " + isbn);
        Collection collection=new Collection(isbn,title,author);
        System.out.print( collectionService.update(collection));
    }

    public void findAll()throws SQLException{
        List<Collection> collections = collectionService.findAll();

        System.out.println("*********************************************************************************************************");
        System.out.printf("| %-15s | %-30s | %-30s | %-20s | %-15s |%n", "Book", "Title", "Auteur", "Book disponible", "Isbn");
        System.out.println("*********************************************************************************************************");
        for (Collection collection : collections) {
            System.out.printf("| %-15s | %-30s | %-30s | %-20d | %-15s |%n",
                    " ",
                    collection.getTitle(),
                    collection.getAuteur(),
                    collection.getTotale(),
                    collection.getIsbn());
        }
        System.out.println("********************************************************************************************************");
    }

    public void findByIsbn() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ISBN: ");
        String isbn = scanner.next();
        Collection collection = collectionService.findByIsbn(isbn);

        System.out.println("+-------------------------+");
        System.out.println("| Book Information        |");
        System.out.println("+-------------------------+");
        System.out.println("| Title: " + collection.getTitle());
        System.out.println("| Auteur: " + collection.getAuteur());
        System.out.println("| Book disponible: " + collection.getTotale());
        System.out.println("+-------------------------+");
    }



}
