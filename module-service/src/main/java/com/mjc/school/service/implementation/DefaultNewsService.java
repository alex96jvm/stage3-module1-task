package com.mjc.school.service.implementation;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.*;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.NewsException;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultNewsService implements NewsService {
    private final String NEWS = "News";
    private final String AUTHOR = "Author";
    private final DefaultValidator validator;
    private final NewsRepository newsRepository;

    public DefaultNewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
        validator = new DefaultValidator();
    }

    @Override
    public NewsDTO createNews(String title, String content, String authorStringId) throws NewsException {
        Long authorId = validator.validateNumericValue(authorStringId, AUTHOR);
        validator.validateNewsData(title, content);
        findAuthorById(authorId);
        News newNews = newsRepository.createNews(title, content, authorId);
        return mapToNewsDto(newNews);
    }

    @Override
    public List<NewsDTO> getAllNews() {
        return newsRepository.readAllNews().stream().map(NewsMapper.INSTANCE::newsToNewsDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDTO getNews(String id) throws NewsException {
        Long newsId = validator.validateNumericValue(id, NEWS);
        News news = findNewsById(newsId);
        return mapToNewsDto(news);
    }

    @Override
    public NewsDTO updateNews(String id, String title, String content, String authorStringId) throws NewsException {
        Long newsId = validator.validateNumericValue(id, NEWS);
        Long authorId = validator.validateNumericValue(authorStringId, AUTHOR);
        findNewsById(newsId);
        validator.validateNewsData(title, content);
        findAuthorById(authorId);
        News news = newsRepository.updateNews(newsId, title, content, authorId);
        return mapToNewsDto(news);
    }

    @Override
    public boolean deleteNews(String id) throws NewsException {
        Long newsId = validator.validateNumericValue(id, NEWS);
        findNewsById(newsId);
        return newsRepository.deleteNews(newsId);
    }

    private News findNewsById(Long id) throws NewsException {
        return newsRepository.readByIdNews(id)
                .orElseThrow(() -> new NewsException(ErrorCodes.NEWS_NOT_FOUND, "News with id " + id + " does not exist."));
    }

    private void findAuthorById(Long authorId) throws NewsException {
        newsRepository.readAllAuthors().stream().filter(n -> n.getId().equals(authorId)).findAny()
                .orElseThrow(() -> new NewsException(ErrorCodes.AUTHOR_NOT_FOUND, "Author Id does not exist. Author Id is: " + authorId));
    }

    private NewsDTO mapToNewsDto(News news) {
        return NewsMapper.INSTANCE.newsToNewsDto(news);
    }
}