package com.mjc.school.controller.implementation;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.NewsDtoService;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.NewsException;
import java.util.List;

public class NewsController implements Controller {
    private final NewsDtoService newsService;

    public NewsController(NewsDtoService newsService) {
        this.newsService = newsService;
    }

    @Override
    public List<NewsDto> readAll() {
        return newsService.readAll();
    }

    @Override
    public NewsDto readById(Long id) throws NewsException {
        return newsService.readById(id);
    }

    @Override
    public NewsDto create(NewsDto newsDto) throws NewsException {
        return newsService.create(newsDto);
    }

    @Override
    public NewsDto update(NewsDto newsDto) throws NewsException {
        return newsService.update(newsDto);
    }

    @Override
    public Boolean delete(Long id) throws NewsException {
        return newsService.delete(id);
    }
}
