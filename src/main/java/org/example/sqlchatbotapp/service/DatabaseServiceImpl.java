package org.example.sqlchatbotapp.service;

import org.example.sqlchatbotapp.exception.ServiceException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> executeQuery(String sqlQuery) throws ServiceException {

        try {
            // Execute the SQL query
            return jdbcTemplate.queryForList(sqlQuery);
        } catch (Exception e) {
            // Log and wrap the exception
            System.err.println("An error occurred while executing the SQL query: " + e.getMessage());
            throw new ServiceException("Failed to execute query: " + sqlQuery, e);
        }

    }
}




