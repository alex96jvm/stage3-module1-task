package com.mjc.school.service.implementation;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.datasource.AuthorDataSource;
import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.implementation.DefaultNewsRepository;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.exception.NewsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DefaultNewsServiceTest {
    private static NewsService newsService;

    @BeforeAll
    static void setUp() {
        AuthorDataSource authorDataSource = new AuthorDataSource();
        NewsDataSource newsDataSource = new NewsDataSource();
        NewsRepository newsRepository = new DefaultNewsRepository(authorDataSource, newsDataSource);
        newsService = new DefaultNewsService(newsRepository);
    }

    @Test
    void createNewsWithInvalidContent() {
        Assertions.assertThrows(NewsException.class,
                () -> newsService.createNews("JUDICIARY", "News", "3"));
    }

    @Test
    void getCountOfAllNews() {
        Assertions.assertEquals(20, newsService.getAllNews().size());
    }

    @Test
    void getFirstNews() throws NewsException {
        Assertions.assertNotNull(newsService.getNews("1"));
    }

    @Test
    void updateNewsTitle() throws NewsException {
        newsService.updateNews("2", "AGRICULTURE", "A Nigerian boy solves a 30-year math equation.", "1");
        Assertions.assertEquals("AGRICULTURE", newsService.getNews("2").getTitle());
    }

    @Test
    void deleteNews() throws NewsException {
        Assertions.assertTrue(newsService.deleteNews("3"));
    }
}
