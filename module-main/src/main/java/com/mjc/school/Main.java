package com.mjc.school;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.NewsView;
import com.mjc.school.repository.FromFileNewsDataSource;
import com.mjc.school.service.DefaultNewsService;

public class Main {
    public static void main(String[] args) {
        new NewsView(new NewsController(new DefaultNewsService(FromFileNewsDataSource.getAuthorDataSource())));
    }
}
