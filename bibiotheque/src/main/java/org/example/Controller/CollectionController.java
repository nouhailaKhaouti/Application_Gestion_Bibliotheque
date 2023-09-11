package org.example.Controller;


import org.example.Model.Collection;
import org.example.Repository.CollectionRepository;
import org.example.Service.CollectionService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CollectionController {
     CollectionService collectionService=new CollectionService();
    public void Update() throws SQLException {
        //can't add totale
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter ISBN: ");
        String isbn = scanner.next();
        System.out.print("Enter Title: ");
        String title = scanner.next();
        System.out.print("Enter Author: ");
        String author = scanner.next();
        System.out.print("Enter Total Number of Books: ");
        int totalBooks = scanner.nextInt();
        System.out.println("Provided ISBN: " + isbn);
        Collection collection=new Collection(isbn,title,totalBooks,author);
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

    public void findByIsbn()throws SQLException{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter ISBN: ");
        String isbn = scanner.next();
        Collection collection=collectionService.findByIsbn(isbn);
        System.out.print("***********************");
        System.out.print("Book:");
        System.out.print("Title:"+collection.getTitle());
        System.out.print("Auteur:"+collection.getAuteur());
        System.out.print("Book disponible:"+collection.getTotale());
    }


}
