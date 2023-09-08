package org.example.Config;
import java.sql.SQLException;
import java.util.List;

public interface QueriesGeneratorInterface {
    boolean insertData(String tableName, Object obj) throws SQLException;
    boolean updateData(String tableName, Object obj,Long id) throws SQLException;
    boolean deleteData(String tableName, Long id) throws SQLException;
//    List<Object> findAll(String tableName) throws SQLException;
//    Object findById(String tableName,Long id) throws SQLException;
//    List<Object> findByAttribute(String tableName, String attribute,Object attributeValue) throws SQLException;
}
