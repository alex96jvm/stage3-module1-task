package com.mjc.school.service.implementations;

import com.mjc.school.repository.models.News;
import com.mjc.school.repository.NewsDataSource;
import com.mjc.school.service.*;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.validation.NewsException;
import com.mjc.school.service.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultNewsService implements NewsService {
    private final String NEWS = "News";
    private final String AUTHOR = "Author";

    NewsDataSource newsDataSource;
    Validator validator;

    public DefaultNewsService(NewsDataSource newsDataSource, Validator validator) {
        this.newsDataSource = newsDataSource;
        this.validator = validator;
    }

    @Override
    public NewsDTO createNews(String title, String content, String authorStringId) throws NewsException {
        Long authorId = validator.validateNumericValue(authorStringId, AUTHOR);
        validateNewsData(title, content, authorId);
        News newNews = new News(title, content, LocalDateTime.now(), LocalDateTime.now(), authorId);
        newsDataSource.getAllNews().add(newNews);
        return mapToNewsDto(newNews);
    }

    @Override
    public List<NewsDTO> getAllNews() {
        return newsDataSource.getAllNews().stream().map(NewsMapper.INSTANCE::newsToNewsDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDTO getNews(String id) throws NewsException {
        Long newsId = validator.validateNumericValue(id, NEWS);
        News news = validator.validateNewsId(newsId);
        return mapToNewsDto(news);
    }

    @Override
    public NewsDTO updateNews(String id, String title, String content, String authorStringId) throws NewsException {
        Long newsId = validator.validateNumericValue(id, NEWS);
        Long authorId = validator.validateNumericValue(authorStringId, AUTHOR);
        News news = validator.validateNewsId(newsId);
        validateNewsData(title, content, authorId);
        news.setTitle(title);
        news.setContent(content);
        news.setLastUpdatedDate(LocalDateTime.now());
        news.setAuthorId(authorId);
        return mapToNewsDto(news);
    }

    @Override
    public boolean deleteNews(String id) throws NewsException {
        Long newsId = validator.validateNumericValue(id, NEWS);
        return newsDataSource.getAllNews().remove(validator.validateNewsId(newsId));
    }

    private void validateNewsData(String title, String content, Long authorId) throws NewsException {
        validator.validateTitle(title);
        validator.validateContent(content);
        validator.validateAuthorId(authorId);
    }

    private NewsDTO mapToNewsDto(News news) {
        return NewsMapper.INSTANCE.newsToNewsDto(news);
    }

}