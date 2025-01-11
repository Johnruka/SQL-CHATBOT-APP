package org.example.sqlchatbotapp.service;


import org.example.sqlchatbotapp.exception.ServiceException;

public interface AIService {
    String generateSqlQuery(String naturalLanguageQuery) throws ServiceException;

    }

