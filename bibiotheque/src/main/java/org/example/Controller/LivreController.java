package org.example.Controller;

import org.example.Model.Collection;
import org.example.Model.Livre;
import org.example.Service.Impl.CollectionService;
import org.example.Service.Impl.LivreService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LivreController {
    LivreService livreService=new LivreService();
    CollectionService collectionService=new CollectionService();


    public void save()throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the Isbn of the livre");
        String isbn= scanner.next();
        Collection collectionN=collectionService.findByIsbn(isbn);
        if(collectionN==null){
            System.out.print("Enter Title: ");
            String title = scanner.next();
            System.out.print("Enter Author: ");
            String author = scanner.next();
            System.out.println("Provided ISBN: " + isbn);
            Collection collection=new Collection(isbn,title,0,author);
            collectionN=collection;
            collectionService.save(collectionN);
        }
        System.out.print("Enter the numero Inventair");
        String numero=scanner.next();
        Livre livre=new Livre(numero,collectionN);

        System.out.print(livreService.save(livre));
    }

    public void Delete()throws SQLException{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the Numero d' inventair of the livre");
        String numero= scanner.next();
        System.out.print(livreService.delete(numero));
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
        System.out.print("Isbn:"+livre.getCollection().getIsbn());
        System.out.print("Stat:"+livre.getStatus().getLabel());
    }

    public void findByTitre()throws SQLException{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the title of the livre");
        String titre= scanner.next();
       List<Livre> livres=livreService.findByTitre(titre);
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
    public void findByAuthor()throws SQLException{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the author of the livre");
        String author= scanner.next();
        List<Livre> livres=livreService.findByAuthor(author);
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

    public void findDisponible()throws SQLException{
        List<Livre> livres = livreService.findDisponible();

        System.out.println("************************************************************************************************");
        System.out.printf("| %-15s | %-20s | %-30s | %-20s | %-20s | %-15s | %-10s |%n",
                "Book", "Num Inventair", "Title", "Auteur", "Book disponible", "Isbn");
        System.out.println("************************************************************************************************");

        for (Livre livre : livres) {
            System.out.printf("| %-15s | %-20s | %-30s | %-20s | %-20d | %-15s |%n",
                    " ",
                    livre.getNumeroInventair(),
                    livre.getCollection().getTitle(),
                    livre.getCollection().getAuteur(),
                    livre.getCollection().getTotale(),
                    livre.getCollection().getIsbn());
        }

        System.out.println("************************************************************************************************");
    }

    public void statistique() throws SQLException {
        System.out.println("+----------------------+");
        System.out.println("| Statistiques         |");
        System.out.println("+----------------------+");

        System.out.println("| Disponible");
        System.out.println("| " + livreService.Statistiques("Disponible"));
        System.out.println("|");

        System.out.println("| Emprunté");
        System.out.println("| " + livreService.Statistiques("Emprunte"));
        System.out.println("|");

        System.out.println("| Perdue");
        System.out.println("| " + livreService.Statistiques("Perdue"));
        System.out.println("+----------------------+");
    }

    public void change_status_perdue(){
        System.out.println(livreService.change_status_perdue());
    }

}
