package org.example.Controller;

import org.example.Model.Status;
import org.example.Service.Impl.StatusService;

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

    public void findAll() throws SQLException {
        List<Status> statusList = statusService.findAll();

        System.out.println("+----------------------+");
        System.out.println("| Status Information   |");
        System.out.println("+----------------------+");

        for (Status status : statusList) {
            System.out.println("| Status ID: " + status.getId());
            System.out.println("| Status Label: " + status.getLabel());
            System.out.println("+----------------------+");
        }
    }

}
