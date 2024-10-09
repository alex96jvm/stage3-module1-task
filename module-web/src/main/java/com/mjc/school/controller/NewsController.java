package com.mjc.school.controller;

import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.NewsException;
import com.mjc.school.service.NewsService;
import java.time.LocalDateTime;
import java.util.Scanner;

public class NewsController {
    private final String OPERATION = "Operation: ";
    private final String ENTER_NEWS_ID = "Enter news id:";
    private final String ENTER_AUTHOR_ID = "Enter author id:";
    private final String ENTER_NEWS_TITLE = "Enter news title:";
    private final String ENTER_NEWS_CONTENT = "Enter news content:";
    private final String NEWS = "News";
    private final String AUTHOR = "Author";
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
        try {
            Long authorId = validateNumericValue(scanner.next(), AUTHOR); scanner.nextLine();
            NewsDTO newsDTO = new NewsDTO(title, content, LocalDateTime.now(), LocalDateTime.now(), authorId);
            System.out.println(newsService.createNews(newsDTO));
        } catch (NewsException newsException){
            System.out.println(newsException);
            createNews(scanner);
        }
    }

    public void getAllNews() {
        System.out.printf("%sGet all news.%n", OPERATION);
        newsService.readAllNews().forEach(System.out::println);
    }

    public void getNews(Scanner scanner) {
        System.out.printf("%sGet news by id.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            Long id = validateNumericValue(scanner.next(), NEWS);
            System.out.println(newsService.readByIdNews(id));
        } catch (NewsException newsException) {
            System.out.println(newsException);
        }
    }

    public void updateNews(Scanner scanner) {
        NewsDTO newsDTO = new NewsDTO();
        System.out.printf("%sUpdate news.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            Long id = validateNumericValue(scanner.next(), NEWS); scanner.nextLine();
            newsDTO = newsService.readByIdNews(id);
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
            Long authorId = validateNumericValue(scanner.next(), AUTHOR); scanner.nextLine();
            newsDTO.setTitle(title);
            newsDTO.setContent(content);
            newsDTO.setLastUpdatedDate(LocalDateTime.now());
            newsDTO.setAuthorId(authorId);
            System.out.println(newsService.updateNews(newsDTO));
        } catch (NewsException newsException) {
            System.out.println(newsException);
            updateNews(scanner);
        }
    }

    public void deleteNews(Scanner scanner) {
        System.out.printf("%sRemove news by id.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            Long id = validateNumericValue(scanner.next(), NEWS);
            System.out.println(newsService.deleteNews(id));
        } catch (NewsException newsException) {
            System.out.println(newsException);
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
