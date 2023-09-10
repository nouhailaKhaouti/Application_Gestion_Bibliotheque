package org.example.Controller;

import org.example.Model.Status;
import org.example.Service.StatusService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StatusController {

    StatusService statusService=new StatusService();

    public String save()throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter label of the status");
        String label= scanner.next();
        Status status=new Status(label);
        return statusService.save(status);
    }

    public String update()throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter label of the status");
        String label= scanner.next();
        System.out.print("Enter id of the status");
        Long id= scanner.nextLong();
        Status status=new Status(label);
        status.setId(id);
        return statusService.update(status);
    }

    public String delete()throws SQLException{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter id of the status");
        Long id= scanner.nextLong();
        return statusService.delete(id);
    }

    public void findAll()throws SQLException{

        List<Status> statusList=statusService.findAll();
        for(Status status:statusList){
            System.out.print("************************");
            System.out.print("status id:"+status.getId());
            System.out.print("status label:"+status.getLabel());
            System.out.print("************************");
        }
    }
}
