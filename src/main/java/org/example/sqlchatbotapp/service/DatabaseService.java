package org.example.sqlchatbotapp.service;


import org.example.sqlchatbotapp.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface DatabaseService {
    List<Map<String, Object>> executeQuery(String sqlQuery) throws ServiceException;
}





