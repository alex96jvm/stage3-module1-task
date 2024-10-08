package com.mjc.school.repository;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import java.util.List;
import java.util.Optional;

public interface NewsRepository {
    NewsModel createNews(String title, String content, Long authorId);

    List<NewsModel> readAllNews();

    List<AuthorModel> readAllAuthors();

    Optional<NewsModel> readByIdNews(Long id);

    NewsModel updateNews(Long id, String title, String content, Long authorId);

    Boolean deleteNews(Long id);
}