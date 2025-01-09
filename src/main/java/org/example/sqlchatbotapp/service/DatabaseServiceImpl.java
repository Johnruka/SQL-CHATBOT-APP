package org.example.sqlchatbotapp.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DatabaseServiceImpl extends DatabaseService {

    public DatabaseServiceImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<Map<String, Object>> executeQuery(String sqlQuery) {
        // Handle potential exceptions and log query executions
        try {
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sqlQuery);
            // Log results or handle them as needed
            return results;
        } catch (Exception ex) {
            // Add more sophisticated error handling as needed
            System.err.println("An error occurred while executing the SQL query: " + ex.getMessage());
            return null;
        }
    }
}

