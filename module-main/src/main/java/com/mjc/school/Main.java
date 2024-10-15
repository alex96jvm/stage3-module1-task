package com.mjc.school;

import com.mjc.school.controller.implementation.NewsController;
import com.mjc.school.controller.view.NewsView;
import com.mjc.school.repository.NewsModelRepository;
import com.mjc.school.repository.datasource.AuthorData;
import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.implementation.DefaultNewsRepository;
import com.mjc.school.service.implementation.DefaultNewsService;
import com.mjc.school.service.NewsDtoService;

public class Main {
    public static void main(String[] args) {
        AuthorData authorData = new AuthorData();
        NewsDataSource newsDataSource = new NewsDataSource();
        NewsModelRepository newsRepository = new DefaultNewsRepository(authorData, newsDataSource);
        NewsDtoService newsService = new DefaultNewsService(newsRepository);
        NewsController newsController = new NewsController(newsService);
        new NewsView(newsController);
    }
}
