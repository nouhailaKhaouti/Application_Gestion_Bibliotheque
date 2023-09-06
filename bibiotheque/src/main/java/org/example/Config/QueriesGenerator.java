//package org.example.Config;
//
//import java.sql.PreparedStatement;
//import java.util.Map;
//public class QueriesGenerator {
//
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
//}
