package com.mjc.school;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.NewsView;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.datasource.AuthorDataSource;
import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.implementation.DefaultNewsRepository;
import com.mjc.school.service.implementation.DefaultNewsService;
import com.mjc.school.service.NewsService;

public class Main {
    public static void main(String[] args) {
        AuthorDataSource authorDataSource = new AuthorDataSource();
        NewsDataSource newsDataSource = new NewsDataSource();
        NewsRepository newsRepository = new DefaultNewsRepository(authorDataSource, newsDataSource);
        NewsService newsService = new DefaultNewsService(newsRepository);
        NewsController newsController = new NewsController(newsService);
        new NewsView(newsController);
    }
}
