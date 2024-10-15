package com.mjc.school.service.implementation;

import com.mjc.school.repository.NewsModelRepository;
import com.mjc.school.repository.datasource.AuthorData;
import com.mjc.school.repository.datasource.NewsDataSource;
import com.mjc.school.repository.implementation.DefaultNewsRepository;
import com.mjc.school.service.NewsDtoService;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.NewsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DefaultNewsServiceTest {
    private static NewsDtoService newsService;

    @BeforeAll
    static void setUp() {
        AuthorData authorData = new AuthorData();
        NewsDataSource newsDataSource = new NewsDataSource();
        NewsModelRepository newsRepository = new DefaultNewsRepository(authorData, newsDataSource);
        newsService = new DefaultNewsService(newsRepository);
    }

    @Test
    void getCountOfAllNews() {
        Assertions.assertEquals(20, newsService.readAll().size());
    }

    @Test
    void getFirstNews() throws NewsException {
        Assertions.assertNotNull(newsService.readById(1L));
    }

    @Test
    void createNewsWithInvalidContent() {
        NewsDto newsDTO = new NewsDto();
        newsDTO.setId(2L);
        newsDTO.setTitle("RULE");
        newsDTO.setContent("The woman standing her ground.");
        newsDTO.setAuthorId(5L);
        Assertions.assertThrows(NewsException.class,
                () -> newsService.create(newsDTO));
    }

    @Test
    void updateNewsTitle() throws NewsException {
        NewsDto newsDTO = new NewsDto();
        newsDTO.setId(15L);
        newsDTO.setTitle("AGRICULTURE");
        newsDTO.setContent("The woman standing her ground.");
        newsDTO.setAuthorId(5L);
        newsService.update(newsDTO);
        Assertions.assertEquals("AGRICULTURE", newsService.readById(15L).getTitle());
    }

    @Test
    void deleteNews() throws NewsException {
        Assertions.assertTrue(newsService.delete(3L));
    }
}
