package com.mjc.school.controller;

import java.util.Scanner;

public class NewsView {
    NewsController newsController;

    public NewsView(NewsController newsController){
        this.newsController = newsController;
        showControlMenu();
        selectOperation();
    }

    private void showControlMenu () {
        System.out.println("""
                Enter the number of operation:
                1 - Get all news.
                2 - Get news by id.
                3 - Create news.
                4 - Update news.
                5 - Remove news by id.
                0 - Exit."""
        );
    }

    private void selectOperation () {
        Scanner scanner = new Scanner(System.in);
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 0:
                    return;
                case 1:
                    newsController.getAllNews();
                    break;
                case 2:
                    newsController.getNews(scanner);
                    break;
                case 3:
                    newsController.createNews(scanner);
                    break;
                case 4:
                    newsController.updateNews(scanner);
                    break;
                case 5:
                    newsController.deleteNews(scanner);
                    break;
                default:
                    System.out.println("Command not found.");
            }
        } catch (NumberFormatException e){
            System.out.println("Command not found.");
        }
        showControlMenu();
        selectOperation();
        scanner.close();
    }
}
