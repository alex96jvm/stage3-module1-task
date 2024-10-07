package com.mjc.school.service;

import java.util.List;

public interface NewsService {
    NewsDTO createNews(String title, String content, String authorId) throws NewsException;

    List<NewsDTO> getAllNews();

    NewsDTO getNews(String id) throws NewsException;

    NewsDTO updateNews(String id, String title, String content, String authorId) throws NewsException;

    boolean deleteNews(String id) throws NewsException;
}
