package com.mjc.school.controller;

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
        try {
            Long authorId = Long.parseLong(scanner.nextLine());
            System.out.println(newsService.createNews(title, content, authorId));
        } catch (NumberFormatException e){
            getNews(scanner);
        }
    };

    public void getAllNews() {
        System.out.println("Operation: Get all news.");
        newsService.getAllNews().forEach(System.out::println);
    };

    public void getNews(Scanner scanner) {
        System.out.println("Operation: Get news by id.");
        System.out.println("Enter news id:");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            newsService.getNews(id);
        } catch (NumberFormatException e){
            getNews(scanner);
        }
    };

    public void updateNews(Scanner scanner) {
        long id = 0L;
        System.out.println("Operation: Update news.");
        System.out.println("Enter news id:");
        try {
            id = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e){
            updateNews(scanner);
        }
        System.out.println("Enter news title:");
        String title = scanner.nextLine();
        System.out.println("Enter news content:");
        String content = scanner.nextLine();
        System.out.println("Enter author id:");
        try {
            Long authorId = Long.parseLong(scanner.nextLine());
            System.out.println(newsService.updateNews(id, title, content, authorId));
        } catch (NumberFormatException e){
            updateNews(scanner);
        }
    };

    public void deleteNews(Scanner scanner) {
        System.out.println("Operation: Remove news by id.");
        System.out.println("Enter news id:");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            System.out.println(newsService.deleteNews(id));
        } catch (NumberFormatException e){
            deleteNews(scanner);
        }
    };
}
