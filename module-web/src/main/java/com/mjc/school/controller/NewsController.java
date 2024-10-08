package com.mjc.school.controller;

import com.mjc.school.service.exception.NewsException;
import com.mjc.school.service.NewsService;
import java.util.Scanner;

public class NewsController {
    private final String OPERATION = "Operation: ";
    private final String ENTER_NEWS_ID = "Enter news id:";
    private final String ENTER_AUTHOR_ID = "Enter author id:";
    private final String ENTER_NEWS_TITLE = "Enter news title:";
    private final String ENTER_NEWS_CONTENT = "Enter news content:";
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    public void createNews(Scanner scanner) {
        System.out.printf("%sCreate news.%n", OPERATION);
        System.out.println(ENTER_NEWS_TITLE);
        String title = scanner.nextLine();
        System.out.println(ENTER_NEWS_CONTENT);
        String content = scanner.nextLine();
        System.out.println(ENTER_AUTHOR_ID);
        String authorId = scanner.next(); scanner.nextLine();
        try {
            System.out.println(newsService.createNews(title, content, authorId));
        } catch (NewsException newsException){
            System.out.println(newsException);
            createNews(scanner);
        }
    }

    public void getAllNews() {
        System.out.printf("%sGet all news.%n", OPERATION);
        newsService.getAllNews().forEach(System.out::println);
    }

    public void getNews(Scanner scanner) {
        System.out.printf("%sGet news by id.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            System.out.println(newsService.getNews(scanner.next()));
        } catch (NewsException newsException) {
            System.out.println(newsException);
        }
    }

    public void updateNews(Scanner scanner) {
        System.out.printf("%sUpdate news.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        String id = scanner.next(); scanner.nextLine();
        try {
            newsService.getNews(id);
        } catch (NewsException newsException){
            System.out.println(newsException);
            updateNews(scanner);
        }
        System.out.println(ENTER_NEWS_TITLE);
        String title = scanner.nextLine();
        System.out.println(ENTER_NEWS_CONTENT);
        String content = scanner.nextLine();
        System.out.println(ENTER_AUTHOR_ID);
        String authorId = scanner.next();
        try {
            System.out.println(newsService.updateNews(id, title, content, authorId));
        } catch (NewsException newsException) {
            System.out.println(newsException);
            updateNews(scanner);
        }
    }

    public void deleteNews(Scanner scanner) {
        System.out.printf("%sRemove news by id.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        String id = scanner.next();
        try {
            System.out.println(newsService.deleteNews(id));
        } catch (NewsException newsException) {
            System.out.println(newsException);
        }
    }
}
