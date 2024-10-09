package com.mjc.school.service.implementation;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.*;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.NewsException;
import com.mjc.school.service.validation.DefaultValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

public class DefaultNewsService implements NewsService {
    private final String NEWS = "News";
    private final String AUTHOR = "Author";
    private final NewsRepository newsRepository;
    private final DefaultValidator validator;

    public DefaultNewsService(NewsRepository newsRepository, DefaultValidator validator) {
        this.newsRepository = newsRepository;
        this.validator = validator;
    }

    @Override
    public NewsDTO createNews(NewsDTO newsDTO) throws NewsException {
        String title = newsDTO.getTitle();
        String content = newsDTO.getContent();
        Long authorId = newsDTO.getAuthorId();
        validator.validateNewsData(title, content);
        findAuthorById(authorId);
        NewsModel newNews = new NewsModel(title, content, LocalDateTime.now(), LocalDateTime.now(), authorId);
        newsRepository.createNews(newNews);
        return mapToNewsDto(newNews);
    }

    @Override
    public List<NewsDTO> getAllNews() {
        return newsRepository.readAllNews().stream().map(NewsMapper.INSTANCE::newsToNewsDto)
                .toList();
    }

    @Override
    public NewsDTO getNews(Long id) throws NewsException {
        NewsModel news = findNewsById(id);
        return mapToNewsDto(news);
    }

    @Override
    public NewsDTO updateNews(NewsDTO newsDTO) throws NewsException {
        Long newsId = newsDTO.getId();
        String title = newsDTO.getTitle();
        String content = newsDTO.getContent();
        Long authorId = newsDTO.getAuthorId();
        LocalDateTime lastUpdatedDate = newsDTO.getLastUpdatedDate();
        NewsModel news = findNewsById(newsId);
        validator.validateNewsData(title, content);
        findAuthorById(authorId);
        news.setTitle(title);
        news.setContent(content);
        news.setAuthorId(authorId);
        news.setLastUpdatedDate(lastUpdatedDate);
        newsRepository.updateNews(news);
        return mapToNewsDto(news);
    }

    @Override
    public Boolean deleteNews(Long id) throws NewsException {
        findNewsById(id);
        return newsRepository.deleteNews(id);
    }

    private NewsModel findNewsById(Long id) throws NewsException {
        try {
            return newsRepository.readByIdNews(id);
        } catch (NoSuchElementException noSuchElementException) {
            throw new NewsException(ErrorCodes.NEWS_NOT_FOUND, "News with id " + id + " does not exist.");
        }
    }

    private void findAuthorById(Long authorId) throws NewsException {
        newsRepository.readAllAuthors().stream().filter(n -> n.getId().equals(authorId)).findAny()
                .orElseThrow(() -> new NewsException(ErrorCodes.AUTHOR_NOT_FOUND, "Author Id does not exist. Author Id is: " + authorId));
    }

    private NewsDTO mapToNewsDto(NewsModel news) {
        return NewsMapper.INSTANCE.newsToNewsDto(news);
    }
}