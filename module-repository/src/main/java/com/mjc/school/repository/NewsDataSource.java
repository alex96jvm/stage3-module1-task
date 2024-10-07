package com.mjc.school.repository;

import com.mjc.school.repository.models.News;

import java.util.List;

public interface NewsDataSource {
    List<News> getAllNews();
}
