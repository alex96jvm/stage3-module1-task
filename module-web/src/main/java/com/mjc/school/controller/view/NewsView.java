package com.mjc.school.controller.view;

import com.mjc.school.controller.implementation.NewsController;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.NewsException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class NewsView {
    private final NewsController newsController;
    private final String OPERATION = "Operation: ";
    private final String ENTER_NEWS_ID = "Enter news id:";
    private final String ENTER_AUTHOR_ID = "Enter author id:";
    private final String ENTER_NEWS_TITLE = "Enter news title:";
    private final String ENTER_NEWS_CONTENT = "Enter news content:";
    private final String NEWS = "News";
    private final String AUTHOR = "Author";

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
                    readAllNews().forEach(System.out::println);
                    break;
                case 2:
                    System.out.println(readByIdNews(scanner));
                    break;
                case 3:
                    System.out.println(createNews(scanner));
                    break;
                case 4:
                    System.out.println(updateNews(scanner));
                    break;
                case 5:
                    System.out.println(deleteNews(scanner));
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

    private List<NewsDto> readAllNews() {
        System.out.printf("%sGet all news.%n", OPERATION);
        return newsController.readAll();
    }

    private NewsDto readByIdNews(Scanner scanner) {
        System.out.printf("%sGet news by id.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            Long id = validateNumericValue(scanner.next(), NEWS);
            return newsController.readById(id);
        } catch (NewsException newsException) {
            System.out.println(newsException);
            return readByIdNews(scanner);
        }
    }

    private NewsDto createNews(Scanner scanner) {
        System.out.printf("%sCreate news.%n", OPERATION);
        System.out.println(ENTER_NEWS_TITLE);
        String title = scanner.nextLine();
        System.out.println(ENTER_NEWS_CONTENT);
        String content = scanner.nextLine();
        System.out.println(ENTER_AUTHOR_ID);
        try {
            Long authorId = validateNumericValue(scanner.nextLine(), AUTHOR);
            NewsDto newsDto = new NewsDto(title, content, LocalDateTime.now(), LocalDateTime.now(), authorId);
            return newsController.create(newsDto);
        } catch (NewsException newsException){
            System.out.println(newsException);
            return createNews(scanner);
        }
    }

    private NewsDto updateNews(Scanner scanner) {
        NewsDto newsDto = new NewsDto();
        System.out.printf("%sUpdate news.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            Long id = validateNumericValue(scanner.nextLine(), NEWS);
            newsDto = newsController.readById(id);
        } catch (NewsException newsException){
            System.out.println(newsException);
            updateNews(scanner);
        }
        System.out.println(ENTER_NEWS_TITLE);
        String title = scanner.nextLine();
        System.out.println(ENTER_NEWS_CONTENT);
        String content = scanner.nextLine();
        System.out.println(ENTER_AUTHOR_ID);
        try {
            Long authorId = validateNumericValue(scanner.nextLine(), AUTHOR);
            newsDto.setTitle(title);
            newsDto.setContent(content);
            newsDto.setLastUpdatedDate(LocalDateTime.now());
            newsDto.setAuthorId(authorId);
            return newsController.update(newsDto);
        } catch (NewsException newsException) {
            System.out.println(newsException);
            return updateNews(scanner);
        }
    }

    private Boolean deleteNews(Scanner scanner) {
        System.out.printf("%sRemove news by id.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            Long id = validateNumericValue(scanner.next(), NEWS);
            return newsController.delete(id);
        } catch (NewsException newsException) {
            System.out.println(newsException);
            return deleteNews(scanner);
        }
    }

    private Long validateNumericValue(String id, String subValue) throws NewsException {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NewsException(ErrorCodes.NOT_NUMERIC, subValue + " Id should be number");
        }
    }
}
