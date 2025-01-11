package org.example.sqlchatbotapp.controller;

import org.example.sqlchatbotapp.model.QueryRequest;
import org.example.sqlchatbotapp.service.AIService;
import org.example.sqlchatbotapp.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class QueryController {

    private final AIService aiService;
    private final DatabaseService databaseService;

    @Autowired
    public QueryController(AIService aiService, DatabaseService databaseService) {
        this.aiService = aiService;
        this.databaseService = databaseService;
    }

    @PostMapping("/generate-sql-query")
    public ResponseEntity<Map<String, String>> generateSqlQuery(@RequestBody QueryRequest request) {
        try {
            String naturalLanguageQuery = request.getQuery();
            String sqlQuery = aiService.generateSqlQuery(naturalLanguageQuery);
            return ResponseEntity.ok(Map.of("sqlQuery", sqlQuery));
        } catch (Exception e) {
            e.printStackTrace(); // Log the stack trace for debugging
            return ResponseEntity.status(500).body(Map.of("error", "Error generating SQL query: " + e.getMessage()));
        }
    }

    @PostMapping("/query")
    public ResponseEntity<?> getQuery(@RequestBody QueryRequest request) {
        try {
            String sqlQuery = request.getQuery(); // Assume request contains SQL query from the frontend
            List<Map<String, Object>> results = databaseService.executeQuery(sqlQuery);

            // Ensure the results are not null
            return ResponseEntity.ok(results != null ? results : List.of());

        } catch (Exception e) {
            e.printStackTrace(); // Log the stack trace for debugging
            return ResponseEntity.status(500).body(Map.of("error", "Error executing SQL query: " + e.getMessage()));
        }
    }
}

