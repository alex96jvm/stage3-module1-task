package com.mjc.school.controller.implementation;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.NewsException;
import java.util.List;

public class NewsController implements Controller<NewsDto> {
    private final NewsService<NewsDto> newsService;

    public NewsController(NewsService<NewsDto> newsService) {
        this.newsService = newsService;
    }

    @Override
    public List<NewsDto> readAll() {
        return newsService.readAll();
    }

    @Override
    public NewsDto readById(Long id) throws NewsException {
        NewsDto newsDto = newsService.readById(id);
        return newsDto;
    }

    @Override
    public NewsDto create(NewsDto newsDto) throws NewsException {
        newsDto = newsService.create(newsDto);
        return newsDto;
    }

    @Override
    public NewsDto update(NewsDto newsDto) throws NewsException {
        newsDto = newsService.update(newsDto);
        return newsDto;
    }

    @Override
    public Boolean delete(Long id) throws NewsException {
        return newsService.delete(id);
    }
}
