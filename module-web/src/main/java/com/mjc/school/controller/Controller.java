package com.mjc.school.controller;

import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.NewsException;

import java.util.List;

public interface Controller {
    List<NewsDto> readAll();

    NewsDto readById(Long id) throws NewsException;

    NewsDto create(NewsDto newsDto) throws NewsException;

    NewsDto update(NewsDto newsDto) throws NewsException;

    Boolean delete(Long id) throws NewsException;
}
