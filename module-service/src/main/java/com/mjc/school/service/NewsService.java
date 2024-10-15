package com.mjc.school.service;

import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.NewsException;
import java.util.List;

public interface NewsService<T> {
    List<NewsDto> readAll();

    T readById(Long id) throws NewsException;

    T create(T dto) throws NewsException;

    T update(T dto) throws NewsException;

    Boolean delete(Long id) throws NewsException;
}
