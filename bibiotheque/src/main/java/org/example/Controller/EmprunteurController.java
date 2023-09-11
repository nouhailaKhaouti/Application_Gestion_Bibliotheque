package org.example.Controller;

import org.example.Model.Emprunteur;
import org.example.Model.Emprunteur;
import org.example.Service.EmprunteurService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmprunteurController {
    EmprunteurService emprunteurService=new EmprunteurService();
    public String save()throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter MemberShip number: ");
        String membership = scanner.next();
        System.out.print("Enter fullNumber: ");
        String fullName = scanner.next();
        System.out.print("Enter Email: ");
        String email = scanner.next();
        System.out.print("Enter Phone: ");
        String phone = scanner.next();
        Emprunteur emprunteur=new Emprunteur(membership,fullName,email,phone);
        boolean res=emprunteurService.save(emprunteur);
        if (res){
            return "this emprunteur has been added successfully";
        }else{
            return "an error occurred while adding this emprunteur try again";
        }
    }
    public String update()throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter id: ");
        Long id = scanner.nextLong();
        if(emprunteurService.findById(id)!=null) {
            System.out.print("Enter MemberShip number: ");
            String membership = scanner.next();
            System.out.print("Enter fullNumber: ");
            String fullName = scanner.next();
            System.out.print("Enter Email: ");
            String email = scanner.next();
            System.out.print("Enter Phone: ");
            String phone = scanner.next();
            Emprunteur emprunteur = new Emprunteur(membership, fullName, email, phone);
            emprunteur.setId(id);
            return emprunteurService.update(emprunteur);
        }
        return "this emprunteur doesn't exists";
    }

    public String delete()throws SQLException{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter id: ");
        Long id = scanner.nextLong();
        if(emprunteurService.findById(id)!=null) {
            boolean res = emprunteurService.delete(id);
            if (res) {
                return "this emprunteur has been deleted successfully";
            } else {
                return "an error occurred while deleting this emprunteur try again";
            }
        }
        return "this emprunteur doesn't exists";
    }

    public void findAll()throws SQLException{
        List<Emprunteur> emprunteurs=emprunteurService.findAll();
        for(Emprunteur emprunteur:emprunteurs){
            System.out.print("***********************");
            System.out.print("Emprunteur :");
            System.out.print("MemberShip:"+emprunteur.getMembreShip());
            System.out.print("Fullname:"+emprunteur.getFullName());
            System.out.print("Phone:"+emprunteur.getPhone());
            System.out.print("email:"+emprunteur.getEmail());
        }
    }

    public void findByMemberShip()throws SQLException{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter memberShip: ");
        String member = scanner.next();
        Emprunteur emprunteur=emprunteurService.findByMemberShip(member);
        System.out.print("***********************");
        System.out.print("Emprunteur:");
        System.out.print("MemberShip:"+emprunteur.getMembreShip());
        System.out.print("Fullname:"+emprunteur.getFullName());
        System.out.print("Phone:"+emprunteur.getPhone());
        System.out.print("email:"+emprunteur.getEmail());
    }

}
