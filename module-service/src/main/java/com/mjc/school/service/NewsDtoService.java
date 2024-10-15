package com.mjc.school.service;

import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.NewsException;
import java.util.List;

public interface NewsDtoService {
    List<NewsDto> readAll();

    NewsDto readById(Long id) throws NewsException;

    NewsDto create(NewsDto dto) throws NewsException;

    NewsDto update(NewsDto dto) throws NewsException;

    Boolean delete(Long id) throws NewsException;
}
