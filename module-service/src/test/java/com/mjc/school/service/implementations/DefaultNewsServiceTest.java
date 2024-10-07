package com.mjc.school.service.implementations;

import com.mjc.school.repository.models.Author;
import com.mjc.school.repository.models.News;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.validation.NewsException;
import com.mjc.school.service.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DefaultNewsServiceTest {

    private static NewsService newsService;

    @BeforeAll
    static void setUp() {
        List<Author> authors = new ArrayList<>(){{
           add(new Author("Barbara Cortland"));
           add(new Author("Danielle Steel"));
           add(new Author("Harold Robbins"));
        }};
        List<News> allNews = new ArrayList<>() {{
            add(new News("MAINE CRIMINAL CODE", "The 18-year old grandson taking his grandma to prom.", LocalDateTime.now(), LocalDateTime.now(), 2L));
            add(new News("AGRICULTURE", "A Nigerian boy solves a 30-year math equation.", LocalDateTime.now(), LocalDateTime.now(), 3L));
            add(new News("DOMESTIC RELATIONS", "Nineteen of the very best uplifting photos of the day.", LocalDateTime.now(), LocalDateTime.now(), 1L));
        }};
        Validator validator = new Validator(authors, allNews);
        newsService = new DefaultNewsService(allNews, validator);
    }

    @Test
    void createNewsWithInvalidContent() {
        Assertions.assertThrows(NewsException.class,
                () -> newsService.createNews("JUDICIARY", "News", "3"));
    }

    @Test
    void getCountOfAllNews() {
        Assertions.assertEquals(3, newsService.getAllNews().size());
    }

    @Test
    void getTitleOfFirstNews() throws NewsException {
        Assertions.assertEquals("MAINE CRIMINAL CODE", newsService.getNews("1").getTitle());
    }

    @Test
    void updateNewsAuthor() throws NewsException {
        newsService.updateNews("2", "AGRICULTURE", "A Nigerian boy solves a 30-year math equation.", "1");
        Assertions.assertEquals(1, newsService.getNews("2").getAuthorId());
    }

    @Test
    void deleteNews() throws NewsException {
        Assertions.assertTrue(newsService.deleteNews("3"));
    }
}
