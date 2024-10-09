package com.mjc.school;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.NewsView;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.datasource.AuthorData;
import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.implementation.DefaultNewsRepository;
import com.mjc.school.service.implementation.DefaultNewsService;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.validation.Validator;

public class Main {
    public static void main(String[] args) {
        AuthorData authorData = new AuthorData();
        NewsDataSource newsDataSource = new NewsDataSource();
        NewsRepository newsRepository = new DefaultNewsRepository(authorData, newsDataSource);
        Validator validator = new Validator();
        NewsService newsService = new DefaultNewsService(newsRepository, validator);
        NewsController newsController = new NewsController(newsService);
        new NewsView(newsController);
    }
}
