package com.mjc.school.controller;

import com.mjc.school.service.NewsException;
import com.mjc.school.service.NewsService;
import java.util.Scanner;

public class NewsController {
    NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    public void createNews(Scanner scanner) {
        System.out.println("Operation: Create news.");
        System.out.println("Enter news title:");
        String title = scanner.nextLine();
        System.out.println("Enter news content:");
        String content = scanner.nextLine();
        System.out.println("Enter author id:");
        String authorId = scanner.next(); scanner.nextLine();
        try {
            System.out.println(newsService.createNews(title, content, authorId));
        } catch (NewsException newsException){
            System.out.println(newsException);
            createNews(scanner);
        }
    }

    public void getAllNews() {
        System.out.println("Operation: Get all news.");
        newsService.getAllNews().forEach(System.out::println);
    }

    public void getNews(Scanner scanner) {
        System.out.println("Operation: Get news by id.");
        System.out.println("Enter news id:");
        try {
            System.out.println(newsService.getNews(scanner.next()));
        } catch (NewsException newsException) {
            System.out.println(newsException);
        }
    }

    public void updateNews(Scanner scanner) {
        System.out.println("Operation: Update news.");
        System.out.println("Enter news id:");
        String id = scanner.next(); scanner.nextLine();
        try {
            newsService.getNews(id);
        } catch (NewsException newsException){
            System.out.println(newsException);
            updateNews(scanner);
        }
        System.out.println("Enter news title:");
        String title = scanner.nextLine();
        System.out.println("Enter news content:");
        String content = scanner.nextLine();
        System.out.println("Enter author id:");
        String authorId = scanner.next();
        try {
            System.out.println(newsService.updateNews(id, title, content, authorId));
        } catch (NewsException newsException) {
            System.out.println(newsException);
            updateNews(scanner);
        }
    }

    public void deleteNews(Scanner scanner) {
        System.out.println("Operation: Remove news by id.");
        System.out.println("Enter news id:");
        String id = scanner.next();
        try {
            System.out.println(newsService.deleteNews(id));
        } catch (NewsException newsException) {
            System.out.println(newsException);
        }
    }
}
