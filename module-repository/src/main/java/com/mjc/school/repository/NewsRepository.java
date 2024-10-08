package com.mjc.school.repository;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import java.util.List;
import java.util.Optional;

public interface NewsRepository {
    News createNews(String title, String content, Long authorId);

    List<News> readAllNews();

    List<Author> readAllAuthors();

    Optional<News> readByIdNews(Long id);

    News updateNews(Long id, String title, String content, Long authorId);

    boolean deleteNews(Long id);
}