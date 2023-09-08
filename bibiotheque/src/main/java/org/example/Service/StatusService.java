package org.example.Service;

import org.example.Model.Status;
import org.example.Repository.StatusRepository;

import java.sql.SQLException;
import java.util.List;

public class StatusService {
    StatusRepository statusRepository=new StatusRepository();
    public Boolean save(Status status) throws SQLException {
        if(statusRepository.findById(status.getId())!=null){
            boolean res= statusRepository.save(status);
            if(res){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
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

    public Status findById(Long id)throws SQLException{
        Status status=statusRepository.findById(id);
        if(status!=null){
            return status;
        }else{
            return null;
        }
    }

    public Boolean delete(Long id)throws SQLException{
        Status status=statusRepository.findById(id);
        if(status!=null){
            boolean res= statusRepository.delete(id);
            if(res){
                return true;
            }else{
                return false;
            }
        }else{
            return null;
        }
    }
}
