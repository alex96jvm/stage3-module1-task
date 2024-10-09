package com.mjc.school.service.implementation;

import com.mjc.school.repository.NewsModelRepository;
import com.mjc.school.repository.datasource.AuthorData;
import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.implementation.DefaultNewsRepository;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exception.NewsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DefaultNewsServiceTest {
    private static NewsService newsService;

    @BeforeAll
    static void setUp() {
        AuthorData authorData = new AuthorData();
        NewsDataSource newsDataSource = new NewsDataSource();
        NewsModelRepository newsRepository = new DefaultNewsRepository(authorData, newsDataSource);
        newsService = new DefaultNewsService(newsRepository);
    }

    @Test
    void createNewsWithInvalidContent() {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(2L);
        newsDTO.setTitle("RULE");
        newsDTO.setContent("The woman standing her ground.");
        newsDTO.setAuthorId(5L);
        Assertions.assertThrows(NewsException.class,
                () -> newsService.createNews(newsDTO));
    }

    @Test
    void getCountOfAllNews() {
        Assertions.assertEquals(20, newsService.readAllNews().size());
    }

    @Test
    void getFirstNews() throws NewsException {
        Assertions.assertNotNull(newsService.readByIdNews(1L));
    }

    @Test
    void updateNewsTitle() throws NewsException {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(15L);
        newsDTO.setTitle("AGRICULTURE");
        newsDTO.setContent("The woman standing her ground.");
        newsDTO.setAuthorId(5L);
        newsService.updateNews(newsDTO);
        Assertions.assertEquals("AGRICULTURE", newsService.readByIdNews(15L).getTitle());
    }

    @Test
    void deleteNews() throws NewsException {
        Assertions.assertTrue(newsService.deleteNews(3L));
    }
}
