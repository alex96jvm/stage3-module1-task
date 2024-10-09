package com.mjc.school.service.implementation;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.*;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.NewsException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validation.Validator;
import java.util.List;
import java.util.NoSuchElementException;

public class DefaultNewsService implements NewsService {
    private final NewsRepository newsRepository;
    private final Validator validator;

    public DefaultNewsService(NewsRepository newsRepository, Validator validator) {
        this.newsRepository = newsRepository;
        this.validator = validator;
    }

    @Override
    public NewsDTO createNews(NewsDTO newsDTO) throws NewsException {
        validator.validateNewsData(newsDTO.getTitle(), newsDTO.getContent());
        findAuthorById(newsDTO.getAuthorId());
        NewsModel news = mapToNews(newsDTO);
        newsRepository.createNews(news);
        return newsDTO;
    }

    @Override
    public List<NewsDTO> readAllNews() {
        return newsRepository.readAllNews().stream().map(NewsMapper.INSTANCE::newsToNewsDto)
                .toList();
    }

    @Override
    public NewsDTO readByIdNews(Long id) throws NewsException {
        NewsModel news = findNewsById(id);
        return mapToNewsDto(news);
    }

    @Override
    public NewsDTO updateNews(NewsDTO newsDTO) throws NewsException {
        NewsModel news = findNewsById(newsDTO.getId());
        validator.validateNewsData(newsDTO.getTitle(), newsDTO.getContent());
        findAuthorById(newsDTO.getAuthorId());
        news.setTitle(newsDTO.getTitle());
        news.setContent(newsDTO.getContent());
        news.setLastUpdatedDate(newsDTO.getLastUpdatedDate());
        news.setAuthorId(newsDTO.getAuthorId());
        newsRepository.updateNews(news);
        return newsDTO;
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

    private NewsModel mapToNews(NewsDTO newsDto) {
        return NewsMapper.INSTANCE.newsDtoToNews(newsDto);
    }
}