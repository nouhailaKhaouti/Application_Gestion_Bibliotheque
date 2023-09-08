package org.example.Controller;


import org.example.Model.Collection;
import org.example.Repository.CollectionRepository;
import org.example.Service.CollectionService;

import java.sql.SQLException;
import java.util.Scanner;

public class CollectionController {
     CollectionService collectionService=new CollectionService();
    public String save() throws SQLException {
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
        if(collectionService.save(collection)){
          return "your collection is successfully added";
        }
        return "saving collection failed";
    }
}
