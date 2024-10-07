package com.mjc.school;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.NewsView;
import com.mjc.school.repository.FromFileNewsDataSource;
import com.mjc.school.repository.NewsDataSource;
import com.mjc.school.service.DefaultNewsService;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.Validator;

public class Main {
    public static void main(String[] args) {
        NewsDataSource newsDataSource = FromFileNewsDataSource.getAuthorDataSource();
        NewsService newsService = new DefaultNewsService(newsDataSource, new Validator());
        NewsController newsController = new NewsController(newsService);
        new NewsView(newsController);
    }
}
