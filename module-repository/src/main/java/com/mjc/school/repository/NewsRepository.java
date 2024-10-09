package com.mjc.school.repository;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import java.util.List;

public interface NewsRepository {
    NewsModel createNews(NewsModel news);

    List<NewsModel> readAllNews();

    List<AuthorModel> readAllAuthors();

    NewsModel readByIdNews(Long id);

    NewsModel updateNews(NewsModel news);

    Boolean deleteNews(Long id);
}