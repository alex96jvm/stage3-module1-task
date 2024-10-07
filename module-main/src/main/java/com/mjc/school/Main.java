package com.mjc.school;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.NewsView;
import com.mjc.school.repository.AuthorDataSource;
import com.mjc.school.repository.implementations.FromFileAuthorDataSource;
import com.mjc.school.repository.implementations.FromFileNewsDataSource;
import com.mjc.school.repository.NewsDataSource;
import com.mjc.school.service.implementations.DefaultNewsService;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.validation.Validator;

public class Main {
    public static void main(String[] args) {
        AuthorDataSource authorDataSource = FromFileAuthorDataSource.getAuthorDataSource();
        NewsDataSource newsDataSource = FromFileNewsDataSource.getNewsDataSource();
        Validator validator = new Validator(authorDataSource.getAuthors(), newsDataSource.getAllNews());
        NewsService newsService = new DefaultNewsService(newsDataSource.getAllNews(), validator);
        NewsController newsController = new NewsController(newsService);
        new NewsView(newsController);
    }
}
