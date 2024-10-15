package com.mjc.school.service;

import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.NewsException;
import java.util.List;

public interface NewsService {
    List<NewsDto> readAll();

    NewsDto readById(Long id) throws NewsException;

    NewsDto create(NewsDto newsDTO) throws NewsException;

    NewsDto update(NewsDto newsDTO) throws NewsException;

    Boolean delete(Long id) throws NewsException;
}
