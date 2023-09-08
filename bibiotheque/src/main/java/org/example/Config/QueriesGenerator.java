package org.example.Config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueriesGenerator implements QueriesGeneratorInterface {
    static DatabaseConnection db=new DatabaseConnection();
    static Connection connection = db.connect();

    @Override
    public boolean insertData(String tableName, Object object) throws SQLException {
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(tableName).append(" (");

        StringBuilder values = new StringBuilder(" VALUES (");

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();

        for (Field field : fields) {
            String fieldName = field.getName();
            String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

            for (Method method : methods) {
                if (method.getName().equals(getterName)) {
                    try {
                        query.append(fieldName).append(", ");
                        values.append("?, ");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        query.setLength(query.length() - 2);
        values.setLength(values.length() - 2);
        query.append(")").append(values).append(")");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;

            for (Field field : fields) {
                String fieldName = field.getName();
                String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

                for (Method method : methods) {
                    if (method.getName().equals(getterName)) {
                        try {
                            Object fieldValue = method.invoke(object);
                            preparedStatement.setObject(index++, fieldValue);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }
            }
            preparedStatement.executeUpdate();
            return true;
        }
    }

    @Override
    public boolean updateData(String tableName,Object object, Long id)throws SQLException{
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(tableName).append(" SET ");

        Class<?> cla = object.getClass();
        Field[] fields = cla.getDeclaredFields();
        Method[] methods = cla.getDeclaredMethods();

        for (Field field : fields) {
            String fieldName = field.getName();
            String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            for (Method method : methods) {
                if (method.getName().equals(getterName)) {
                    try {
                        query.append(fieldName).append("=?, ");
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
        }
        query.setLength(query.length() - 2);
        query.append(" WHERE id=?");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;

            for (Field field : fields) {
                String fieldName = field.getName();
                String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

                for (Method method : methods) {
                    if (method.getName().equals(getterName)) {
                        try {
                            Object fieldValue = method.invoke(object);
                            preparedStatement.setObject(index++, fieldValue);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }
            }
            preparedStatement.setLong(index, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean deleteData(String tableName,Long id)throws SQLException{
        String query = "DELETE FROM " + tableName + " WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//    @Override
//    public List<Object> findAll(String tableName) throws SQLException {
//        List<Object> resultList = new ArrayList<>();
//
//        String query = "SELECT * FROM " + tableName;
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//            while (resultSet.next()) {
//                Object object = object.getClass().getDeclaredConstructor().newInstance();
//
//                for (Field field : object.getClass().getDeclaredFields()) {
//                    String fieldName = field.getName();
//                    String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
//
//                    try {
//                        Method setter = object.getClass().getMethod(setterName, field.getType());
//                        setter.invoke(object, resultSet.getObject(fieldName));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                resultList.add(object);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return resultList;
//    }
//
//    public Object findById(String tableName, Long id) throws SQLException {
//        String query = "SELECT * FROM " + tableName + " WHERE id=?"; // Assuming the primary key is named "id"
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setLong(1, id);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    Object object = object.getClass().getDeclaredConstructor().newInstance();
//
//                    for (Field field : object.getClass().getDeclaredFields()) {
//                        String fieldName = field.getName();
//                        String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
//
//                        try {
//                            Method setter = object.getClass().getMethod(setterName, field.getType());
//                            setter.invoke(object, resultSet.getObject(fieldName));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    return object;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }

//            @Override
//    public Object findById(String tableName,Long id) throws SQLException{
//        String query = "SELECT * FROM " + tableName + " WHERE id=?"; // Assuming the primary key is named "id"
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setLong(1, id);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    Object object = objectClass.getDeclaredConstructor().newInstance();
//
//                    for (Field field : objectClass.getDeclaredFields()) {
//                        String fieldName = field.getName();
//                        String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
//
//                        try {
//                            Method setter = objectClass.getMethod(setterName, field.getType());
//                            setter.invoke(object, resultSet.getObject(fieldName));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    return object;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    @Override
//    public List<Object> findByAttribute(String tableName,String attributeName,Object attributeValue)throws SQLException{
//        List<Object> resultList = new ArrayList<>();
//
//        String query = "SELECT * FROM " + tableName + " WHERE " + attributeName + "=?";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setObject(1, attributeValue);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    Object object = objectClass.getDeclaredConstructor().newInstance();
//
//                    for (Field field : objectClass.getDeclaredFields()) {
//                        String fieldName = field.getName();
//                        String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
//
//                        try {
//                            Method setter = objectClass.getMethod(setterName, field.getType());
//                            setter.invoke(object, resultSet.getObject(fieldName));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    resultList.add(object);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return resultList;
//    }

//    private static String generateUpdateQuery(String tableName, Object obj, Map<String, Object> updateFields) {
//        if (tableName == null || obj == null || updateFields.isEmpty()) {
//            throw new IllegalArgumentException("Table name, object, and updateFields cannot be null or empty.");
//        }
//
//        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
//        boolean firstField = true;
//
//        for (Map.Entry<String, Object> entry : updateFields.entrySet()) {
//            String fieldName = entry.getKey();
//            Object fieldValue = entry.getValue();
//
//            if (hasValue(fieldValue)) {
//                if (!firstField) {
//                    query.append(", ");
//                }
//                query.append(fieldName).append(" = ?");
//                firstField = false;
//            }
//        }
//
//        // Assuming you have a primary key field 'id' for identifying the record to update.
//        if (hasValue(obj.getId())) {
//            query.append(" WHERE id = ?");
//        } else {
//            throw new IllegalArgumentException("Object must have a valid ID.");
//        }
//
//        return query.toString();
//    }
//
//    private static void setParameters(PreparedStatement preparedStatement, Object obj, Map<String, Object> updateFields) {
//        int parameterIndex = 1;
//
//        for (Object fieldValue : updateFields.values()) {
//            if (hasValue(fieldValue)) {
//                preparedStatement.setObject(parameterIndex++, fieldValue);
//            }
//        }
//
//        // Set the primary key value.
//        preparedStatement.setObject(parameterIndex, obj.getId());
//    }
}
