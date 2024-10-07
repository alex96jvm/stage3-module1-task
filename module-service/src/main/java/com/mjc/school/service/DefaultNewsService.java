package com.mjc.school.service;

import com.mjc.school.repository.News;
import com.mjc.school.repository.NewsDataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class DefaultNewsService implements NewsService{

    NewsDataSource newsDataSource;
    Validator validator;

    public DefaultNewsService(NewsDataSource newsDataSource, Validator validator) {
        this.newsDataSource = newsDataSource;
        this.validator = validator;
    }

    @Override
    public NewsDTO createNews(String title, String content, String authorStringId) throws NewsException {
        Long authorId = validator.validateNumericValue(authorStringId, "Author");
        validator.validateTitle(title);
        validator.validateContent(content);
        validator.validateAuthorId(authorId);
        News newNews = new News(title, content, LocalDateTime.now(), LocalDateTime.now(), authorId);
        newsDataSource.getAllNews().add(newNews);
        return NewsMapper.INSTANCE.newsToNewsDto(newNews);
    }

    @Override
    public List<NewsDTO> getAllNews() {
        return newsDataSource.getAllNews().stream().map(NewsMapper.INSTANCE::newsToNewsDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDTO getNews(String id) throws NewsException {
        Long newsId = validator.validateNumericValue(id, "News");
        News news = validator.validateNewsId(newsId);
        return NewsMapper.INSTANCE.newsToNewsDto(news);
    }

    @Override
    public NewsDTO updateNews(String id, String title, String content, String authorStringId) throws NewsException {
        Long newsId = validator.validateNumericValue(id, "News");
        Long authorId = validator.validateNumericValue(authorStringId, "Author");
        News news = validator.validateNewsId(newsId);
        validator.validateTitle(title);
        validator.validateContent(content);
        validator.validateAuthorId(authorId);
        news.setTitle(title);
        news.setContent(content);
        news.setLastUpdatedDate(LocalDateTime.now());
        news.setAuthorId(authorId);
        return NewsMapper.INSTANCE.newsToNewsDto(news);
    }

    @Override
    public boolean deleteNews(String id) throws NewsException {
        Long newsId = validator.validateNumericValue(id, "News");
        return newsDataSource.getAllNews().remove(validator.validateNewsId(newsId));
    }
}