package com.mjc.school.service;

import java.util.List;

public interface NewsService {
    NewsDTO createNews(String title, String content, Long authorId);

    List<NewsDTO> getAllNews();

    NewsDTO getNews(Long id);

    NewsDTO updateNews(Long id, String title, String content, Long authorId);

    boolean deleteNews(Long id);
}
