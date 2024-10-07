package com.mjc.school.controller;

import java.util.Scanner;

public class NewsView {
    private final NewsController newsController;

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

    private void selectOperation() {
        String NOT_FOUND = "Command not found.";
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
                    System.out.println(NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            System.out.println(NOT_FOUND);
        }
        showControlMenu();
        selectOperation();
        scanner.close();
    }
}
