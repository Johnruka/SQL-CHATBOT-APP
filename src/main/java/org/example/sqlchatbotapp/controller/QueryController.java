package org.example.sqlchatbotapp.controller;

import org.example.sqlchatbotapp.model.QueryRequest;
import org.example.sqlchatbotapp.service.AIService;
import org.example.sqlchatbotapp.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QueryController {

    private final AIService aiService;
    private final DatabaseService databaseService;

    @Autowired
    public QueryController(AIService aiService, DatabaseService databaseService) {
        this.aiService = aiService;
        this.databaseService = databaseService;
    }

    @PostMapping("/query")
    public ResponseEntity<?> getQuery(@RequestBody QueryRequest request) {
        String naturalLanguageQuery = request.getQuery();
        String sqlQuery = aiService.generateSqlQuery(naturalLanguageQuery);

        // Optional: Execute SQL query
        // List<Map<String, Object>> results = databaseService.executeQuery(sqlQuery);

        return ResponseEntity.ok(sqlQuery); // Returns SQL query for now
    }
}

