package com.mjc.school.controller.implementation;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.NewsException;
import com.mjc.school.service.NewsService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class NewsController implements Controller {
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

    public NewsDTO createNews(Scanner scanner) {
        System.out.printf("%sCreate news.%n", OPERATION);
        System.out.println(ENTER_NEWS_TITLE);
        String title = scanner.nextLine();
        System.out.println(ENTER_NEWS_CONTENT);
        String content = scanner.nextLine();
        System.out.println(ENTER_AUTHOR_ID);
        try {
            Long authorId = validateNumericValue(scanner.nextLine(), AUTHOR);
            NewsDTO newsDTO = new NewsDTO(title, content, LocalDateTime.now(), LocalDateTime.now(), authorId);
            return newsService.createNews(newsDTO);
        } catch (NewsException newsException){
            System.out.println(newsException);
            return createNews(scanner);
        }
    }

    public List<NewsDTO> readAllNews() {
        System.out.printf("%sGet all news.%n", OPERATION);
        return newsService.readAllNews();
    }

    public NewsDTO readByIdNews(Scanner scanner) {
        System.out.printf("%sGet news by id.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            Long id = validateNumericValue(scanner.next(), NEWS);
            return newsService.readByIdNews(id);
        } catch (NewsException newsException) {
            System.out.println(newsException);
            return readByIdNews(scanner);
        }
    }

    public NewsDTO updateNews(Scanner scanner) {
        NewsDTO newsDTO = new NewsDTO();
        System.out.printf("%sUpdate news.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            Long id = validateNumericValue(scanner.nextLine(), NEWS);
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
            Long authorId = validateNumericValue(scanner.nextLine(), AUTHOR);
            newsDTO.setTitle(title);
            newsDTO.setContent(content);
            newsDTO.setLastUpdatedDate(LocalDateTime.now());
            newsDTO.setAuthorId(authorId);
            return newsService.updateNews(newsDTO);
        } catch (NewsException newsException) {
            System.out.println(newsException);
            return updateNews(scanner);
        }
    }

    public Boolean deleteNews(Scanner scanner) {
        System.out.printf("%sRemove news by id.%n", OPERATION);
        System.out.println(ENTER_NEWS_ID);
        try {
            Long id = validateNumericValue(scanner.next(), NEWS);
            return newsService.deleteNews(id);
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
