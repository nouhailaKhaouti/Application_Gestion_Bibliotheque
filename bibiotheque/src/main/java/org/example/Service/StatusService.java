package org.example.Service;

import org.example.Model.Status;
import org.example.Repository.StatusRepository;

import java.sql.SQLException;
import java.util.List;

public class StatusService {
    StatusRepository statusRepository=new StatusRepository();
    public String save(Status status) throws SQLException {
        if(statusRepository.findByLabel(status.getLabel())==null){
            boolean res= statusRepository.save(status);
            if(res){
                return "status has been added successfully";
            }else{
                return "an error occured why adding this status";
            }
        }else{
            return "this status already exists";
        }

    }
    public String update(Status status) throws SQLException {
        if(statusRepository.statusExists(status.getLabel())>0){
            boolean res= statusRepository.update(status);
            if(res){
                return "status has been updated successfully";
            }else{
                return "updating status has failed";
            }
        }else{
            return "status with id:"+status.getId()+" doesn't exist";
        }

    }

    public List<Status> findAll()throws  SQLException{
        List<Status> status=statusRepository.findAll();
        if(status!=null){
            return status;
        }else{
            return null;
        }
    }

    public Status findByLabel(String label)throws SQLException{
        Status status=statusRepository.findByLabel(label);
        if(status!=null){
            return status;
        }else{
            return null;
        }
    }

    public String delete(Long id)throws SQLException{
        Status status=statusRepository.findById(id);
        if(status!=null){
            boolean res= statusRepository.delete(id);
            if(res){
                return "this status has been deleted successfully";
            }else{
                return "an error occurred while deleting status";
            }
        }else{
            return "this status doesn't exists";
        }
    }
}
